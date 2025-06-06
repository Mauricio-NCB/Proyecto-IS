package presentacion;

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
		if(prod.altaProducto(producto)) {
			System.out.println("Exito");
			new VentanaNuevoProd().setVisible(true);
			return true;
		}
		else {
			System.out.println("Se ha producido un error al crear el producto");
			return false;
		}
	}

    public boolean deleteProducto(String idStr) {
        int id = Integer.parseInt(idStr);
		if(prod.deleteProducto(id)) {
			System.out.println("Exito");
			return true;
		}
		else {
			System.out.println("Se ha producido un error al crear el producto");
			return false;
		}
	}
	
	public List<TProducto> listarProductos() {
		return prod.listarProductos();		
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
                boolean exito = prod.altaProducto(nuevoPoster); 
    
                if (exito) {
                    System.out.println("¡Poster registrado con éxito! ID asignado: " + nuevoPoster.getID());
                } else {
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
                boolean exito = prod.updateProducto(nuevoPoster); 
    
                if (exito) {
                    System.out.println("¡Poster registrado con éxito! ID asignado: " + nuevoPoster.getID());
                } else {
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

    public void registrarCamiseta(String nombre, Float precio, Integer stock, Integer talla, String dorsal, Integer numJugador) throws Exception {
    	
        TCamiseta nuevaCamiseta = new TCamiseta(nombre, precio, stock, talla, dorsal, numJugador);
        prod.altaProducto(nuevaCamiseta); 
    }

    public void actualizarCamiseta(String idStr, String nombre, String precioStr, String stockStr, String tallaStr, String dorsal, String numJugadorStr) {
        System.out.println("\nIntentando actualizar nueva Camiseta...");
        try {
            if (idStr.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || precioStr.trim().isEmpty() || stockStr.trim().isEmpty() || tallaStr.trim().isEmpty() || dorsal.trim().isEmpty() || numJugadorStr.trim().isEmpty()){
                System.err.println("Datos inválidos para la camiseta.");
            }
            else{
                float precio = Float.parseFloat(precioStr);
                int stock = Integer.parseInt(stockStr);
                int talla = Integer.parseInt(tallaStr);
                int numJugador = Integer.parseInt(numJugadorStr);
                int id = Integer.parseInt(idStr);
    
                TCamiseta nuevaCamiseta = new TCamiseta(id, nombre.trim(), precio, stock, talla, dorsal, numJugador);
                boolean exito = prod.updateProducto(nuevaCamiseta); 
    
                if (exito) {
                    System.out.println("¡Camiseta registrada con éxito! ID asignado: " + nuevaCamiseta.getID());
                } else {
                    System.out.println("El registro de la camiseta no tuvo éxito (sin error específico).");
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Formato de precio, talla, número o stock inválido.");
        } catch (Exception e) {
            System.err.println("Error inesperado al registrar camiseta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void registrarEntrada(String nombre, String precioStr, String stockStr, Date fecha, String hora, 
                                String ubicacion, String numeroAsiento, String partido) {
        System.out.println("\nIntentando registrar nueva Entrada...");
        try {
            if (nombre == null || nombre.trim().isEmpty() || precioStr.trim().isEmpty() || stockStr.trim().isEmpty() || hora.trim().isEmpty() 
                || ubicacion.trim().isEmpty() || numeroAsiento.trim().isEmpty() || partido.trim().isEmpty()){
                System.err.println("Datos inválidos para la entrada.");
            }
            else{
                float precio = Float.parseFloat(precioStr);
                int stock = Integer.parseInt(stockStr);

                TEntrada nuevaEntrada = new TEntrada(nombre.trim(), precio, stock, fecha, hora.trim(), ubicacion.trim(), numeroAsiento.trim(), partido.trim());
                boolean exito = prod.altaProducto(nuevaEntrada); 
    
                if (exito) {
                    System.out.println("¡Entrada registrada con éxito! ID asignado: " + nuevaEntrada.getID());
                } else {
                    System.out.println("El registro de la entrada no tuvo éxito (sin error específico).");
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Formato de precio o stock inválido.");
        } catch (Exception e) {
            System.err.println("Error inesperado al registrar entrada: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void actualizarEntrada(String idStr, String nombre, String precioStr, String stockStr, Date fecha, String hora, 
                                String ubicacion, String numeroAsiento, String partido) {
        System.out.println("\nIntentando actualizar nueva entrada...");
        try {
            if (idStr.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || precioStr.trim().isEmpty() || stockStr.trim().isEmpty() || hora.trim().isEmpty() 
                || ubicacion.trim().isEmpty() || numeroAsiento.trim().isEmpty() || partido.trim().isEmpty()){
                System.err.println("Datos inválidos para la entrada.");
            }
            else{
                float precio = Float.parseFloat(precioStr);
                int stock = Integer.parseInt(stockStr);
                int id = Integer.parseInt(idStr);
                TEntrada nuevaEntrada = new TEntrada(id, nombre.trim(), precio, stock, fecha, hora.trim(), ubicacion.trim(), numeroAsiento.trim(), partido.trim());
                boolean exito = prod.updateProducto(nuevaEntrada); 
    
                if (exito) {
                    System.out.println("¡Entrada registrada con éxito! ID asignado: " + nuevaEntrada.getID());
                } else {
                    System.out.println("la actualización de la entrada no tuvo éxito (sin error específico).");
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Error: Formato de precio o stock inválido.");
        } catch (Exception e) {
            System.err.println("Error inesperado al actualizar entrada: " + e.getMessage());
            e.printStackTrace();
        }
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
                boolean exito = prod.altaProducto(nuevoJuguete); 
    
                if (exito) {
                    System.out.println("¡Juguete registrado con éxito! ID asignado: " + nuevoJuguete.getID());
                } else {
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
                boolean exito = prod.updateProducto(nuevoJuguete); 
    
                if (exito) {
                    System.out.println("¡Juguete registrado con éxito! ID asignado: " + nuevoJuguete.getID());
                } else {
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
        List<TProducto> productos = prod.listarProductos();

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
        if (prod.deleteProducto(id)) {
        	 System.out.println("¡Producto con ID: " + idStr + " eliminado correctamente!");
        }
        else {
        	System.out.println("No se encontró un producto con el ID: " + idStr + " (o ya había sido eliminado).");
        }
    }
}
