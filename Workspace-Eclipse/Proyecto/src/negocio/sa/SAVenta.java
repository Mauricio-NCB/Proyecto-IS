package negocio.sa;

import java.util.List;

import negocio.dto.TLineaVenta;
import negocio.dto.TVenta;

public interface SAVenta {
	void abrirVenta(int idCliente, String idDependiente, List<TLineaVenta> listaProductos) throws Exception;
	void cerrarVenta(TVenta venta) throws Exception;
	void añadirProducto(String idVenta, TLineaVenta productos) throws Exception;
	TVenta obtenerVenta(String id) throws Exception;
	List<TVenta> obtenerventas();
}
