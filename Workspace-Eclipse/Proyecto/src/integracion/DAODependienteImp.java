package integracion;

import negocio.dto.Factura;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DAODependienteImp implements DAODependiente{

	 @Override
	    public boolean crearFactura(Factura factura) {
	        String sql = "INSERT INTO Factura (codigo, fecha, hora, importe) VALUES (?, ?, ?, ?)";

	        try (Connection conn = BDConexion.getInstance().getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setString(1, factura.getCodigo());
	            pstmt.setDate(2, new java.sql.Date(factura.getFecha().getTime()));
	            pstmt.setString(3, factura.getHora());
	            pstmt.setFloat(4, factura.getImporte());

	            return pstmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}
