package negocio.sa;

import java.util.List;

import negocio.dto.TFactura;

public interface SAFactura {

	void crearFactura(int idCliente, String idDependiente, List<Object[]> productosConCantidades) throws Exception;
	void actualizarFactura(TFactura factura) throws Exception;
	void eliminarFactura(String codigoFactura) throws Exception;
	TFactura obtenerFactura(String codigoFactura) throws Exception;
	List<TFactura> obtenerFacturas();
	
}
