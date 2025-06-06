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

    protected TProducto(final String nombre, final float precio, final int stock) {
    	this.nombre = nombre;
    	this.precio = precio;
    	this.stock = stock;
    }
    
    public void validarDatos() throws Exception {
    	validarDatosComunes();
    	validarDatosEspecificos();
    }
    
    private void validarDatosComunes() throws Exception {
    	if (nombre == null)
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (precio < 0)
            throw new IllegalArgumentException("El precio no puede ser negativo");
        if (stock < 0)
            throw new IllegalArgumentException("El stock no puede ser negativo");
    }
    
    protected abstract void validarDatosEspecificos() throws Exception;

    public void disminuirStock(final int cantidad) {
    	stock -= cantidad;
    }

    public void aumentarStock(final int cantidad) {
    	stock += cantidad;
    }

    public void setID(final int id){
        this.ID = id;
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
