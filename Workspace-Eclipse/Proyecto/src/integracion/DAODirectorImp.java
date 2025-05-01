package integracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.dto.TDirector;

public class DAODirectorImp implements DAODirector {
    
    private TDirector mapDir(ResultSet rs) throws SQLException {
        String id = rs.getString("e_identificador");
        String nombre = rs.getString("e_nombre");
        float sueldo = rs.getFloat("e_sueldo");
        String cargo = rs.getString("d_cargo");
        String contrasena = rs.getString("e_contrasena");
        return new TDirector(id, nombre, sueldo, contrasena, cargo);
    }
    
    //Crea un empleado Director
    @Override
    public boolean createDirector(TDirector director) {
    	String sqlEmpleado = "INSERT INTO Empleado (identificador, nombre, sueldo, contrasena) VALUES (?, ?, ?, ?)";
        String sqlDir = "INSERT INTO Director (id, cargo) VALUES (?, ?)";

        try (Connection conn = BDConexion.getInstance().getConnection();
        		PreparedStatement pstmtEm = conn.prepareStatement(sqlEmpleado)) {
        	pstmtEm.setString(1, director.getIdentificador());
        	pstmtEm.setString(2, director.getNombre());
        	pstmtEm.setFloat(3, director.getSueldo());
        	pstmtEm.setString(4, director.getContrasena());
            if (pstmtEm.executeUpdate() > 0) {
            	try (PreparedStatement pstmtDir = conn.prepareStatement(sqlDir)) {
            		pstmtDir.setString(1, director.getIdentificador());
                    pstmtDir.setString(2, director.getCargo());
                    return pstmtDir.executeUpdate() > 0;
            	}
            }
            else {return false;}
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Obtiene Director
    @Override
    public TDirector obtenerPorId(String id) {
    	TDirector director = null;
        String sql = "SELECT e.identificador as e_identificador, e.nombre as e_nombre, e.sueldo as e_sueldo, " +
        				"e.contrasena as e_contrasena, d.cargo as d_cargo "+
        				"FROM Empleado e JOIN Director d ON e.identificador = d.id WHERE e.identificador = ?";
        try (Connection conn = BDConexion.getInstance().getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    director = mapDir(rs);
                }
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return director;
    }
    
    
    //Obtiene todos los Directores
    @Override
    public List<TDirector> obtenerTodos() {
        String sql = "SELECT e.identificador as e_identificador, e.nombre as e_nombre, " +
                		"e.sueldo as e_sueldo, e.contrasena as e_contrasena, d.cargo as d_cargo " +
                		"FROM Empleado e JOIN Director d ON e.identificador = d.id";
        List<TDirector> lista = new ArrayList<>();
        try (Connection conn = BDConexion.getInstance().getConnection();
        		PreparedStatement ps = conn.prepareStatement(sql);
        		ResultSet rs = ps.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapDir(rs));
            }
            return lista;
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return lista;
    }
    
    //Actualiza los datos del director
    @Override
    public boolean actualizar(TDirector director) {
        String sqlEmpleado = "UPDATE Empleado SET nombre = ?, sueldo = ?, contrasena = ? WHERE identificador = ?";
        String sqlDirector = "UPDATE Director SET cargo = ? WHERE id = ?";
        try (Connection conn = BDConexion.getInstance().getConnection();
        		PreparedStatement psEm = conn.prepareStatement(sqlEmpleado)) {
        	psEm.setString(4, director.getIdentificador());
        	psEm.setString(1, director.getNombre());
        	psEm.setFloat(2, director.getSueldo());
        	psEm.setString(3, director.getContrasena());
        	if (psEm.executeUpdate() > 0) {
            	try (PreparedStatement pstmtDir = conn.prepareStatement(sqlDirector)) {
            		pstmtDir.setString(2, director.getIdentificador());
                    pstmtDir.setString(1, director.getCargo());
                    return pstmtDir.executeUpdate() > 0;
            	}
        	}
        } catch (SQLException e) {   
        	e.printStackTrace();
        }
		return false;
    }
    
    //Elimina el director
    @Override
    public boolean eliminar(String id) {
        String sqlDirector = "DELETE FROM Director WHERE id = ?";
        String sqlEmpleado = "DELETE FROM Empleado WHERE identificador = ?";
        try (Connection conn = BDConexion.getInstance().getConnection();
        		PreparedStatement psDir = conn.prepareStatement(sqlDirector)) {
            psDir.setString(1, id);
            if (psDir.executeUpdate() > 0) {
            	try (PreparedStatement psEm = conn.prepareStatement(sqlEmpleado)){
            		psEm.setString(1, id);
            		return psEm.executeUpdate() > 0;
            	}
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		return false;
    }
}

