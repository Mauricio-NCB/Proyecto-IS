package integracion;



import java.util.List;

import negocio.dto.TDependiente;
import negocio.dto.TFactura;


public interface DAODependiente {
	boolean crearFactura(TFactura factura);
	List<TDependiente> listarDependientes();
	boolean eliminar(String id);
}
