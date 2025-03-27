package model;

import java.util.ArrayList;
import java.util.List;


public class Venta {
    private int ID;
    private float precio;
    private int cantidad;
    public Factura asociado;
    public List<Producto> productos = new ArrayList<Producto> ();
    public Cliente recibe;
    public Venta(final float precio, final int cantidad) {
    	
    }
    
    public int getID() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.ID;
    }
    
    public float getPrecio() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.precio;
    }
    
    public void setPrecio(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.precio = value;
    }
    
    public int getCantidad() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.cantidad;
    }
    
    public void setCantidad(final int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.cantidad = value;
    }

}
