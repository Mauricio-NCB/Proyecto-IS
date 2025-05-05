package integracion;

import negocio.dto.Factura;
import negocio.dto.TDependiente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
	 @Override
	 public List<TDependiente> listarDependientes() {
	     List<TDependiente> lista = new ArrayList<>();
	     String sql = "SELECT * FROM Empleado WHERE tipo = 'dependiente'";

	     try (Connection conn = BDConexion.getInstance().getConnection();
	          PreparedStatement pstmt = conn.prepareStatement(sql);
	          ResultSet rs = pstmt.executeQuery()) {

	         while (rs.next()) {
	             TDependiente dep = new TDependiente(
	                 rs.getString("identificador"),
	                 rs.getString("nombre"),
	                 rs.getFloat("sueldo"),
	                 rs.getString("contrasena"),
	                 rs.getFloat("sumVentas")
	             );
	             lista.add(dep);
	         }
	     } catch (SQLException e) {
	         e.printStackTrace();
	     }

	     return lista;
	 }

}
