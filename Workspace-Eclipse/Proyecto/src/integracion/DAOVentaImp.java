package integracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.dto.TCamiseta;
import negocio.dto.TCliente;
import negocio.dto.TDependiente;
import negocio.dto.TEntrada;
import negocio.dto.TFactura;
import negocio.dto.TJuguete;
import negocio.dto.TLineaVenta;
import negocio.dto.TPoster;
import negocio.dto.TProducto;
import negocio.dto.TVenta;

public class DAOVentaImp implements DAOVenta{


	@Override
	public void crearVenta(TVenta venta) throws Exception {
        String sqlVenta = "INSERT INTO Venta (codigo, fecha, hora, cliente, dependiente) VALUES (?, ?, ?, ?, ?)";
        String sqlLinea = "INSERT INTO Linea_Venta (venta, producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = BDConexion.getInstance().getConnection()) {
            conn.setAutoCommit(false); 

            
            try (PreparedStatement pstmtVenta = conn.prepareStatement(sqlVenta)) {
                pstmtVenta.setString(1, venta.getCodigo());
                pstmtVenta.setDate(2, java.sql.Date.valueOf(venta.getFecha()));
                pstmtVenta.setTime(3, java.sql.Time.valueOf(venta.getHora()));
                pstmtVenta.setInt(4, venta.getTiene().getNumSocio());
                pstmtVenta.setString(5, venta.getDependiente().getIdentificador());

                if (pstmtVenta.executeUpdate() == 0) {
                    throw new SQLException("Error al insertar venta");
                }
            }

            try (PreparedStatement pstmtLinea = conn.prepareStatement(sqlLinea)) {
                for (TLineaVenta linea : venta.getLineasVenta()) {
                    pstmtLinea.setString(1, venta.getCodigo());
                    pstmtLinea.setInt(2, linea.getProducto().getID());
                    pstmtLinea.setInt(3, linea.getCantidad());
                    pstmtLinea.setDouble(4, linea.getProducto().getPrecio());
                    pstmtLinea.addBatch();
                }
                pstmtLinea.executeBatch();
            }

            conn.commit(); 

        } catch (SQLException e) {
            throw new SQLException("Error al crear venta: " + e.getMessage(), e);
        }
    
		
	}

	@Override
	public void actualizarVenta(TVenta venta) throws Exception {
		   String sql = "UPDATE Venta SET fecha = ?, hora = ?, cliente = ?, dependiente = ? WHERE codigo = ?";

	        try (Connection conn = BDConexion.getInstance().getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setDate(1, java.sql.Date.valueOf(venta.getFecha()));
	            pstmt.setTime(2, java.sql.Time.valueOf(venta.getHora()));
	            pstmt.setInt(3, venta.getTiene().getNumSocio());
	            pstmt.setString(4, venta.getDependiente().getIdentificador());
	            pstmt.setString(5, venta.getCodigo());

	            if (pstmt.executeUpdate() == 0) {
	                throw new SQLException("No se actualiz� ninguna venta");
	            }
	        }
	}

	@Override
	public TVenta obtenerVenta(String codigo) throws Exception {
		String sql = "SELECT v.*, c.nombre as nombre_cliente, d.nombre as nombre_dependiente" +
                "FROM Venta v " +
                "JOIN Cliente c ON v.cliente = c.num_socio " +
                "JOIN Dependiente d ON v.dependiente = d.identificador " +
                "WHERE v.codigo = ?";

   try (Connection conn = BDConexion.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {

       pstmt.setString(1, codigo);
       ResultSet rs = pstmt.executeQuery();

       if (rs.next()) {
           
    	   TCliente cliente = new TCliente(
                   rs.getString("nombre_cliente"),
                   rs.getString("direccion"),
                   rs.getString("correo")
               );

               TDependiente dependiente = new TDependiente(
                       rs.getString("dependiente"),
                       rs.getString("nombre_dependiente"),
                       rs.getFloat("sueldo"),
                       "", 
                       rs.getFloat("sum_ventas"));

           TVenta venta = new TVenta(
               rs.getString("codigo"),
               rs.getDate("fecha").toLocalDate(),
               rs.getTime("hora").toLocalTime(),
               cliente,
               dependiente
           );

           venta.setLineasVenta(obtenerLineasVenta(codigo));

           return venta;
       }
       return null;
   	}
	}

	@Override
	public List<TVenta> obtenerTodasVentas () throws Exception {
		String sql = "SELECT codigo FROM Venta";
        List<TVenta> ventas = new ArrayList<>();

        try (Connection conn = BDConexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ventas.add(obtenerVenta(rs.getString("codigo")));
            }
        }catch (SQLException e) {
        	throw new Exception("Error al obtener las ventas de la base de datos: " + e.getMessage(), e);
        }
        return ventas;
	}
	
	
	public List<TLineaVenta> obtenerLineasVenta(String codigoVenta) throws SQLException {
        String sql = "SELECT lv.*, p.* FROM linea_venta lv JOIN productos p ON lv.producto = p.id WHERE lv.venta = ?";
        List<TLineaVenta> lineas = new ArrayList<>();
        
        try (Connection conn = BDConexion.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, codigoVenta);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
            	 TProducto producto;
                 String tipoProducto = rs.getString("tipo"); 
                 switch(tipoProducto) {
                 case "CAMISETA":
                     producto = new TCamiseta(
                         rs.getInt("id"),
                         rs.getString("nombre"),
                         rs.getFloat("precio"),
                         rs.getInt("stock"),
                         rs.getInt("talla"),
                         rs.getString("dorsal"),
                         rs.getInt("numero")
                     );
                     break;
                 case "ENTRADA":
                     producto = new TEntrada(
                         rs.getInt("id"),
                         rs.getString("nombre"),
                         rs.getFloat("precio"),
                         rs.getInt("stock"),
                         rs.getDate("fecha_evento"),
                         rs.getString("hora_evento"),
                         rs.getString("ubicacion"), 
                         rs.getString("numAsiento"), 
                         rs.getString("partido")
                     );
                     break;
                 case "POSTER":
                	 producto = new TPoster(
                             rs.getInt("id"),
                             rs.getString("nombre"),
                             rs.getFloat("precio"),
                             rs.getInt("stock"),
                             rs.getString("tamano")
                         );
                	 break;
                 case "JUGUETE":
                	 producto = new TJuguete(
                             rs.getInt("id"),
                             rs.getString("nombre"),
                             rs.getFloat("precio"),
                             rs.getInt("stock"),
                             rs.getString("tipo"),
                             rs.getString("tamano")
                         );
                	 break;
                
                 default:
                     throw new SQLException("Tipo de producto desconocido: " + tipoProducto);
             }
                
                TLineaVenta linea = new TLineaVenta(
                    producto,
                    rs.getInt("cantidad")
                );
                
                lineas.add(linea);
            }
            return lineas;
        }
    }

}
