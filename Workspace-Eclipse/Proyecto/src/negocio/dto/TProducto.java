package negocio.dto;
public abstract class TProducto {

    private int ID;
    private String nombre;
    private float precio;
    private int stock;
    
    protected TProducto(final int ID, final String nombre, final float precio, final int stock) {
    	this.ID = ID;
    	this.nombre = nombre;
    	this.precio = precio;
    	this.stock = stock;
    }

    public void disminuirStock(final int cantidad) {
    	stock -= cantidad;
    }

    public void aumentarStock(final int cantidad) {
    	stock += cantidad;
    }

    public int getID() {
        return this.ID;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(final String value) {
        this.nombre = value;
    }

    public float getPrecio() {
        return this.precio;
    }

    public void setPrecio(final float value) {
        this.precio = value;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(final int value) {
        this.stock = value;
    }

    public boolean estaDisponible() {
    	return stock > 0; 
    }
}
