package negocio.sa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import integracion.DAOFactura;
import integracion.DAOFacturaImp;
import integracion.DAOProducto;
import integracion.DAOProductoImp;
import negocio.dto.TCliente;
import negocio.dto.TDependiente;
import negocio.dto.TFactura;
import negocio.dto.TProducto;

public class SAFacturaImp implements SAFactura {

	SACliente saCliente = new SAClienteImp();
	SAEmpleado saEmpleado = new SAEmpleadoImp();
	DAOProducto daoProducto = new DAOProductoImp();
	DAOFactura daoFactura = new DAOFacturaImp();
	
	@Override
	public void crearFactura(int idCliente, String idDependiente, List<Object[]> productosConCantidad) throws Exception {
		// TODO Auto-generated method stub

		List<TCliente> clientes = saCliente.getAllClientes(); 
		boolean existeCliente = clientes.stream().anyMatch(c -> c.getNumSocio() == idCliente);

		if (!existeCliente) throw new Exception("El cliente con ID " + idCliente + " no existe.");

		
		TDependiente dependiente = (TDependiente) saEmpleado.obtenerEmpleado(idDependiente); 

		if (dependiente == null) throw new Exception("El dependiente con ID " + idDependiente + " no existe.");

		// Validaci�n de stock
		for (Object[] par : productosConCantidad) {
			TProducto producto = (TProducto) par[0];
			int cantidad = (int) par[1];

			if (producto.getStock() < cantidad)
				throw new Exception("No hay suficiente stock para el producto con ID " + producto.getID());
		}

		// Calcular total e insertar factura
		float total = 0f;
		for (Object[] par : productosConCantidad) {
			TProducto producto = (TProducto) par[0];
			int cantidad = (int) par[1];
			total += producto.getPrecio() * cantidad;
		}

		TFactura f = new TFactura();
		f.setCodigo(UUID.randomUUID().toString());
		f.setFecha(LocalDate.now());
		f.setHora(LocalTime.now());
		f.setImporte(total);

		TCliente cliente = clientes.stream()
		    .filter(c -> c.getNumSocio() == idCliente)
		    .findFirst().orElseThrow(() -> new Exception("Cliente no encontrado"));
		f.setTiene(cliente);

		f.setDependientes(dependiente.getNombre());

		daoFactura.createFactura(f);

		// Descontar stock
		for (Object[] par : productosConCantidad) {
			TProducto producto = (TProducto) par[0];
			int cantidad = (int) par[1];
			producto.setStock(producto.getStock() - cantidad);
			daoProducto.updateProducto(producto);

		}
	}

	@Override
	public void actualizarFactura(TFactura factura) throws Exception {
		// TODO Auto-generated method stub
		daoFactura.updateFactura(factura);
	}

	@Override
	public void eliminarFactura(String codigoFactura) throws Exception {
		// TODO Auto-generated method stub
		daoFactura.deleteFactura(codigoFactura);
	}

	@Override
	public TFactura obtenerFactura(String codigoFactura) throws Exception {
		// TODO Auto-generated method stub
		return daoFactura.readFactura(codigoFactura);
	}

	@Override
	public List<TFactura> obtenerFacturas() {
		// TODO Auto-generated method stub
		return daoFactura.readAllFacturas();
	}
	
}

