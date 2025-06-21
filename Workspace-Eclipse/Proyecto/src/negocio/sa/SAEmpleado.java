package negocio.sa;

import negocio.dto.TEmpleado;
import java.util.List;

public interface SAEmpleado {
	TEmpleado loguearEmpleado(String id, String contrasena) throws Exception;
	boolean altaEmpleado(TEmpleado empleado) throws Exception;
	boolean eliminarEmpleado(String id);
	boolean actualizaDatosEmpleado(String id, Float sueldo, String contrasena) throws Exception;
	List<TEmpleado> mostrarEmpleados();
}
