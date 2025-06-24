package integracion;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import negocio.dto.TFactura;

public class DAOFacturaImp implements DAOFactura {

    @Override
    public List<TFactura> obtenerFacturasPorCliente(int numSocio) throws Exception {
        List<TFactura> resultados = new ArrayList<>();
        String sql = "SELECT codigo, fecha, hora, importe FROM Factura WHERE cliente = ?";

        try (Connection conn = BDConexion.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, numSocio);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TFactura fila = new TFactura(
                    rs.getString("codigo"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getTime("hora").toLocalTime(),
                    rs.getFloat("importe")
                );
                resultados.add(fila);
            }

        } catch (SQLException e) {
        	throw new Exception("Error al crear factura en la base de datos: " + e.getMessage(), e);
        }

        return resultados;
    }

	@Override
	public void createFactura(TFactura factura) throws Exception {
	    String sql = "INSERT INTO Factura (codigo, fecha, hora, importe) VALUES (?, ?, ?, ?)";

	    try (Connection conn = BDConexion.getInstance().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, factura.getCodigo());
	        ps.setDate(2, Date.valueOf(factura.getFecha()));
	        ps.setTime(3, Time.valueOf(factura.getHora()));
	        ps.setFloat(4, factura.getImporte());

	        ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void updateFactura(TFactura f) {
		// TODO Auto-generated method stub
		String sql = "UPDATE * FROM Factura WHERE codigo = ?, fecha = ?, hora = ?, importe = ?";
		
		try (Connection conn = BDConexion.getInstance().getConnection()) {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, f.getCodigo());
	        pstmt.setDate(2, Date.valueOf(f.getFecha()));
	        pstmt.setTime(3, Time.valueOf(f.getHora()));
	        pstmt.setFloat(4, f.getImporte());

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void deleteFactura(String codigoF) {
		// TODO Auto-generated method stub
		String sql = "DELETE * FROM Factura WHERE codigo = ?";
		
		try (Connection conn = BDConexion.getInstance().getConnection()) {
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, codigoF);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public TFactura readFactura(String codigoF) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Factura WHERE codigo = ?";
		TFactura factura = null;
		
		try (Connection conn = BDConexion.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, codigoF);
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.next()) throw new SQLException("No se pudo obtener la factura en la tabla Factura");
			
			String codigo = rs.getString("codigo");
			LocalDate fecha = rs.getDate("fecha").toLocalDate();
			LocalTime hora = rs.getTime("hora").toLocalTime();
			float importe = rs.getFloat("importe");		
			
			factura = new TFactura(codigo, fecha, hora, importe);
		}
		catch(SQLException e) {
            throw new Exception("Error al leer factura en la base de datos: " + e.getMessage(), e);
		}
		
		return factura;
	}

	@Override
	public List<TFactura> readAllFacturas() {
		List<TFactura> lista = new ArrayList<>();

		String sql = "SELECT * FROM Factura";
		try (Connection conn = BDConexion.getInstance().getConnection();
		     PreparedStatement stmt = conn.prepareStatement(sql);
		     ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				
				String codigo = rs.getString("codigo");
				LocalDate fecha = rs.getDate("fecha").toLocalDate();
				LocalTime hora = rs.getTime("hora").toLocalTime();
				float importe = rs.getFloat("importe");
				
				TFactura f = new TFactura(codigo, fecha, hora, importe);

				lista.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	
}
