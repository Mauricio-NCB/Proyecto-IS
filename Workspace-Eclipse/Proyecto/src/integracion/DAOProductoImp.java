package integracion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			pstmt.setString(1, producto.getNombre());
	        pstmt.setFloat(2, producto.getPrecio());
	        pstmt.setInt(3, producto.getStock());
	        
	        pstmt.executeUpdate();
	        
	        ResultSet generatedKeys = pstmt.getGeneratedKeys();
	        if (!generatedKeys.next()) {
	            return false;
	        }
	        int idGenerado = generatedKeys.getInt(1);
            producto.setID(idGenerado);
            generatedKeys.close();
            pstmt.close();
            
	        if (producto instanceof TCamiseta) {
	            TCamiseta c = (TCamiseta) producto;
	            String sql2 = "INSERT INTO Camiseta (ID, talla, dorsal, numero) VALUES (?, ?, ?, ?)";
	            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
	            pstmt2.setInt(1, idGenerado);
	            pstmt2.setInt(2, c.getTalla());
	            pstmt2.setString(3, c.getDorsalJugador());
	            pstmt2.setInt(4, c.getNumeroJugador());
	            pstmt2.executeUpdate();
	        } 
	        else if (producto instanceof TEntrada) {
	            TEntrada e = (TEntrada) producto;
	            String sql2 = "INSERT INTO Entrada (ID, fecha, hora, ubicacion, numero_asiento, partido) VALUES (?, ?, ?, ?, ?, ?)";
	            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
	            pstmt2.setInt(1, idGenerado);
	            pstmt2.setDate(2, new Date(e.getFecha().getTime()));
	            pstmt2.setString(3, e.getHora());
	            pstmt2.setString(4, e.getUbicacion());
	            pstmt2.setString(5, e.getNumeroAsiento());
	            pstmt2.setString(6, e.getPartido());
	            pstmt2.executeUpdate();
	        }
	        else if (producto instanceof TJuguete) {
	            TJuguete j = (TJuguete) producto;
	            String sql2 = "INSERT INTO Juguete (ID, tipo, tamano) VALUES (?, ?, ?)";
	            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
	            pstmt2.setInt(1, idGenerado);
	            pstmt2.setString(2, j.getTipo());
	            pstmt2.setString(3, j.getTamano());
	            pstmt2.executeUpdate();
	        }
	        else if (producto instanceof TPoster) {
	            TPoster p = (TPoster) producto;
	            String sql2 = "INSERT INTO Poster (ID, tamano) VALUES (?, ?)";
	            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
	            pstmt2.setInt(1, idGenerado);
	            pstmt2.setString(2, p.getTamano());
	            pstmt2.executeUpdate();
	        }
            
	        return true;

		}catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
    public boolean updateProducto(TProducto producto) {
		
		String sqlProducto = "UPDATE Producto SET nombre = ?, precio = ?, stock = ? WHERE ID = ?";
		
		try (Connection conn = BDConexion.getInstance().getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sqlProducto)) {
			
			 pstmt.setString(1, producto.getNombre());
             pstmt.setFloat(2, producto.getPrecio());
             pstmt.setInt(3, producto.getStock());
             pstmt.setInt(4, producto.getID());
			
             pstmt.executeUpdate();
             
             boolean actualizado = actualizarProducto(conn, producto);
             
             if (!actualizado) {
                 return false;
             }
             
             return true;
		} catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
		
	}
	
	private boolean actualizarProducto(Connection conn, TProducto producto) throws SQLException {
        if (producto instanceof TCamiseta) {
            TCamiseta c = (TCamiseta) producto;
            String sql = "UPDATE Camiseta SET talla = ?, dorsal = ?, numero = ? WHERE ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, c.getTalla());
                pstmt.setString(2, c.getDorsalJugador());
                pstmt.setInt(3, c.getNumeroJugador());
                pstmt.setInt(4, c.getID());
                return pstmt.executeUpdate() > 0;
            }
        } else if (producto instanceof TEntrada) {
            TEntrada e = (TEntrada) producto;
            String sql = "UPDATE Entrada SET fecha = ?, hora = ?, ubicacion = ?, numero_asiento = ?, partido = ? WHERE ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDate(1, new Date(e.getFecha().getTime()));
                pstmt.setString(2, e.getHora());
                pstmt.setString(3, e.getUbicacion());
                pstmt.setString(4, e.getNumeroAsiento());
                pstmt.setString(5, e.getPartido());
                pstmt.setInt(6, e.getID());
                return pstmt.executeUpdate() > 0;
            }
        }
        else if (producto instanceof TJuguete) {
            TJuguete j = (TJuguete) producto;
            String sql = "UPDATE Juguete SET tipo = ?, tamano = ? WHERE ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, j.getTipo());
                pstmt.setString(2, j.getTamano());
                pstmt.setInt(3, j.getID());
                return pstmt.executeUpdate() > 0;
            }
        }
        else if (producto instanceof TPoster) {
            TPoster p = (TPoster) producto;
            String sql = "UPDATE Poster SET tamano = ? WHERE ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, p.getTamano());
                pstmt.setInt(2, p.getID());
                return pstmt.executeUpdate() > 0;
            }
        }
        return false;
    }
	
	public boolean deleteProducto(int idProducto) {
		
		try (Connection conn = BDConexion.getInstance().getConnection()) {
			
			String tipoProducto = null;
	        
	        if (esCamiseta(conn, idProducto)) {
	            tipoProducto = "Camiseta";
	        } else if (esEntrada(conn, idProducto)) {
	            tipoProducto = "Entrada";
	        } else if (esJuguete(conn, idProducto)) {
	            tipoProducto = "Juguete";
	        } else if (esPoster(conn, idProducto)) {
	            tipoProducto = "Poster";
	        }
	        
	        if (tipoProducto == null) {
	            return false;
	        }
	        
	        if (!eliminarDeTabla(conn, tipoProducto, idProducto)) {
	            return false;
	        }

	        if (!eliminarDeProducto(conn, idProducto)) {
	            return false;
	        }
	        
	        return true;
		}catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	private boolean eliminarDeTabla(Connection conn, String tipoProducto, int idProducto) throws SQLException {
	    String sql = "DELETE FROM " + tipoProducto + " WHERE ID = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, idProducto);
	        return pstmt.executeUpdate() > 0;
	    }
	}
	
	private boolean eliminarDeProducto(Connection conn, int idProducto) throws SQLException {
	    String sql = "DELETE FROM Producto WHERE ID = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, idProducto);
	        return pstmt.executeUpdate() > 0;
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
