package negocio.sa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import integracion.DAOProducto;
import integracion.DAOProductoImp;
import integracion.DAOVenta;
import integracion.DAOVentaImp;
import negocio.dto.TCliente;
import negocio.dto.TDependiente;
import negocio.dto.TFactura;
import negocio.dto.TLineaVenta;
import negocio.dto.TProducto;
import negocio.dto.TVenta;

public class SAVentaImp implements SAVenta{
	
	 	private DAOVenta daoVenta;
	    private DAOProducto daoProducto;
	    private SACliente saCliente;
	    private SADependiente saDependiente;
	    private SAFactura saFactura;

	    public SAVentaImp() {
	    	daoVenta = new DAOVentaImp();
	    	daoProducto = new DAOProductoImp();
	    	saCliente = new SAClienteImp();
	    	saDependiente = new SADependienteImp();
	    	saFactura = new SAFacturaImp();
	    }

	@Override
	public void abrirVenta(int idCliente, String idDependiente, List<TLineaVenta> listaProductos) throws Exception {
		

		List<TCliente> clientes = saCliente.getAllClientes(); 
		boolean existeCliente = clientes.stream().anyMatch(c -> c.getNumSocio() == idCliente);
		if (!existeCliente) throw new Exception("El cliente con ID " + idCliente + " no existe.");
		
		List<TDependiente> dependientes = saDependiente.listarDependientes();
		boolean existeDep = dependientes.stream().anyMatch(d -> d.getIdentificador().equals(idDependiente));

		if (!existeDep) throw new Exception("El dependiente con ID " + idDependiente + " no existe.");
		
		
		
        for (TLineaVenta linea : listaProductos) {
       	 	TProducto producto;
            for (TProducto p: daoProducto.obtenerTodosLosProductos()) {
            	if (p.getID() == producto.getID()) {
            		producto = p;
            	}
            }
            if (producto == null) {
                throw new Exception("Producto no encontrado: " + linea.getProducto().getID());
            }
            if (producto.getStock() < linea.getCantidad()) {
                throw new Exception("Stock insuficiente para producto: " + producto.getNombre());
            }
        }
        TCliente cliente = clientes.stream().filter(c -> c.getNumSocio() == idCliente)
    		    .findFirst().orElseThrow(() -> new Exception("Cliente no encontrado"));
    	

		TDependiente dep = dependientes.stream().filter(d -> d.getIdentificador().equals(idDependiente))
    		    .findFirst().orElseThrow(() -> new Exception("Dependiente no encontrado"));
    		
        
        String codigo = UUID.randomUUID().toString();
        TVenta venta = new TVenta(codigo, LocalDate.now(), LocalTime.now(),cliente,dep);
        
        for (TLineaVenta linea: listaProductos) {
        	venta.addLineaVenta(linea);
        }
        
        daoVenta.crearVenta(venta);
	}

	@Override
	public void cerrarVenta(TVenta venta) throws Exception {
		 // Validar 
        TVenta ventaExistente = daoVenta.obtenerVenta(venta.getCodigo());
        if (ventaExistente == null) {
            throw new Exception("Venta no encontrada");
        }

        // Actualizar stock de productos
        for (TLineaVenta linea : venta.getLineasVenta()) {
            TProducto producto = linea.getProducto();
            producto.setStock(producto.getStock() - linea.getCantidad());
            daoProducto.updateProducto(producto);
        }

        // Generar factura 
        TFactura factura = new TFactura();
        factura.setCodigo(UUID.randomUUID().toString());
        factura.setFecha(LocalDate.now());
        factura.setHora(LocalTime.now());
        factura.setImporte((float) venta.getImporteTotal());
        factura.setTiene(venta.getTiene());
        factura.setDependientes(venta.getDependiente());

        saFactura.crearFactura(venta.getTiene().getNumSocio(), 
                              venta.getDependiente().getIdentificador(), 
                              venta.getLineasVenta().stream()
                                  .map(lv -> new Object[]{lv.getProducto(), lv.getCantidad()})
                                  .toList());

       
        venta.setFactura(factura);
        daoVenta.actualizarVenta(venta);
    }
		
	}

	@Override
	public void añadirProducto(String idVenta, TLineaVenta lineaVenta) throws Exception {
		
        TVenta venta = daoVenta.obtenerVenta(idVenta);
        if (venta == null) {
            throw new Exception("Venta no encontrada");
        }

      
        TProducto producto = lineaVenta.getProducto();
        if (producto == null) {
            throw new Exception("Producto no válido");
        }

        TProducto productoBD;
        for (TProducto p: daoProducto.obtenerTodosLosProductos()) {
        	if (p.getID() == producto.getID()) {
        		productoBD = p;
        	}
        }
        if (productoBD == null) {
            throw new Exception("Producto no encontrado");
        }

        if (productoBD.getStock() < lineaVenta.getCantidad()) {
            throw new Exception("Stock insuficiente para producto: " + productoBD.getNombre());
        }

        // Añadir línea de venta
        venta.addLineaVenta(lineaVenta);
        daoVenta.actualizarVenta(venta);
		
	}

	@Override
	public TVenta obtenerVenta(String id) throws Exception {
		TVenta venta = daoVenta.obtenerVenta(id);
        if (venta == null) {
            throw new Exception("Venta no encontrada");
        }
        return venta;
	}

	@Override
	public List<TVenta> obtenerventas() {
		 return daoVenta.obtenerTodasVentas();
	}

}
