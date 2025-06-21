package integracion;

import java.util.List;
import negocio.dto.TEmpleado;
import negocio.dto.TFactura;

public interface DAOEmpleado {
	TEmpleado readEmpleado(String id) throws Exception;
	boolean createEmpleado(TEmpleado empleado) throws Exception;
	boolean existeEmpleado(String id);
	boolean actualizarEmpleado(TEmpleado empleado);
	boolean eliminar(String id);
	List<TEmpleado> ListarEmpleados();
	boolean crearFactura(TFactura factura);
}
