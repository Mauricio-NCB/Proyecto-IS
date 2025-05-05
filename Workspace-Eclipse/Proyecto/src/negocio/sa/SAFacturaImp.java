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
	SADependiente saDependiente = new SADependienteImp();
	DAOProducto daoProducto = new DAOProductoImp();
	DAOFactura daoFactura = new DAOFacturaImp();
	
	@Override
	public void crearFactura(int idCliente, String idDependiente, List<Object[]> productosConCantidad) throws Exception {
		// TODO Auto-generated method stub

		List<TCliente> clientes = saCliente.getAllClientes(); 
		boolean existeCliente = clientes.stream().anyMatch(c -> c.getNumSocio() == idCliente);

		if (!existeCliente) throw new Exception("El cliente con ID " + idCliente + " no existe.");

		List<TDependiente> dependientes = saDependiente.listarDependientes();
		boolean existeDep = dependientes.stream().anyMatch(d -> d.getIdentificador().equals(idDependiente));

		if (!existeDep) throw new Exception("El dependiente con ID " + idDependiente + " no existe.");

		// Validación de stock
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

		TDependiente dep = dependientes.stream()
		    .filter(d -> d.getIdentificador().equals(idDependiente))
		    .findFirst().orElseThrow(() -> new Exception("Dependiente no encontrado"));
		f.setDependientes(dep);

		daoFactura.insert(f);

		// Descontar stock
		for (Object[] par : productosConCantidad) {
			TProducto producto = (TProducto) par[0];
			int cantidad = (int) par[1];
			producto.setStock(producto.getStock() - cantidad);
			daoProducto.updateProducto(producto);

		}
	}

	@Override
	public List<TFactura> listarFacturas() {
		// TODO Auto-generated method stub
		return daoFactura.listarFacturas();
	}

}
