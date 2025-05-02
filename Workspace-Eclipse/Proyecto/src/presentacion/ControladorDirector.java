package presentacion;

import java.util.List;

import negocio.dto.TDirector;
import negocio.dto.TPoster;
import negocio.dto.TProducto;
import negocio.dto.Venta;
import negocio.dto.TCliente;
import negocio.sa.SACliente;
import negocio.sa.SAClienteImp;
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

    private ControladorDirector() {
        this.servicioDirector = new SADirectorImp();
        this.servicioCliente = new SAClienteImp();
        this.servicioProducto = new SAProductoImp();
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

    // Ventas
    /*public void mostrarTodasLasVentas() {
        System.out.println("\n--- Listado de Ventas ---");
        List<Venta> ventas = servicioVenta.getAllVentas();

        if (ventas == null || ventas.isEmpty()) {
            System.out.println("No se encontraron ventas registrados.");
        } else {
            for (TCliente venta : ventas) {
                System.out.println(venta);
            }
        }
    }*/

    // Productos

    // Lo mismo para diferentes productos... A terminar cuando todo este listo
    /*public void registrarPoster(String nombre, String precioStr, String stockStr, String tamano) {
        System.out.println("\nIntentando registrar nuevo Poster...");
        try {
            // Validación y conversión
            float precio = Float.parseFloat(precioStr);
            int stock = Integer.parseInt(stockStr);
            if (nombre == null || nombre.trim().isEmpty() || precio < 0 || stock < 0 || tamano == null || tamano.trim().isEmpty()){
                System.err.println("Datos inválidos para el poster.");
            }

            TPoster nuevoPoster = new TPoster(nombre.trim(), precio, stock, tamano.trim());
            boolean exito = servicioProducto.altaProducto(nuevoPoster); 

            if (exito) {
                System.out.println("¡Poster registrado con éxito! ID asignado: " + nuevoPoster.getID());
            } else {
                System.out.println("El registro del poster no tuvo éxito (sin error específico).");
            }

        } catch (NumberFormatException e) {
            System.err.println("Error: Formato de precio o stock inválido.");
        } catch (Exception e) {
            System.err.println("Error inesperado al registrar poster: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void mostrarTodosLosProductos() {
        System.out.println("\n--- Listado de Productos ---");
        List<TProducto> productos = servicioProducto.listarProductos();

        if (productos == null || productos.isEmpty()) {
            System.out.println("No se encontraron productos registrados.");
        } else {
            for (TProducto producto : productos) {
                System.out.println(producto);
            }
        }
    }
    
    /*public void eliminarProducto(String id) {
        System.out.println("\nIntentando eliminar producto con ID: " + id);
        
        if (id == null || id.trim().isEmpty()) {
            System.err.println("Error: El ID del producto a eliminar no puede estar vacío.");
            return;
        }
        
        if (servicioProducto.eliminarProducto(id)) {
        	 System.out.println("¡Producto con ID: " + id.trim() + " eliminado correctamente!");
        }
        else {
        	System.out.println("No se encontró un producto con el ID: " + id.trim() + " (o ya había sido eliminado).");
        }
    }*/
    
    /*public void actualizarProducto(String id, Float sueldo, String contrasena) {
    	System.out.println("\nIntentando actualizar datos para producto ID: " + id);
        if (id == null || id.trim().isEmpty()) {
            System.err.println("Error: El ID del Producto a actualizar es requerido.");
            return;
        }
        
        if (servicioDirector.actualizaDatosProducto(id, sueldo, contrasena)) {
        	System.out.println("¡Producto con ID: " + id.trim() + " actualizado correctamente!");
        }
        else {
        	System.out.println("No se encontró un producto con el ID: " + id.trim() + " (o no se pudo actualizar).");
        }
    }*/


    
}