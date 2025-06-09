package negocio.sa;

import negocio.dto.TEmpleado;

public interface SAEmpleado {
	TEmpleado loguearEmpleado(String id, String contrasena) throws Exception;
	void altaEmpleado(TEmpleado empleado) throws Exception;
	boolean eliminarEmpleado(String id);
}
