package negocio.sa;

import java.util.List;

import integracion.DAOEmpleado;
import integracion.DAOEmpleadoImp;
import negocio.dto.TEmpleado;
import util.HashUtil;


public class SAEmpleadoImp implements SAEmpleado {
	private DAOEmpleado daoEmpleado = new DAOEmpleadoImp();

	@Override
	public boolean altaEmpleado(TEmpleado empleado) throws Exception {
		
		if (daoEmpleado.existEmpleado(empleado.getIdentificador())) {
			throw new Exception("Ya existe un empleado con el identificador: " + empleado.getIdentificador());
		}

		return daoEmpleado.createEmpleado(empleado);
    }

	@Override
	public TEmpleado loguearEmpleado(String id, String contrasena) throws Exception {
		TEmpleado empleado = daoEmpleado.readEmpleado(id);
		if (empleado == null) {
			throw new Exception("No existe un empleado con el ID: " + id);
		}
		String hashContrasena = HashUtil.hashPassword(contrasena);
		if (!hashContrasena.equals(empleado.getContrasena())) {
			throw new Exception("No coincide la contraseña");
		}
		
		return empleado;
	}
	
	public boolean eliminarEmpleado(String id) throws Exception {
		if (!daoEmpleado.existEmpleado(id)){
			throw new Exception("No existe ningún empleado con identificador: " + id);
		}
		return daoEmpleado.deleteEmpleado(id);
	}
    
    public boolean actualizaDatosEmpleado(String id, Float sueldo, String contrasena) throws Exception {
    	TEmpleado empleado = daoEmpleado.readEmpleado(id);
  
    	if (empleado == null) {
    		throw new Exception("No existe ningún empleado con identificador: " + id);
    	}
 
    	if (sueldo != null) {empleado.setSueldo(sueldo);}
    	if (!contrasena.trim().isEmpty()) {empleado.setContrasena(HashUtil.hashPassword(contrasena));}
    	return daoEmpleado.updateEmpleado(empleado);
    }

	public List<TEmpleado> mostrarEmpleados() throws Exception {
		List<TEmpleado> empleados = daoEmpleado.listEmpleados();
		if (empleados == null || empleados.isEmpty()) {
			throw new Exception("No se encontraron empleados registrados.");
		} 

		return empleados;
	}

	public TEmpleado obtenerEmpleado(String id) throws Exception {

		if (!daoEmpleado.existEmpleado(id)) {
			throw new Exception("No existe un empleado con el ID: " + id);
		}
		return daoEmpleado.readEmpleado(id);
	}
}
