package negocio.sa;

import negocio.dto.TProducto;

import java.util.List;

import integracion.DAOProducto;
import integracion.DAOProductoImp;

public class SAProductoImp implements SAProducto{
	private DAOProducto daoProducto = new DAOProductoImp();

	@Override
	public boolean altaProducto(TProducto producto) {
		return daoProducto.createProducto(producto);
	}

	@Override
	public boolean deleteProducto(int id) {
		return daoProducto.deleteProducto(id);
	}

	@Override
	public List<TProducto> listarProductos() {
		return daoProducto.obtenerTodosLosProductos();
	}

	@Override
	public boolean updateProducto(TProducto producto) {
		return daoProducto.updateProducto(producto);
	}

}
