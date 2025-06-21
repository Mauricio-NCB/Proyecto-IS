package integracion;

import java.util.List;

import negocio.dto.TFactura;

public interface DAOFactura {
    List<Object[]> obtenerFacturasPorCliente(int numSocio);
	void createFactura(TFactura f);
	void updateFactura(TFactura f);
	void deleteFactura(String codigoF);
	TFactura readFactura(String codigoF) throws Exception;
	List<TFactura> readAllFacturas();
	
}
