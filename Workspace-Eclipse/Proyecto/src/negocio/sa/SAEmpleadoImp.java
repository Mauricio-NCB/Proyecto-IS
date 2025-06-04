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

		if (empleado instanceof TDirector){
			TDirector dir = (TDirector) empleado;
			
			daoEmpleado.createEmpleado(dir);
		}
		else {
			TDependiente emp = (TDependiente) empleado;
			
			daoEmpleado.createEmpleado(emp);
		}
    }

	@Override
	public TEmpleado loguearEmpleado(String id, String contrasena) throws Exception {
		TEmpleado empleado = daoEmpleado.readEmpleado(id);
		String hashContrasena = HashUtil.hashPassword(contrasena);
		
		if (!hashContrasena.equals(empleado.getContrasena())) {
			throw new IllegalArgumentException("No coincide la contraseña");
		}
		
		return empleado;
	}

}
