package negocio.sa;

import java.util.List;

import negocio.dto.TProducto;

public interface SAProducto {

	boolean altaProducto(TProducto producto);
	boolean deleteProducto(int id);
	boolean updateProducto(TProducto producto);
	List<TProducto> listarProductos();
}
