package presentacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import negocio.dto.TCamiseta;
import negocio.dto.TEntrada;
import negocio.dto.TJuguete;
import negocio.dto.TPoster;
import negocio.dto.TProducto;
import negocio.sa.SAProducto;
import negocio.sa.SAProductoImp;

public class ControladorProducto {

	private static ControladorProducto instancia;
	private SAProducto prod;
	
	private ControladorProducto() {
		this.prod = new SAProductoImp();
	}
	
	public static ControladorProducto getInstance() {
		if (instancia == null) {
			instancia = new ControladorProducto();
		}
		return instancia;
	}
	
	public boolean nuevoProducto(TProducto producto) {
		boolean exito;
		try {
			prod.altaProducto(producto);
			System.out.println("Exito");
			new VentanaNuevoProd().setVisible(true);
			exito = true;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			exito = false;
		}
		return exito;
	}

    public boolean deleteProducto(String idStr) {
    	boolean exito;
        int id = Integer.parseInt(idStr);
		try{
			prod.deleteProducto(id);
			System.out.println("Exito");
			exito = true;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			exito = false;
		}
		return exito;
	}
	
	public List<TProducto> listarProductos() {
		try {
			return prod.listarProductos();		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	    // Productos
    public void registrarPoster(String nombre, String precioStr, String stockStr, String tamano) {
        System.out.println("\nIntentando registrar nuevo Poster...");
        try {
            if (nombre == null || nombre.trim().isEmpty() || precioStr.trim().isEmpty() || stockStr.trim().isEmpty() || tamano == null || tamano.trim().isEmpty()){
                System.err.println("Datos inválidos para el poster.");
            }
            else{
                float precio = Float.parseFloat(precioStr);
                int stock = Integer.parseInt(stockStr);
    
    
                TPoster nuevoPoster = new TPoster(nombre.trim(), precio, stock, tamano.trim());
                
                try{
                	prod.altaProducto(nuevoPoster); 
                	System.out.println("¡Poster registrado con éxito! ID asignado: " + nuevoPoster.getID());
                }
                catch (Exception e) {
                	System.out.println("El registro del poster no tuvo éxito (sin error específico).");
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Formato de precio o stock inválido.");
        } catch (Exception e) {
            System.err.println("Error inesperado al registrar poster: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void actualizarPoster(String idStr, String nombre, String precioStr, String stockStr, String tamano) {
        System.out.println("\nIntentando actualizar Poster...");
        try {
            if (idStr.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || precioStr.trim().isEmpty() || stockStr.trim().isEmpty() || tamano == null || tamano.trim().isEmpty()){
                System.err.println("Datos inválidos para el poster.");
            }
            else{
                float precio = Float.parseFloat(precioStr);
                int stock = Integer.parseInt(stockStr);
                int id = Integer.parseInt(idStr);
    
                TPoster nuevoPoster = new TPoster(id, nombre.trim(), precio, stock, tamano.trim());
                
                try{
                	prod.updateProducto(nuevoPoster); 
                	 System.out.println("¡Poster registrado con éxito! ID asignado: " + nuevoPoster.getID());
                }
                catch(Exception e) {
                	System.out.println(e.getMessage());
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Formato de precio o stock inválido.");
        } catch (Exception e) {
            System.err.println("Error inesperado al registrar poster: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void registrarCamiseta(String nombre, Float precio, Integer stock, Integer talla, String dorsal, Integer numJugador) throws Exception {
    	
        TCamiseta nuevaCamiseta = new TCamiseta(nombre, precio, stock, talla, dorsal, numJugador);
        prod.altaProducto(nuevaCamiseta); 
    }

    public void actualizarCamiseta(Integer id, String nombre, Float precio, Integer stock, Integer talla, String dorsal, Integer numJugador) throws Exception {

		TCamiseta nuevaCamiseta = new TCamiseta(id, nombre, precio, stock, talla, dorsal, numJugador);
        prod.updateProducto(nuevaCamiseta); 
    }

    public void registrarEntrada(String nombre, Float precio, Integer stock, Date fecha, String hora, 
                                String ubicacion, String numeroAsiento, String partido) throws Exception {
        
        TEntrada nuevaEntrada = new TEntrada(nombre, precio, stock, fecha, hora, ubicacion, numeroAsiento, partido);
        prod.altaProducto(nuevaEntrada); 

    }

    public void actualizarEntrada(Integer id, String nombre, Float precio, Integer stock, Date fecha, String hora, 
                                String ubicacion, String numeroAsiento, String partido) throws Exception {

        TEntrada nuevaEntrada = new TEntrada(id, nombre, precio, stock, fecha, hora, ubicacion, numeroAsiento, partido);
        prod.updateProducto(nuevaEntrada); 
    }
    

    public void registrarJuguete(String nombre, String precioStr, String stockStr, String tipo, String tamano) {
        System.out.println("\nIntentando registrar nuevo Juguete...");
        try {
            if (nombre == null || nombre.trim().isEmpty() || precioStr.trim().isEmpty() || stockStr.trim().isEmpty() || tipo.trim().isEmpty() || tamano.trim().isEmpty()){
                System.err.println("Datos inválidos para el juguete.");
            }
            else{
                float precio = Float.parseFloat(precioStr);
                int stock = Integer.parseInt(stockStr);
    
    
                TJuguete nuevoJuguete = new TJuguete(nombre.trim(), precio, stock, tipo.trim(),tamano.trim());
               try {
            	   prod.altaProducto(nuevoJuguete); 
            	   System.out.println("¡Juguete registrado con éxito! ID asignado: " + nuevoJuguete.getID());
               }
               catch(Exception e) {
            	   System.out.println("El registro del juguete no tuvo éxito (sin error específico).");
               }
    
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Formato de precio o stock inválido.");
        } catch (Exception e) {
            System.err.println("Error inesperado al registrar juguete: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void actualizarJuguete(String idStr, String nombre, String precioStr, String stockStr, String tipo, String tamano) {
        System.out.println("\nIntentando registrar nuevo Juguete...");
        try {
            if (idStr.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || precioStr.trim().isEmpty() || stockStr.trim().isEmpty() || tipo.trim().isEmpty() || tamano.trim().isEmpty()){
                System.err.println("Datos inválidos para el juguete.");
            }
            else{
                float precio = Float.parseFloat(precioStr);
                int stock = Integer.parseInt(stockStr);
                int id = Integer.parseInt(idStr);
    
                TJuguete nuevoJuguete = new TJuguete(id, nombre.trim(), precio, stock, tipo.trim(),tamano.trim());
                try {
                	prod.updateProducto(nuevoJuguete); 
                	System.out.println("¡Juguete registrado con éxito! ID asignado: " + nuevoJuguete.getID());
                }
                catch(Exception e) {
                	System.out.println("El registro del juguete no tuvo éxito (sin error específico).");
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Formato de precio o stock inválido.");
        } catch (Exception e) {
            System.err.println("Error inesperado al registrar juguete: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void mostrarTodosLosProductos() {
        System.out.println("\n--- Listado de Productos ---");
        List<TProducto> productos = new ArrayList<>();
        try {
        	 productos = prod.listarProductos();
        }
        catch (Exception e) {
        	System.out.println(e.getMessage());
        }

        if (productos == null || productos.isEmpty()) {
            System.out.println("No se encontraron productos registrados.");
        } else {
            for (TProducto producto : productos) {
                System.out.println(producto);
            }
        }
    }
    
    public void eliminarProducto(String idStr) {
        System.out.println("\nIntentando eliminar producto con ID: " + idStr);
        
        if (idStr == null || idStr.trim().isEmpty()) {
            System.err.println("Error: El ID del producto a eliminar no puede estar vacío.");
            return;
        }
        
        int id = Integer.parseInt(idStr);
        
        try {
        	prod.deleteProducto(id);
        	System.out.println("¡Producto con ID: " + idStr + " eliminado correctamente!");
        }
        catch (Exception e) {
        	System.out.println("No se encontró un producto con el ID: " + idStr + " (o ya había sido eliminado).");
        }
    }
}