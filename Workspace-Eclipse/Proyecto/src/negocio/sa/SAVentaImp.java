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
import negocio.dto.TEmpleado;
import negocio.dto.TFactura;
import negocio.dto.TLineaVenta;
import negocio.dto.TProducto;
import negocio.dto.TVenta;

public class SAVentaImp implements SAVenta{
	
	 	private DAOVenta daoVenta;
	    private DAOProducto daoProducto;
	    private SACliente saCliente;
	    private SAEmpleado saEmpleado;
	    private SAFactura saFactura;

	    public SAVentaImp() {
	    	daoVenta = new DAOVentaImp();
	    	daoProducto = new DAOProductoImp();
	    	saCliente = new SAClienteImp();
	    	saEmpleado = new SAEmpleadoImp();
	    	saFactura = new SAFacturaImp();
	    }

	@Override
	public void abrirVenta(int idCliente, String idDependiente, List<TLineaVenta> listaProductos) throws Exception {
		

		TCliente cliente = saCliente.obtenerCliente(idCliente);
		if (cliente == null) throw new Exception("El cliente con ID " + idCliente + " no existe.");

		TEmpleado empleado = saEmpleado.obtenerEmpleado(idDependiente);
		if (empleado == null) throw new Exception("El dependiente con ID " + idDependiente + " no existe.");

        
        if (!(empleado instanceof TDependiente)) {
            throw new Exception("El empleado con ID " + idDependiente + " no es un dependiente.");
        }

        TDependiente dep = (TDependiente) empleado;

        List<TProducto> productos = daoProducto.obtenerTodosLosProductos();
        for (TLineaVenta linea : listaProductos) {
            TProducto producto = productos.stream()
                .filter(p -> p.getID() == linea.getProducto().getID())
                .findFirst()
                .orElse(null);

            if (producto == null) {
                throw new Exception("Producto no encontrado: " + linea.getProducto().getID());
            }
            if (producto.getStock() < linea.getCantidad()) {
                throw new Exception("Stock insuficiente para producto: " + producto.getNombre());
            }
            linea.setProducto(producto);
        }

        String codigo = UUID.randomUUID().toString();
        TVenta venta = new TVenta(codigo, LocalDate.now(), LocalTime.now(), cliente, dep);

        // 2. Añadir todas las líneas de venta a la venta
        for (TLineaVenta linea : listaProductos) {
            venta.addLineaVenta(linea);
        }

        // 3. Persistir la venta (inserta venta y líneas: relación n:m)
        daoVenta.crearVenta(venta); // Este método debe insertar en tabla 'Venta' y tabla 'LineaVenta'

        // 4. Generar automáticamente la factura asociada a esta venta
        TFactura factura = new TFactura(venta.getCodigo(), LocalDate.now(), LocalTime.now(), venta.getImporteTotal()); // ajusta los parámetros según el constructor de TFactura
        saFactura.crearFactura(factura);
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
        TFactura factura = new TFactura(UUID.randomUUID().toString(), LocalDate.now(), LocalTime.now(), (float) venta.getImporteTotal());
        saFactura.crearFactura(factura);
        
        venta.setFactura(factura);
        daoVenta.actualizarVenta(venta);
    }
		
	

	@Override
	public void anadirProducto(String idVenta, TLineaVenta lineaVenta) throws Exception {
		
        TVenta venta = daoVenta.obtenerVenta(idVenta);
        if (venta == null) {
            throw new Exception("Venta no encontrada");
        }

      
        TProducto producto = lineaVenta.getProducto();
        if (producto == null) {
            throw new Exception("Producto no v�lido");
        }

        TProducto productoBD = null;
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

        // A�adir l�nea de venta
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
	public List<TVenta> obtenerventas() throws Exception {
		List<TVenta> ventas = daoVenta.obtenerTodasVentas();
		if (ventas == null) {
			throw new Exception("No se encontraron ventas registradas.");
		}
		return ventas;
	}

}

