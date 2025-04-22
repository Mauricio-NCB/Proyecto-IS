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
    public TEmpleado readEmpleado(String id) {
        String sql = "SELECT * FROM Empleado WHERE identificador = ?";
        try (Connection conn = BDConexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                float sueldo = rs.getFloat("sueldo");
                String contrasena = rs.getString("contrasena");

                if (esDirector(conn, id)) {
                	String cargo = getCargoDirector(conn, id);
                	
                	return new TDirector(id, nombre, sueldo, contrasena, cargo);
                }
                else if (esDependiente(conn, id)) {
                	Float sumVentas = getSumVentas(conn, id);
                	
                	return new TDependiente(id, nombre, sueldo, contrasena, sumVentas);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
