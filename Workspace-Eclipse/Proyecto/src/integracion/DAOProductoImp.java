package integracion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.dto.TCamiseta;
import negocio.dto.TEntrada;
import negocio.dto.TJuguete;
import negocio.dto.TPoster;
import negocio.dto.TProducto;

public class DAOProductoImp implements DAOProducto{

	@Override
	public boolean createProducto(TProducto producto) {
		
		String sql = "INSERT INTO Producto (nombre, precio, stock) VALUES (?, ?, ?)";
		
		try (Connection conn = BDConexion.getInstance().getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, producto.getNombre());
            pstmt.setFloat(2, producto.getPrecio());
            pstmt.setLong(3, producto.getStock());
            
            return pstmt.executeUpdate() > 0;
		}catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
    public List<TProducto> obtenerTodosLosProductos() {
        List<TProducto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Producto";
        
        try (Connection conn = BDConexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("nombre");
                float precio = rs.getFloat("precio");
                int stock = rs.getInt("stock");

                if (esCamiseta(conn, id)) {
                    TCamiseta camiseta = crearCamiseta(conn, id, nombre, precio, stock);
                    productos.add(camiseta);
                } 
                else if (esEntrada(conn, id)) {
                    TEntrada entrada = crearEntrada(conn, id, nombre, precio, stock);
                    productos.add(entrada);
                }
                else if (esJuguete(conn, id)) {
                    TJuguete juguete = crearJuguete(conn, id, nombre, precio, stock);
                    productos.add(juguete);
                }
                else if (esPoster(conn, id)) {
                    TPoster poster = crearPoster(conn, id, nombre, precio, stock);
                    productos.add(poster);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return productos;
    }
	
	private boolean esCamiseta(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM Camiseta WHERE ID = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }

    private boolean esEntrada(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM Entrada WHERE ID = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }

    private boolean esJuguete(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM Juguete WHERE ID = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }

    private boolean esPoster(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM Poster WHERE ID = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }

    private TCamiseta crearCamiseta(Connection conn, int id, String nombre, float precio, int stock) throws SQLException {
        String sql = "SELECT * FROM Camiseta WHERE ID = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int talla = rs.getInt("talla");
                String dorsalJugador = rs.getString("dorsal");
                int numeroJugador = rs.getInt("numero");
                
                return new TCamiseta(id, nombre, precio, stock, talla, dorsalJugador, numeroJugador);
            }
        }
        return null;
    }

    private TEntrada crearEntrada(Connection conn, int id, String nombre, float precio, int stock) throws SQLException {
        String sql = "SELECT * FROM Entrada WHERE ID = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Date fecha = rs.getDate("fecha");
                String hora = rs.getString("hora");
                String ubicacion = rs.getString("ubicacion");
                String numeroAsiento = rs.getString("numero_asiento");
                String partido = rs.getString("partido");
                
                return new TEntrada(id, nombre, precio, stock, fecha, hora, ubicacion, numeroAsiento, partido);
            }
        }
        return null;
    }

    private TJuguete crearJuguete(Connection conn, int id, String nombre, float precio, int stock) throws SQLException {
        String sql = "SELECT * FROM Juguete WHERE ID = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String tipo = rs.getString("tipo");
                String tamano = rs.getString("tamano");
                
                return new TJuguete(id, nombre, precio, stock, tipo, tamano);
            }
        }
        return null;
    }

    private TPoster crearPoster(Connection conn, int id, String nombre, float precio, int stock) throws SQLException {
        String sql = "SELECT * FROM Poster WHERE ID = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String tamano = rs.getString("tamano");
                
                return new TPoster(id, nombre, precio, stock, tamano);
            }
        }
        return null;
    }


}
