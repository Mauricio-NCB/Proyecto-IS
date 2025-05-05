package presentacion;

import java.util.List;

import negocio.dto.TDependiente;
import negocio.dto.TFactura;
import negocio.sa.SADependiente;
import negocio.sa.SADependienteImp;

public class ControladorDependiente {
	private static ControladorDependiente instancia;
    private SADependiente servicioDependiente;

    private ControladorDependiente() {
        this.servicioDependiente = new SADependienteImp();
    }

    public static ControladorDependiente getInstance() {
        if (instancia == null) {
            instancia = new ControladorDependiente();
        }
        return instancia;
    }

    public void crearFactura(TFactura factura) {
        boolean exito = servicioDependiente.generarFactura(factura);

        if (exito) {
            System.out.println("La factura se ha generado correctamente");
        } else {
            System.out.println("Error al generar la factura");
        }
    }
    public List<TDependiente> listarDependientes() {
        return servicioDependiente.listarDependientes();
    }

    public void mostrarTodosLosDependientes() {
        System.out.println("\n--- Listado de Dependientes ---");
        List<TDependiente> dependientes = servicioDependiente.listarDependientes();

       if (dependientes == null || dependientes.isEmpty()) {
            System.out.println("No se encontraron dependientes registrados.");
        } else {
            for (TDependiente dependiente : dependientes) {
                System.out.println(dependiente);
            }
        }
    } 

    public void eliminarDependiente(String id) {
        System.out.println("\nIntentando eliminar dependiente con ID: " + id);
        
        if (id == null || id.trim().isEmpty()) {
            System.err.println("Error: El ID del dependiente a eliminar no puede estar vacío.");
            return;
        }
        
        if (servicioDependiente.eliminarDependiente(id)) {
        	 System.out.println("¡dependiente con ID: " + id.trim() + " eliminado correctamente!");
        }
        else {
        	System.out.println("No se encontró un dependiente con el ID: " + id.trim() + " (o ya había sido eliminado).");
        }
    }
}
