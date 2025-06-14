package integracion;

import java.util.List;

import negocio.dto.TDependiente;
import negocio.dto.TDirector;
import negocio.dto.TEmpleado;

public interface DAOEmpleado {
	TEmpleado readEmpleado(String id) throws Exception;
	void createEmpleado(TEmpleado empleado) throws Exception;
	boolean existeEmpleado(String id);
	boolean actualizarEmpleado(TEmpleado empleado);
	boolean eliminar(String id);
	List<TEmpleado> ListarEmpleados();
}
