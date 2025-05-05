package test;

import negocio.dto.TCamiseta;
import negocio.dto.TEntrada;
import negocio.dto.TJuguete;
import negocio.dto.TProducto;
import negocio.sa.SAProductoImp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SAProductoTest {
	
	private SAProductoImp saProd;
	
	@BeforeEach
	public void setUp() {
		saProd = new SAProductoImp();
	}
	
	@Test
    public void testAltaProducto() {
        TCamiseta camiseta = new TCamiseta(0, "Segunda equipación", 89.99f, 10, 42, "10", 7);
        boolean resultado = saProd.altaProducto(camiseta);
        assertTrue(resultado, "El alta de camiseta debería ser exitosa");
        assertTrue(camiseta.getID() > 0, "El ID debería haberse asignado");
        
        TEntrada entrada = new TEntrada(0, "Entrada VIP", 150.0f, 5, 
            new Date(), "20:00", "Tribuna", "A12", "Celta de Vigo vs Madrid");
        resultado = saProd.altaProducto(entrada);
        assertTrue(resultado, "El alta de entrada debería ser exitosa");
    }
	
	 @Test
	    public void testListarProductos() {
	        List<TProducto> productos = saProd.listarProductos();
	        assertNotNull(productos, "La lista no debería ser nula");
	        assertFalse(productos.isEmpty(), "Debería haber productos en la BD");
	        
	        for (TProducto p : productos) {
	            assertTrue(p.getID() > 0, "El ID debería ser válido");
	            assertNotNull(p.getNombre(), "El nombre no debería ser nulo");
	            assertTrue(p.getPrecio() > 0, "El precio debería ser positivo");
	            
	            if (p instanceof TCamiseta) {
	                TCamiseta c = (TCamiseta) p;
	                assertTrue(c.getTalla() > 0, "La talla debería ser válida");
	            }
	        }
	    }
	    
	    @Test
	    public void testActualizarProducto() {
	        List<TProducto> productos = saProd.listarProductos();
	        if (!productos.isEmpty()) {
	            TProducto producto = productos.get(0);
	            float nuevoPrecio = producto.getPrecio() + 10;
	            producto.setPrecio(nuevoPrecio);
	            
	            if (producto instanceof TCamiseta) {
	                ((TCamiseta)producto).setTalla(40);
	            }
	            
	            boolean resultado = saProd.updateProducto(producto);
	            assertTrue(resultado, "La actualización debería ser exitosa");
	        }
	    }
	    
	    @Test
	    public void testEliminarProducto() {
	        TJuguete juguete = new TJuguete(0, "Juguete Test", 19.99f, 5, "Pelota", "Mediano");
	        saProd.altaProducto(juguete);
	        int id = juguete.getID();
	        
	        boolean resultado = saProd.deleteProducto(id);
	        assertTrue(resultado, "La eliminación debería ser exitosa");
	    }

}
