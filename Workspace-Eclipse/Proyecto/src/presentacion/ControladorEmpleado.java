package presentacion;

import java.util.List;

import negocio.dto.TDependiente;
import negocio.dto.TDirector;
import negocio.dto.TEmpleado;
import negocio.sa.SAEmpleado;
import negocio.sa.SAEmpleadoImp;
import util.HashUtil;

public class ControladorEmpleado {
	private static ControladorEmpleado instancia;
	private SAEmpleado servicioEmpleado;
	
	private ControladorEmpleado() {
		this.servicioEmpleado = new SAEmpleadoImp();
	}
	
	public static ControladorEmpleado getInstance() {
		if (instancia == null) {
			instancia = new ControladorEmpleado();
		}
		return instancia;
	}
	
	public boolean loguearEmpleado(String id, String contrasena) throws Exception {
		TEmpleado empleado = servicioEmpleado.loguearEmpleado(id, contrasena);
			
		if(empleado instanceof TDirector) {
			new VentanaEmpleado().setVisible(true);
		}
		else if (empleado instanceof TDependiente) {
			new VentanaDependiente().setVisible(true);
		}
		
		return true;

	}

	public boolean registrarEmpleado(String id, String nombre, Float sueldo, String contrasena, String cargo) throws Exception {
			
		TEmpleado nuevoEmpleado;
		
		if ("DIRECTOR".equals(cargo)) {
			nuevoEmpleado = new TDirector(id, nombre, sueldo, HashUtil.hashPassword(contrasena), cargo);
		}
		else{
			nuevoEmpleado = new TDependiente(id, nombre, sueldo, HashUtil.hashPassword(contrasena), 0);
		}

		return servicioEmpleado.altaEmpleado(nuevoEmpleado);
    } 
	
    public boolean eliminarEmpleado(String id) throws Exception {
        return servicioEmpleado.eliminarEmpleado(id);
    }
    
    public boolean actualizarDatos(String id, Float sueldo, String contrasena) throws Exception {
        return servicioEmpleado.actualizaDatosEmpleado(id, sueldo, contrasena);
    }

	public List<TEmpleado> mostrarEmpleados() throws Exception {
        List<TEmpleado> empleados = servicioEmpleado.mostrarEmpleados();
        return empleados;
    }

	public TEmpleado buscarEmpleado(String id) throws Exception {
		return servicioEmpleado.obtenerEmpleado(id);
	}
}
