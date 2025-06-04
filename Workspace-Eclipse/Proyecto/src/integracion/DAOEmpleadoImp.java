package integracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import negocio.dto.TDependiente;
import negocio.dto.TDirector;
import negocio.dto.TEmpleado;

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
            
            if (rs.next()) {
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
            }

        } catch (SQLException e) {
            throw new Exception("Error al leer empleado en la base de datos: " + e.getMessage(), e);
        }
        
        return empleado;
    }

	@Override
    public void createEmpleado(TEmpleado empleado) throws Exception {
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

        } catch (SQLException e) {
        	throw new Exception("Error al crear empleado en la base de datos: " + e.getMessage(), e);
        }
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
	
}
