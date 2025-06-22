package integracion;

import negocio.dto.TDependiente;
import negocio.dto.TFactura;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class DAODependienteImp implements DAODependiente{

	 @Override
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
