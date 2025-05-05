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
	
	public boolean loguearEmpleado(String id, String contrasena) {
		TEmpleado empleado = servicioEmpleado.loguearEmpleado(id, contrasena);
		if (empleado != null) {
			System.out.println("Login exitoso");
			if(empleado instanceof TDirector) {
				new VentanaDirector().setVisible(true);
			}
			else if (empleado instanceof TDependiente) {
				new VentanaDependiente().setVisible(true);
			}
			return true;
		}
		else {
			System.out.println("ERROR al loguear");
			JOptionPane.showMessageDialog(null, "ID o contraseña incorrectos");
			return false;
		}
	}

	public void registrarEmpleado(String id, String nombre, Float sueldo, String contrasena, String cargo) {
		boolean exito = false;
		if (cargo == "DIRECTOR"){
			TDirector nuevoDirector = new TDirector(id, nombre, sueldo, HashUtil.hashPassword(contrasena), cargo);
			exito = servicioEmpleado.altaEmpleado(nuevoDirector);
		}
		else{
			TDependiente nuevoDependiente = new TDependiente(id, nombre, sueldo, contrasena, 0);
			exito = servicioEmpleado.altaEmpleado(nuevoDependiente);
		}

		if (exito) {
			System.out.println(nombre +" registrado correctamente");
		} else {
			System.out.println("Error al registrar empleado");
		}
    } 
}
