package negocio.sa;

import java.util.List;

import negocio.dto.TProducto;

public interface SAProducto {

	boolean altaProducto(TProducto producto);

	List<TProducto> listarProductos();
}
