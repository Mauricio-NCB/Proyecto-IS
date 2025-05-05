package integracion;

import java.util.List;

import negocio.dto.TProducto;

public interface DAOProducto {
	
	boolean createProducto(TProducto producto);
	boolean deleteProducto(int idProducto);
	boolean updateProducto(TProducto producto);
	List<TProducto> obtenerTodosLosProductos();
}
