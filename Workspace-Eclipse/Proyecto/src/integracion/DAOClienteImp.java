package integracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import negocio.dto.TCliente;

public class DAOClienteImp implements DAOCliente {
    @Override
    public boolean createCliente(TCliente cliente) {
        String sql = "INSERT INTO Cliente (nombre, direccion, correo) VALUES (?, ?, ?)";

        try (Connection conn = BDConexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getDireccion());
            pstmt.setString(3, cliente.getCorreo());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
