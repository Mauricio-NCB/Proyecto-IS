package integracion;

import java.util.List;
import negocio.dto.TEmpleado;
import negocio.dto.TFactura;

public interface DAOEmpleado {
	TEmpleado readEmpleado(String id) throws Exception;
	boolean createEmpleado(TEmpleado empleado) throws Exception;
	boolean existEmpleado(String id);
	boolean updateEmpleado(TEmpleado empleado);
	boolean deleteEmpleado(String id);
	List<TEmpleado> listEmpleados();
	boolean createFactura(TFactura factura);
}
