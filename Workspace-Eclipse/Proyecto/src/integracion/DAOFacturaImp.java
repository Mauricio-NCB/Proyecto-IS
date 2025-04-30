package integracion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOFacturaImp implements DAOFactura {

    @Override
    public List<Object[]> obtenerFacturasPorCliente(int numSocio) {
        List<Object[]> resultados = new ArrayList<>();
        String sql = "SELECT codigo, fecha, hora, importe FROM Factura WHERE cliente = ?";

        try (Connection conn = BDConexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, numSocio);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("codigo");
                fila[1] = rs.getDate("fecha");
                fila[2] = rs.getString("hora");
                fila[3] = rs.getFloat("importe");
                resultados.add(fila);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultados;
    }
}
