package negocio.sa;

import java.util.List;

public interface SAFactura {

	void crearFactura(int idCliente, String idDependiente, List<Object[]> productosConCantidades) throws Exception;
	List<TFactura> listarFacturas();
}
