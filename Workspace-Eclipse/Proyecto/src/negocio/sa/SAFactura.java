package negocio.sa;

import java.util.List;

import negocio.dto.TFactura;

public interface SAFactura {

	void crearFactura(int idCliente, String idDependiente, List<Object[]> productosConCantidades) throws Exception;
	List<TFactura> listarFacturas();
}
