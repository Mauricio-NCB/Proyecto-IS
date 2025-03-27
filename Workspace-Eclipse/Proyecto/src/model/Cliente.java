package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private int numSocio;
    private String nombre;
    private String direccion;
    private String correo;
    public List<Factura> facturas = new ArrayList<Factura> ();
    public List<Venta> ventas = new ArrayList<Venta> ();

    public Cliente(final String nombre, final String direccion, final String correo) {
    }

    public int getNumSocio() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.numSocio;
    }

    public String getNombre() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.nombre;
    }

    public void setNombre(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.nombre = value;
    }

    public String getDireccion() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.direccion;
    }

    public void setDireccion(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.direccion = value;
    }

    public String getCorreo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.correo;
    }

    public void setCorreo(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.correo = value;
    }

}
