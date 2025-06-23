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
    
    public void nuevoProducto(TProducto producto) throws Exception {
        
    	prod.altaProducto(producto);
        new VentanaNuevoProd().setVisible(true);
    }

    public void deleteProducto(Integer id) throws Exception {

        prod.deleteProducto(id);
    }
    
    public List<TProducto> listarProductos() throws Exception {

        return prod.listarProductos();        
    }

    public void registrarPoster(String nombre, Float precio, Integer stock, String tamano) throws Exception {

        TPoster nuevoPoster = new TPoster(nombre, precio, stock, tamano);
        prod.altaProducto(nuevoPoster); 
    }

	public void actualizarPoster(Integer id, String nombre, Float precio, Integer stock, String tamano) throws Exception {

        TPoster nuevoPoster = new TPoster(id, nombre, precio, stock, tamano);
        prod.updateProducto(nuevoPoster); 
	
	}
	
	public void registrarCamiseta(String nombre, Float precio, Integer stock, Integer talla, 
	                            String dorsal, Integer numJugador) throws Exception {
        
        TCamiseta nuevaCamiseta = new TCamiseta(nombre, precio, stock, talla, dorsal, numJugador);
        prod.altaProducto(nuevaCamiseta); 
	}
	
	public void actualizarCamiseta(Integer id, String nombre, Float precio, Integer stock, 
	                             Integer talla, String dorsal, Integer numJugador) throws Exception {
	        
        TCamiseta nuevaCamiseta = new TCamiseta(id, nombre, precio, stock, talla, dorsal, numJugador);
        prod.updateProducto(nuevaCamiseta); 
	
	}
	
	public void registrarEntrada(String nombre, Float precio, Integer stock, Date fecha, String hora, 
	                          String ubicacion, String numeroAsiento, String partido) throws Exception {
        
        TEntrada nuevaEntrada = new TEntrada(nombre, precio, stock, fecha, hora, 
                                           ubicacion, numeroAsiento, partido);
        prod.altaProducto(nuevaEntrada); 
	
	}
	
	public void actualizarEntrada(Integer id, String nombre, Float precio, Integer stock, Date fecha, 
	                           String hora, String ubicacion, String numeroAsiento, String partido) throws Exception {
        
        TEntrada nuevaEntrada = new TEntrada(id, nombre, precio, stock, fecha, hora, 
                                           ubicacion, numeroAsiento, partido);
        prod.updateProducto(nuevaEntrada); 
	}
	
	public void registrarJuguete(String nombre, Float precio, Integer stock, String tipo, String tamano) throws Exception {
	        
        TJuguete nuevoJuguete = new TJuguete(nombre, precio, stock, tipo, tamano);
        prod.altaProducto(nuevoJuguete); 
	}
	
	public void actualizarJuguete(Integer id, String nombre, Float precio, Integer stock, 
	                            String tipo, String tamano) throws Exception {

        TJuguete nuevoJuguete = new TJuguete(id, nombre, precio, stock, tipo, tamano);
        prod.updateProducto(nuevoJuguete); 
	
	}
	
	public void eliminarProducto(Integer id) throws Exception {

        prod.deleteProducto(id);
	
	}
}