package negocio.sa;

import java.util.List;

import negocio.dto.TProducto;

public interface SAProducto {

	void altaProducto(TProducto producto) throws Exception;
	void deleteProducto(int id) throws Exception;
	void updateProducto(TProducto producto) throws Exception;
	List<TProducto> listarProductos() throws Exception;
}
