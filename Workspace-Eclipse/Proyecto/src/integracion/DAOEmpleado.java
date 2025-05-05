package integracion;

import negocio.dto.TEmpleado;

public interface DAOEmpleado {
	TEmpleado readEmpleado(String id);
	boolean createEmpleado(TEmpleado empleado);
	boolean existeEmpleado(String id);
}
