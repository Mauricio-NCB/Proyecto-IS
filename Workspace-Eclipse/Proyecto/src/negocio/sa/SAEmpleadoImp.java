package negocio.sa;

import integracion.DAOEmpleado;
import integracion.DAOEmpleadoImp;
import negocio.dto.TDependiente;
import negocio.dto.TDirector;
import negocio.dto.TEmpleado;
import util.HashUtil;


public class SAEmpleadoImp implements SAEmpleado {
	private DAOEmpleado daoEmpleado = new DAOEmpleadoImp();

	@Override
	public void altaEmpleado(TEmpleado empleado) throws Exception {
		
		if (daoEmpleado.existeEmpleado(empleado.getIdentificador())) {
			throw new IllegalArgumentException("Ya existe un empleado con el identificador: " + empleado.getIdentificador());
		}

		daoEmpleado.createEmpleado(empleado);
    }

	@Override
	public TEmpleado loguearEmpleado(String id, String contrasena) throws Exception {
		TEmpleado empleado = daoEmpleado.readEmpleado(id);
		String hashContrasena = HashUtil.hashPassword(contrasena);
		
		if (!hashContrasena.equals(empleado.getContrasena())) {
			throw new IllegalArgumentException("No coincide la contrase�a");
		}
		
		return empleado;
	}
	
    public boolean eliminarEmpleado(String id) {
    	return daoEmpleado.eliminar(id);
    }
    
    public boolean actualizaDatosEmpleado(String id, Float sueldo, String contrasena) throws Exception {
    	TEmpleado empleado = daoEmpleado.readEmpleado(id);
  
    	if (empleado == null) {
    		throw new IllegalArgumentException("No existe ningún empleado con identificador: " + id);
    	}
 
    	if (sueldo != null) {empleado.setSueldo(sueldo);}
    	if (!contrasena.trim().isEmpty()) {empleado.setContrasena(HashUtil.hashPassword(contrasena));}
    	return daoEmpleado.actualizarEmpleado(empleado);
    }

}
