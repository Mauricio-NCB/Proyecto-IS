package integracion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<TCliente> getAllClientes() {
        List<TCliente> lista = new ArrayList<>();
        String sql = "SELECT num_socio, nombre, direccion, correo FROM Cliente";

        try (Connection conn = BDConexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                TCliente cliente = new TCliente(
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("correo")
                );
                cliente.setNumSocio(rs.getInt("num_socio")); 
                lista.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
