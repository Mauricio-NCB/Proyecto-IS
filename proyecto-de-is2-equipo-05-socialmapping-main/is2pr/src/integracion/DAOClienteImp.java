package integracion;

import java.sql.Connection;
import java.sql.PreparedStatement;

import negocio.dto.TCliente;

public class DAOClienteImp implements DAOCliente {
	
	@Override
    public void readCliente() {
    }
    
	@Override
    public void createCliente(TCliente cliente) throws Exception{
    	String sql = "INSERT INTO Cliente (id, dni, nombre, activo) VALUES (?, ?, ?, ?)";
    	
    	
    	try (Connection conn = BDConexion.getInstance().getConnection();
   			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

   			pstmt.setLong(1, cliente.getId());
   			pstmt.setString(2, cliente.getDni());
   			pstmt.setString(3, cliente.getNombre());
   			pstmt.setBoolean(4, cliente.getActivo());

   			pstmt.executeUpdate();
   		}
    }

	@Override
    public void updateCliente(TCliente cliente) throws Exception{
		
		String sql = "UPDATE Cliente SET dni=?, nombre=?, activo=? WHERE id=?";

		try (Connection conn = BDConexion.getInstance().getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, cliente.getDni());
				pstmt.setString(2, cliente.getNombre());
				pstmt.setBoolean(3, cliente.getActivo());
				pstmt.setLong(4, cliente.getId());
				

				pstmt.executeUpdate();
			}
		
    }

	@Override
    public void deleteCliente() {
    }

	
	


	

}
