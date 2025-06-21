package negocio.dto;

public class TLineaVenta {
	private TProducto producto;
	private int cantidad;
	
	public TLineaVenta(TProducto p, int cant) {
		this.producto = p;
		this.cantidad = cant;
	}
	
	public TProducto getProducto() {
		return this.producto;
	}
	
	public void setProcudto(TProducto producto) {
		this.producto = producto;
	}
	
	public int getCantidad () {
		return cantidad;
	}
	
	public void setCantidad(int c) {
		this.cantidad = c;
	}
	
	public double getPrecioTotal() {
		return producto.getPrecio()* this.cantidad;
	}
	
    public String toString() {
        return  producto.getNombre() + ": "+" PrecioUnitario= " + producto.getPrecio() + "  Cantidad = " + this.cantidad +
               "  PrecioUnitario='" + producto.getPrecio() + "PrecioTotal = " + this.getPrecioTotal();
    }
}