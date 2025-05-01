package presentacion;

import java.util.List;

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
	
	public List<TProducto> listarProductos() {
		return prod.listarProductos();		
	}
}
