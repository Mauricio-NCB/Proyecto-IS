package presentacion;

import java.util.List;

import negocio.dto.TDirector;
import negocio.dto.TCliente;
import negocio.dto.TDependiente;
import negocio.sa.SACliente;
import negocio.sa.SAClienteImp;
import negocio.sa.SADependiente;
import negocio.sa.SADependienteImp;
import negocio.sa.SADirector;
import negocio.sa.SADirectorImp;
import negocio.sa.SAProducto;
import negocio.sa.SAProductoImp;
import util.HashUtil;

public class ControladorDirector { 
    private static ControladorDirector instancia;
    private SADirector servicioDirector;
    private SACliente servicioCliente;
    private SAProducto servicioProducto;
    private SADependiente servicioDependiente;

    private ControladorDirector() {
        this.servicioDirector = new SADirectorImp();
        this.servicioCliente = new SAClienteImp();
        this.servicioProducto = new SAProductoImp();
        this.servicioDependiente = new SADependienteImp();
    }

    public static ControladorDirector getInstance() {
        if (instancia == null) {
            instancia = new ControladorDirector();
        }
        return instancia;
    }

    // Directores
    public void registrarDirector(String id, String nombre, Float sueldo, String contrasena) {
        TDirector nuevoDirector = new TDirector(id, nombre, sueldo, HashUtil.hashPassword(contrasena), "DIRECTOR");
        boolean exito = servicioDirector.altaDirector(nuevoDirector);
        
        if (exito) {
            System.out.println("Director registrado correctamente");
        } else {
            System.out.println("Error al registrar el cliente");
        }
    }

    public void mostrarTodosLosDirectores() {
        System.out.println("\n--- Listado de Directores ---");
        List<TDirector> directores = servicioDirector.mostrarDirectores();

        if (directores == null || directores.isEmpty()) {
            System.out.println("No se encontraron directores registrados.");
        } else {
            for (TDirector director : directores) {
                System.out.println(director);
            }
        }
    }
    
    public void eliminarDirector(String id) {
        System.out.println("\nIntentando eliminar director con ID: " + id);
        
        if (id == null || id.trim().isEmpty()) {
            System.err.println("Error: El ID del director a eliminar no puede estar vacío.");
            return;
        }
        
        if (servicioDirector.eliminarDirector(id)) {
        	 System.out.println("¡Director con ID: " + id.trim() + " eliminado correctamente!");
        }
        else {
        	System.out.println("No se encontró un director con el ID: " + id.trim() + " (o ya había sido eliminado).");
        }
    }
    
    public void actualizarDatos(String id, Float sueldo, String contrasena) {
    	System.out.println("\nIntentando actualizar datos para empleado ID: " + id);
        if (id == null || id.trim().isEmpty()) {
            System.err.println("Error: El ID del empleado a actualizar es requerido.");
            return;
        }
        
        if (servicioDirector.actualizaDatosDirector(id, sueldo, contrasena)) {
        	System.out.println("¡Director con ID: " + id.trim() + " actualizado correctamente!");
        }
        else {
        	System.out.println("No se encontró un director con el ID: " + id.trim() + " (o no se pudo actualizar).");
        }
    }
    
    // Cliente
    public void mostrarTodosLosClientes() {
        System.out.println("\n--- Listado de Clientes ---");
        List<TCliente> clientes = servicioCliente.getAllClientes();

        if (clientes == null || clientes.isEmpty()) {
            System.out.println("No se encontraron clientes registrados.");
        } else {
            for (TCliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    // Dependiente 
    public void mostrarTodosLosDependientes() {
        System.out.println("\n--- Listado de Dependientes ---");
        List<TDependiente> dependientes = servicioDependiente.listarDependientes();

        if (dependientes == null || dependientes.isEmpty()) {
            System.out.println("No se encontraron directores registrados.");
        } else {
            for (TDependiente dependiente : dependientes) {
                System.out.println(dependiente);
            }
        }
    }



}