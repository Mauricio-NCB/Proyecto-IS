package presentacion;

import javax.swing.JOptionPane;

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
			new VentanaDirector().setVisible(true);
		}
		else if (empleado instanceof TDependiente) {
			new VentanaDependiente().setVisible(true);
		}
		
		return true;

	}

	public void registrarEmpleado(String id, String nombre, Float sueldo, String contrasena, String cargo) throws Exception {
			
		TEmpleado nuevoEmpleado;
		
		if ("DIRECTOR".equals(cargo)) {
			nuevoEmpleado = new TDirector(id, nombre, sueldo, HashUtil.hashPassword(contrasena), cargo);
		}
		else{
			nuevoEmpleado = new TDependiente(id, nombre, sueldo, HashUtil.hashPassword(contrasena), 0);
		}

		servicioEmpleado.altaEmpleado(nuevoEmpleado);
    } 
	
    public void eliminarEmpleado(String id) {
        System.out.println("\nIntentando eliminar empleado con ID: " + id);
        
        if (id == null || id.trim().isEmpty()) {
            System.err.println("Error: El ID del empleado a eliminar no puede estar vacío.");
            return;
        }
        
        if (servicioEmpleado.eliminarEmpleado(id)) {
        	 System.out.println("¡Director con ID: " + id.trim() + " eliminado correctamente!");
        }
        else {
        	System.out.println("No se encontró un director con el ID: " + id.trim() + " (o ya había sido eliminado).");
        }
    }
    
    public void actualizarDatos(String id, Float sueldo, String contrasena) throws Exception {
    	System.out.println("\nIntentando actualizar datos para empleado ID: " + id);
        if (id == null || id.trim().isEmpty()) {
            System.err.println("Error: El ID del empleado a actualizar es requerido.");
            return;
        }
        
        if (servicioEmpleado.actualizaDatosEmpleado(id, sueldo, contrasena)) {
        	System.out.println("¡Director con ID: " + id.trim() + " actualizado correctamente!");
        }
        else {
        	System.out.println("No se encontró un director con el ID: " + id.trim() + " (o no se pudo actualizar).");
        }
    }
}
