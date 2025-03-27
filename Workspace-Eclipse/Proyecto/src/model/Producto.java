package model;
public class Producto {

    private int ID;
    private String nombre;
    private float precio;
    private int stock;
    
    protected Producto(final String nombre, final float precio, final int stock) {
    }

    public void disminuirStock(final int cantidad) {
    }

    public void aumentarStock(final int cantidad) {
    }

    public int getID() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.ID;
    }

    public String getNombre() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.nombre;
    }

    public void setNombre(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.nombre = value;
    }

    public float getPrecio() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.precio;
    }

    public void setPrecio(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.precio = value;
    }

    public int getStock() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.stock;
    }

    public void setStock(final int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.stock = value;
    }

}
