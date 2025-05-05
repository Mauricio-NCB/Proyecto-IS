package integracion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import negocio.dto.TFactura;

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

	@Override
	public void insert(TFactura factura) {
	    String sql = "INSERT INTO Factura (codigo, fecha, hora, importe, cliente, dependiente) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = BDConexion.getInstance().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, factura.getCodigo());
	        ps.setDate(2, Date.valueOf(factura.getFecha()));
	        ps.setTime(3, Time.valueOf(factura.getHora()));
	        ps.setFloat(4, factura.getImporte());

	        // Aquí extraemos los IDs de los objetos
	        ps.setInt(5, factura.getTiene().getNumSocio());
	        ps.setString(6, factura.getDependientes().getIdentificador());

	        ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
