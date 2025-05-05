package integracion;

import java.util.List;

import negocio.dto.TFactura;

public interface DAOFactura {
    List<Object[]> obtenerFacturasPorCliente(int numSocio);
	void insert(TFactura f);
	List<TFactura> listarFacturas();
}
