package integracion;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import negocio.dto.TCliente;
import negocio.dto.TDependiente;
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
	public void createFactura(TFactura factura) {
	    String sql = "INSERT INTO Factura (codigo, fecha, hora, importe, cliente) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = BDConexion.getInstance().getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, factura.getCodigo());
	        ps.setDate(2, Date.valueOf(factura.getFecha()));
	        ps.setTime(3, Time.valueOf(factura.getHora()));
	        ps.setFloat(4, factura.getImporte());

	        ps.setInt(5, factura.getTiene().getNumSocio());

	        ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void updateFactura(TFactura f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFactura(String codigoF) {
		// TODO Auto-generated method stub
		
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
			//LocalDate fecha = rs.getLocalDate("fecha");
			//LocalTime hora = rs.getLocalTime("hora");
			float importe = rs.getFloat("importe");
			String nombreCliente = rs.getString("cliente");
			
			//
			
			//factura = new TFactura(codigo, fecha, hora, importe, nombreCliente);
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
		
		DAOCliente daocliente = new DAOClienteImp();
		List<TCliente> clientes = daocliente.getAllClientes();

		try (Connection conn = BDConexion.getInstance().getConnection();
		     PreparedStatement stmt = conn.prepareStatement(sql);
		     ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				TFactura f = new TFactura();
				f.setCodigo(rs.getString("codigo"));
				f.setFecha(rs.getDate("fecha").toLocalDate());
				f.setHora(rs.getTime("hora").toLocalTime());
				f.setImporte(rs.getFloat("importe"));

				int idCliente = rs.getInt("cliente");

				TCliente cliente = null;
				for (TCliente c : clientes) {
				    if (c.getNumSocio() == idCliente) {
				        cliente = c;
				        break;
				    }
				}

				f.setTiene(cliente);

				lista.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	
}
