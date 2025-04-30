package integracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import negocio.dto.Envio;

public class DAOEnvioImp implements DAOEnvio {
@Override
public boolean crearEnvio(Envio envio) {
	String sql = "INSERT INTO Envio (id, coste, direccion, estado, fechaEnvio, fechaEntrega) VALUES (?,?,?,?,?,?)";
	 try (Connection conn = BDConexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
		
		 pstmt.setString(1, envio.getID());
         pstmt.setFloat(2, envio.getCoste());
         pstmt.setString(3, envio.getDireccion());
         pstmt.setString(4, envio.getEstado());
         pstmt.setDate(5, new Date(envio.getFechaEnvio().getTime()));
         pstmt.setDate(6, new Date(envio.getFechaEntrega().getTime()));

         return pstmt.executeUpdate() > 0;
	}
	 catch (SQLException e) {
         e.printStackTrace();
         return false;
     }
}
	
	
}
