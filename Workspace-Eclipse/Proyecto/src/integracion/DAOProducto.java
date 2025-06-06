package integracion;

import java.util.List;

import negocio.dto.TProducto;

public interface DAOProducto {
	
	void createProducto(TProducto producto) throws Exception;
	void deleteProducto(int idProducto) throws Exception;
	void updateProducto(TProducto producto) throws Exception;
	List<TProducto> obtenerTodosLosProductos() throws Exception;
}
