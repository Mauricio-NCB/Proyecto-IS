package integracion;



import java.util.List;

import negocio.dto.Factura;
import negocio.dto.TDependiente;


public interface DAODependiente {
	boolean crearFactura(Factura factura);
	List<TDependiente> listarDependientes();
}
