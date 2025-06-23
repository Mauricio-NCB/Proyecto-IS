package presentacion;

import java.util.List;

import negocio.dto.TCliente;
import negocio.dto.TDependiente;
import negocio.dto.TFactura;
import negocio.sa.SAFactura;
import negocio.sa.SAFacturaImp;

public class ControladorFactura {

	private static ControladorFactura instancia;
	private SAFactura servicioFactura;

	private ControladorFactura() {
		servicioFactura = new SAFacturaImp();
	}

	public static ControladorFactura getInstance() {
		if (instancia == null)
			instancia = new ControladorFactura();
		return instancia;
	}

	public void nuevaFactura(TFactura factura) throws Exception {
		servicioFactura.crearFactura(factura);
	}
	
	public void modificarFactura(TFactura factura) throws Exception {
		servicioFactura.actualizarFactura(factura);
	}
	
	public void borrarFactura(String codigoFactura) throws Exception {
		servicioFactura.eliminarFactura(codigoFactura);
	}
	
	public TFactura consultarFactura(String codigoFactura) throws Exception {
		return servicioFactura.obtenerFactura(codigoFactura);
	}
	
	public List<TFactura> consultarFacturas() throws Exception {
		return servicioFactura.obtenerFacturas();
	}
}