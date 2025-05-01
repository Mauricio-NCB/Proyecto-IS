package integracion;

import java.util.List;

import negocio.dto.TProducto;

public interface DAOProducto {
	
	boolean createProducto(TProducto producto);

	List<TProducto> obtenerTodosLosProductos();
}
