package presentacion;

import java.util.List;

import negocio.dto.TFactura;
import negocio.dto.TLineaVenta;
import negocio.dto.TProducto;
import negocio.dto.TVenta;
import negocio.sa.SAVenta;
import negocio.sa.SAVentaImp;

public class ControladorVenta {
	private static ControladorVenta instancia;
    private SAVenta servicioVenta;
    
    private ControladorVenta() {
        this.servicioVenta = new SAVentaImp();
    }
    
    public static ControladorVenta getInstance() {
        if (instancia == null) {
            instancia = new ControladorVenta();
        }
        return instancia;
    }
    
   //true si se llega a abrir la venta, false en caso contrario
    public boolean abrirVenta(int idCliente, String idDependiente, List<TLineaVenta> listaProductos) {
        try {
            servicioVenta.abrirVenta(idCliente, idDependiente, listaProductos);
            System.out.println("Venta abierta con éxito");
            return true;
        } catch (Exception e) {
            System.err.println("Error al abrir venta: " + e.getMessage());
            return false;
        }
    }
    
    
    public TFactura cerrarVenta(String codigoVenta) {
        try {
            TVenta venta = servicioVenta.obtenerVenta(codigoVenta);
            if (venta != null) {
                servicioVenta.cerrarVenta(venta);
                System.out.println("Venta cerrada con éxito");
                return venta.getFactura();
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error al cerrar venta: " + e.getMessage());
            return null;
        }
    }
  
    public boolean anadirProducto(String codigoVenta, TProducto producto, int cantidad) {
        try {
            TLineaVenta linea = new TLineaVenta(producto, cantidad);
            servicioVenta.anadirProducto(codigoVenta, linea);
            System.out.println("Producto añadido con éxito");
            return true;
        } catch (Exception e) {
            System.err.println("Error al añadir producto: " + e.getMessage());
            return false;
        }
    }
    
  
    public TVenta obtenerVenta(String codigoVenta) {
        try {
            return servicioVenta.obtenerVenta(codigoVenta);
        } catch (Exception e) {
            System.err.println("Error al obtener venta: " + e.getMessage());
            return null;
        }
    }
    
   
    public List<TVenta> obtenerTodasVentas() {
        try {
            return servicioVenta.obtenerventas();
        } catch (Exception e) {
            System.err.println("Error al obtener ventas: " + e.getMessage());
            return null;
        }
    }
    
   
    public void mostrarTodasVentas() {
        System.out.println("\n--- Listado de Ventas ---");
        List<TVenta> ventas = obtenerTodasVentas();
        
        if (ventas == null || ventas.isEmpty()) {
            System.out.println("No se encontraron ventas registradas.");
        } else {
            for (TVenta venta : ventas) {
                System.out.println(venta);
                System.out.println("  Productos:");
                for (TLineaVenta linea : venta.getLineasVenta()) {
                    System.out.println("    - " + linea);
                }
                System.out.println("  Total: " + venta.getImporteTotal());
            }
        }
    }
    
   
    public void mostrarVenta(String codigoVenta) {
        TVenta venta = obtenerVenta(codigoVenta);
        if (venta != null) {
            System.out.println("\n--- Detalles de Venta " + codigoVenta + " ---");
            System.out.println("Cliente: " + venta.getTiene().getNombre());
            System.out.println("Dependiente: " + venta.getDependiente().getNombre());
            System.out.println("Fecha: " + venta.getFecha() + " " + venta.getHora());
            System.out.println("Productos:");
            for (TLineaVenta linea : venta.getLineasVenta()) {
                System.out.println("  - " + linea);
            }
            System.out.println("Total: " + venta.getImporteTotal());
            if (venta.getFactura() != null) {
                System.out.println("Factura asociada: " + venta.getFactura().getCodigo());
            }
        } else {
            System.out.println("No se encontró la venta con código: " + codigoVenta);
        }
    }
}
