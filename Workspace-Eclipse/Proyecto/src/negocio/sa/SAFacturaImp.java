package negocio.sa;

import java.util.List;
import integracion.DAOFactura;
import integracion.DAOFacturaImp;
import negocio.dto.TFactura;

public class SAFacturaImp implements SAFactura {

	DAOFactura daoFactura = new DAOFacturaImp();
	
	@Override
	public void crearFactura(TFactura factura) throws Exception {
		// TODO Auto-generated method stub
		daoFactura.createFactura(factura);
	}

	@Override
	public void actualizarFactura(TFactura factura) throws Exception {
		// TODO Auto-generated method stub
		TFactura f = daoFactura.readFactura(factura.getCodigo());
		
		if (f == null) throw new Exception("No existe ninguna factura con identificador: " + factura.getCodigo());
		if (factura.getFecha() == null) throw new IllegalArgumentException("La fecha no puede estar vacía"); 
		if (factura.getHora() == null) throw new IllegalArgumentException("La hora no puede estar vacía"); 
		if (factura.getImporte() < 0) throw new IllegalArgumentException("El importe no puede ser negativo"); 
		
		daoFactura.updateFactura(factura);
	}

	@Override
	public void eliminarFactura(String codigoFactura) throws Exception {
		// TODO Auto-generated method stub
		TFactura factura = daoFactura.readFactura(codigoFactura);
		
		if (factura == null) {
			throw new Exception("No existe ninguna factura con identificador" + codigoFactura);
		}
		
		daoFactura.deleteFactura(codigoFactura);
	}

	@Override
	public TFactura obtenerFactura(String codigoFactura) throws Exception {
		// TODO Auto-generated method stub
		TFactura factura = daoFactura.readFactura(codigoFactura);

		if (factura == null) {
			throw new Exception("No existe ninguna factura con identificador: " + codigoFactura);
		}
		
		return factura;
	}

	@Override
	public List<TFactura> obtenerFacturas() throws Exception {
		// TODO Auto-generated method stub
		List<TFactura> facturas = daoFactura.readAllFacturas();
		
		if (facturas == null || facturas.isEmpty()) {
			throw new Exception("No se encontraron facturas registradas");
		}
		
		return facturas;
	}
	
}

