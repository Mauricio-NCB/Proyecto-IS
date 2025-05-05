package presentacion;

import java.util.List;

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

	public void nuevaFactura(int idCliente, String idDependiente, List<Object[]> productosConCantidad) throws Exception {
		servicioFactura.crearFactura(idCliente, idDependiente, productosConCantidad);
	}
}