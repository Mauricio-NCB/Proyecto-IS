package negocio.sa;

import negocio.dto.TEmpleado;

public interface SAEmpleado {
	TEmpleado loguearEmpleado(String id, String contrasena);
	boolean altaEmpleado(TEmpleado empleado);
}
