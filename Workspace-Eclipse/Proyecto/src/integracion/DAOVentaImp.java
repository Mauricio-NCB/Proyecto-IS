package integracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.dto.TCliente;
import negocio.dto.TDependiente;
import negocio.dto.TFactura;
import negocio.dto.TLineaVenta;
import negocio.dto.TProducto;
import negocio.dto.TVenta;

public class DAOVentaImp implements DAOVenta{

	
	private List<TVenta> ventas = new ArrayList<>(); 
	@Override
	public void crearVenta(TVenta venta) throws Exception {
        String sqlVenta = "INSERT INTO Ventas (codigo, fecha, hora, id_cliente, id_dependiente) VALUES (?, ?, ?, ?, ?)";
        String sqlLinea = "INSERT INTO Lineas_Venta (id_venta, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

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
		   String sql = "UPDATE Ventas SET fecha = ?, hora = ?, id_cliente = ?, id_dependiente = ?, id_factura = ? WHERE codigo = ?";

	        try (Connection conn = BDConexion.getInstance().getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setDate(1, java.sql.Date.valueOf(venta.getFecha()));
	            pstmt.setTime(2, java.sql.Time.valueOf(venta.getHora()));
	            pstmt.setInt(3, venta.getTiene().getNumSocio());
	            pstmt.setString(4, venta.getDependiente().getIdentificador());
	            pstmt.setString(5, venta.getFactura() != null ? venta.getFactura().getCodigo() : null);
	            pstmt.setString(6, venta.getCodigo());

	            if (pstmt.executeUpdate() == 0) {
	                throw new SQLException("No se actualizó ninguna venta");
	            }
	        }
	}

	@Override
	public TVenta obtenerVenta(String codigo) throws Exception {
		String sql = "SELECT v.*, c.nombre as nombre_cliente, d.nombre as nombre_dependiente, f.codigo as codigo_factura " +
                "FROM Ventas v " +
                "JOIN Clientes c ON v.id_cliente = c.num_socio " +
                "JOIN Dependientes d ON v.id_dependiente = d.identificador " +
                "LEFT JOIN Facturas f ON v.id_factura = f.codigo " +
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
                       rs.getString("id_dependiente"),
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


           String codigoFactura = rs.getString("codigo_factura");
           if (codigoFactura != null) {
               TFactura factura = new TFactura();
               factura.setCodigo(codigoFactura);
               venta.setFactura(factura);
           }

   
           venta.setLineasVenta(obtenerLineasVenta(codigo));

           return venta;
       }
       return null;
   	}
	}

	@Override
	public List<TVenta> obtenerTodasVentas () throws Exception {
		String sql = "SELECT codigo FROM Ventas";
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
        String sql = "SELECT lv.*, p.* FROM lineas_venta lv JOIN productos p ON lv.id_producto = p.id WHERE lv.id_venta = ?";
        List<TLineaVenta> lineas = new ArrayList<>();
        
        try (Connection conn = BDConexion.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, codigoVenta);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                TProducto producto = new TProducto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
                
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
