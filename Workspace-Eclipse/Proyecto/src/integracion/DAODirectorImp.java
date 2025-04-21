package integracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negocio.dto.TDirector;

public class DAODirectorImp implements DAODirector {
	
    private final Connection conn;

    public DAODirectorImp(Connection conn) {
        this.conn = conn;
    }
    
    private TDirector mapDir(ResultSet rs) throws SQLException {
        String id = rs.getString("identificador");
        String nombre = rs.getString("nombre");
        float sueldo = rs.getFloat("sueldo");
        String cargo = rs.getString("cargo");
        String contrasena = rs.getString("contrasena");
        return new TDirector(id, nombre, sueldo, contrasena, cargo);
    }
    
    @Override
    public boolean createDirector(TDirector director) {
        String sql = "INSERT INTO Director (identificador, nombre, sueldo, contrasena, cargo) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, director.getIdentificador());
            pstmt.setString(2, director.getNombre());
            pstmt.setFloat(3, director.getSueldo());
            pstmt.setString(3, director.getContrasena());
            pstmt.setString(5, director.getCargo());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public TDirector obtenerPorId(String id) {
        String sql = "SELECT identificador, nombre, sueldo, contrasena, cargo FROM Directores WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapDir(rs);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<TDirector> obtenerTodos() {
        String sql = "SELECT identificador, nombre, sueldo, contrasena cargo FROM Directores";
        List<TDirector> lista = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);
        		ResultSet rs = ps.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapDir(rs));
            }
            return lista;
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public void actualizar(TDirector director) {
        String sql = "UPDATE Directores SET nombre = ?, sueldo = ?, cargo = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, director.getIdentificador());
            ps.setString(2, director.getNombre());
            ps.setFloat(3, director.getSueldo());
            ps.setString(4, director.getContrasena());
            ps.setString(5, director.getCargo());
            ps.executeUpdate();
        } catch (SQLException e) {   
        	e.printStackTrace();
        }
    }
    
    @Override
    public void eliminar(String id) {
        String sql = "DELETE FROM Directores WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
}

