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
	 @Override
	 public List<TDependiente> listarDependientes() {
	     List<TDependiente> lista = new ArrayList<>();
	     String sql = "SELECT * FROM Empleado e JOIN Dependiente d ON e.identificador = d.id";

	     try (Connection conn = BDConexion.getInstance().getConnection();
	          PreparedStatement pstmt = conn.prepareStatement(sql);
	          ResultSet rs = pstmt.executeQuery()) {

	         while (rs.next()) {
	             TDependiente dep = new TDependiente(
	                 rs.getString("identificador"),
	                 rs.getString("nombre"),
	                 rs.getFloat("sueldo"),
	                 rs.getString("contrasena"),
	                 rs.getFloat("sum_ventas")
	             );
	             lista.add(dep);
	         }
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }

	     return lista;
	 }

	 @Override
	 public boolean eliminar(String id) {
		 String sqlDependiente = "DELETE FROM Dependiente WHERE id = ?";
		 String sqlEmpleado = "DELETE FROM Empleado WHERE identificador = ?";
		 try (Connection conn = BDConexion.getInstance().getConnection();
				 PreparedStatement psDir = conn.prepareStatement(sqlDependiente)) {
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
