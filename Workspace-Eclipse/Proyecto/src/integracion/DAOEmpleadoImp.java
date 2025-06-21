package integracion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import negocio.dto.TDependiente;
import negocio.dto.TDirector;
import negocio.dto.TEmpleado;
import negocio.dto.TFactura;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOEmpleadoImp implements DAOEmpleado {

	@Override
    public TEmpleado readEmpleado(String id) throws Exception {
        String sql = "SELECT * FROM Empleado WHERE identificador = ?";
        TEmpleado empleado = null;
        
        try (Connection conn = BDConexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) throw new SQLException("No se pudo obtener el empleado en la tabla Empleado");
                      
            String nombre = rs.getString("nombre");
            float sueldo = rs.getFloat("sueldo");
            String contrasena = rs.getString("contrasena");

            if (esDirector(conn, id)) {
            	String cargo = getCargoDirector(conn, id);
            	
            	empleado = new TDirector(id, nombre, sueldo, contrasena, cargo);
            }
            else if (esDependiente(conn, id)) {
            	Float sumVentas = getSumVentas(conn, id);
            	
            	empleado = new TDependiente(id, nombre, sueldo, contrasena, sumVentas);
            }

        } catch (SQLException e) {
            throw new Exception("Error al leer empleado en la base de datos: " + e.getMessage(), e);
        }
        
        return empleado;
    }

	@Override
    public boolean createEmpleado(TEmpleado empleado) throws Exception {
    	String sqlEmpleado = "INSERT INTO Empleado (identificador, nombre, sueldo, contrasena) VALUES (?, ?, ?, ?)";
        String sqlDir = "INSERT INTO Director (id, cargo) VALUES (?, ?)";
		String sqlDep = "INSERT INTO Dependiente (id, sum_ventas) VALUES (?, ?)";

        try (Connection conn = BDConexion.getInstance().getConnection();
        		PreparedStatement pstmtEm = conn.prepareStatement(sqlEmpleado)) {
        	
        	pstmtEm.setString(1, empleado.getIdentificador());
        	pstmtEm.setString(2, empleado.getNombre());
        	pstmtEm.setFloat(3, empleado.getSueldo());
        	pstmtEm.setString(4, empleado.getContrasena());
        	
        	if (pstmtEm.executeUpdate() == 0) throw new SQLException("No se pudo insertar el empleado en la tabla Empleado");
        	
			if (empleado instanceof TDirector) {
				TDirector director = (TDirector)empleado;
				
				try (PreparedStatement pstmtDir = conn.prepareStatement(sqlDir)) {
					pstmtDir.setString(1, director.getIdentificador());
					pstmtDir.setString(2, director.getCargo());

		        	if (pstmtDir.executeUpdate() == 0) throw new SQLException("No se pudo insertar el empleado en la tabla Empleado");
				}
			}
			else {
				TDependiente dependiente = (TDependiente)empleado;
				
				try (PreparedStatement pstmtDep = conn.prepareStatement(sqlDep)) {
					pstmtDep.setString(1, dependiente.getIdentificador());
					pstmtDep.setFloat(2, dependiente.getSumVentas());

		        	if (pstmtDep.executeUpdate() == 0) throw new SQLException("No se pudo insertar el empleado en la tabla Empleado");
				}
			}

			return true;
        } catch (SQLException e) {
        	throw new Exception("Error al crear empleado en la base de datos: " + e.getMessage(), e);
        }
    }

	//Actualiza los datos del empleado, tanto si es Director como si es Dependiente
    @Override
    public boolean actualizarEmpleado(TEmpleado empleado) {
        String sqlEmpleado = "UPDATE Empleado SET nombre = ?, sueldo = ?, contrasena = ? WHERE identificador = ?";

        try (Connection conn = BDConexion.getInstance().getConnection();
        		PreparedStatement psEm = conn.prepareStatement(sqlEmpleado)) {
			psEm.setString(4, empleado.getIdentificador());
			psEm.setString(1, empleado.getNombre());
			psEm.setFloat(2, empleado.getSueldo());
			psEm.setString(3, empleado.getContrasena());

			if (psEm.executeUpdate() == 0) throw new SQLException("No se pudo actualizar el empleado en la tabla Empleado");
			
			boolean actualizado = false;
			if (empleado instanceof TDirector) {
				TDirector director = (TDirector) empleado;
				String sqlDirector = "UPDATE Director SET cargo = ? WHERE id = ?";
				try (PreparedStatement pstmtDir = conn.prepareStatement(sqlDirector)) {
					pstmtDir.setString(1, director.getCargo());
					pstmtDir.setString(2, director.getIdentificador());
					actualizado = pstmtDir.executeUpdate() > 0;
				}
			} else if (empleado instanceof TDependiente) {
				TDependiente dependiente = (TDependiente) empleado;
				String sqlDependiente = "UPDATE Dependiente SET sum_ventas = ? WHERE id = ?";
				try (PreparedStatement pstmtDep = conn.prepareStatement(sqlDependiente)) {
					pstmtDep.setFloat(1, dependiente.getSumVentas());
					pstmtDep.setString(2, dependiente.getIdentificador());
					actualizado = pstmtDep.executeUpdate() > 0;
				}
			}
			return actualizado;
		} catch (SQLException e) {
			Logger.getLogger(DAOEmpleadoImp.class.getName()).log(Level.SEVERE, "Error updating employee", e);
		}
		return false;
    }


    public boolean eliminar(String id){
        String sqlDirector = "DELETE FROM Director WHERE id = ?";
        String sqlDependiente = "DELETE FROM Dependiente WHERE id = ?";
        String sqlEmpleado = "DELETE FROM Empleado WHERE identificador = ?";

        try (Connection conn = BDConexion.getInstance().getConnection()) {

            // Elimina en ambas tablas, por si acaso
            try (PreparedStatement psDir = conn.prepareStatement(sqlDirector)) {
                psDir.setString(1, id);
                psDir.executeUpdate();
            }
            try (PreparedStatement psDep = conn.prepareStatement(sqlDependiente)) {
                psDep.setString(1, id);
                psDep.executeUpdate();
            }
            boolean eliminado = false;
            try (PreparedStatement psEm = conn.prepareStatement(sqlEmpleado)) {
                psEm.setString(1, id);
                eliminado = psEm.executeUpdate() > 0;
            }

            return eliminado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
    }

	//Obtiene todos los Empleados
    @Override
    public List<TEmpleado> ListarEmpleados() {
        String sql = "SELECT e.identificador, e.nombre, e.sueldo, e.contrasena, d.cargo, dep.sum_ventas " +
                		"FROM Empleado e " +
                      	"LEFT JOIN Director d ON e.identificador = d.id " +
                 		"LEFT JOIN Dependiente dep ON e.identificador = dep.id";
        List<TEmpleado> lista = new ArrayList<>();
		try (Connection conn = BDConexion.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				String id = rs.getString("identificador");
				String nombre = rs.getString("nombre");
				float sueldo = rs.getFloat("sueldo");
				String contrasena = rs.getString("contrasena");
				String cargo = rs.getString("cargo");
				Float sumVentas = (rs.getObject("sum_ventas") != null) ? rs.getFloat("sum_ventas") : null;

				if (cargo != null) { // Es Director
					lista.add(new TDirector(id, nombre, sueldo, contrasena, cargo));
				} else if (sumVentas != null) { // Es Dependiente
					lista.add(new TDependiente(id, nombre, sueldo, contrasena, sumVentas));
				}
			}
			return lista;
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return lista;
    }

	@Override
    public boolean existeEmpleado(String id) {
		// Devuelve true si existe un empleado con ese id
        String sql = "SELECT 1 FROM Empleado WHERE identificador = ? LIMIT 1";
        try (Connection conn = BDConexion.getInstance().getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return false;
    }
	
	private boolean esDirector(Connection conn, String id) throws SQLException {
		String sql = "SELECT * FROM Director WHERE id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			return rs.next();
		}
	}
	
	private boolean esDependiente(Connection conn, String id) throws SQLException {
		String sql = "SELECT * FROM Dependiente WHERE id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			return rs.next();
		}
	}
	
	private String getCargoDirector(Connection conn, String id) throws SQLException {
		String sql = "SELECT * FROM Director WHERE id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			return rs.next() ? rs.getString("cargo") : null;
		}
	}
	
	private float getSumVentas(Connection conn, String id) throws SQLException {
		String sql = "SELECT * FROM Dependiente WHERE id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			return rs.next() ? rs.getFloat("sum_ventas") : 0;
		}
	}

	public boolean crearFactura(TFactura factura) {
		String sql = "INSERT INTO Factura (codigo, fecha, hora, importe) VALUES (?, ?, ?, ?)";

		try (Connection conn = BDConexion.getInstance().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, factura.getCodigo());
			pstmt.setDate(2, Date.valueOf(factura.getFecha()));
			pstmt.setTime(3, Time.valueOf(factura.getHora()));
			pstmt.setFloat(4, factura.getImporte());

			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}	
}
