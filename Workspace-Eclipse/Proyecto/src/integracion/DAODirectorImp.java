package integracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import negocio.dto.TDirector;

public class DAODirectorImp implements DAODirector {
    @Override
    public boolean createDirector(TDirector director) {
        String sql = "INSERT INTO Director (identificador, nombre, sueldo) VALUES (?, ?, ?)";

        try (Connection conn = BDConexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, director.getIdentificador());
            pstmt.setString(2, director.getNombre());
            pstmt.setFloat(3, director.getSueldo());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
