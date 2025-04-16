package negocio.sa;

import integracion.DAOEmpleado;
import integracion.DAOEmpleadoImp;
import negocio.dto.TEmpleado;
import util.HashUtil;


public class SAEmpleadoImp implements SAEmpleado {
	private DAOEmpleado daoEmpleado = new DAOEmpleadoImp();

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
