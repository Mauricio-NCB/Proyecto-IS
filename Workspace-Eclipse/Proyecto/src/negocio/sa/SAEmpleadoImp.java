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
	public boolean altaEmpleado(TEmpleado empleado) {
		if (empleado == null || empleado.getIdentificador() == null || empleado.getIdentificador().trim().isEmpty()) {
			System.err.println("Datos del empleado inválidos o identificador vacío.");
		}
		
		if (daoEmpleado.existeEmpleado(empleado.getIdentificador())) {
			System.err.println("Ya existe un empleado con el identificador: " + empleado.getIdentificador());
		}

		boolean exito = false;

		if (empleado instanceof TDirector){
			TDirector cargoEmpleado = (TDirector)empleado;
			if (cargoEmpleado.getCargo() == null || cargoEmpleado.getCargo().trim().isEmpty()){
				System.err.println("El cargo del empleado no puede ser vacío.");
			}
			exito = daoEmpleado.createEmpleado(cargoEmpleado);
		}
		else {
			TDependiente cargoEmpleado = (TDependiente)empleado;
			exito = daoEmpleado.createEmpleado(cargoEmpleado);
		}

		return exito;
    }

	@Override
	public TEmpleado loguearEmpleado(String id, String contrasena) {
		TEmpleado empleado = daoEmpleado.readEmpleado(id);
		if (empleado != null) {
			
			String hashContrasena = HashUtil.hashPassword(contrasena);
			
			if (hashContrasena.equals(empleado.getContrasena())) {
				return empleado;
			}
		}
		return null;
	}

}
