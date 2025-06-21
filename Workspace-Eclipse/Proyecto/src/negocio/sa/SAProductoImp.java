package negocio.sa;

import negocio.dto.TProducto;

import java.util.List;

import integracion.DAOProducto;
import integracion.DAOProductoImp;

public class SAProductoImp implements SAProducto{
	private DAOProducto daoProducto = new DAOProductoImp();

	@Override
	public void altaProducto(TProducto producto) throws Exception {
		
		producto.validarDatos();
		daoProducto.createProducto(producto);
	}

	@Override
	public void deleteProducto(int id) throws Exception {
		daoProducto.deleteProducto(id);
	}

	@Override
	public void updateProducto(TProducto producto) throws Exception {	
		producto.validarDatos();
		daoProducto.updateProducto(producto);
	}

	@Override
	public List<TProducto> listarProductos() throws Exception {
		return daoProducto.obtenerTodosLosProductos();
	}

}
