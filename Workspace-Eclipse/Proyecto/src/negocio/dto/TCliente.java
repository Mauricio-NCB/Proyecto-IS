package negocio.dto;

import java.util.ArrayList;
import java.util.List;


public class TCliente {
	
    private int numSocio;
    private String nombre;
    private String direccion;
    private String correo;
    public List<TFactura> facturas = new ArrayList<TFactura> ();


    public TCliente(final String nombre, final String direccion, final String correo) {
    	this.nombre = nombre;
    	this.direccion = direccion;
    	this.correo = correo;
    }

    public int getNumSocio() {
        
        return this.numSocio;
    }
    
    public void setNumSocio(int numSocio) {
        this.numSocio = numSocio;
    }

    public String getNombre() {
        
        return this.nombre;
    }

    public void setNombre(final String value) {
       
        this.nombre = value;
    }

    public String getDireccion() {
        
        return this.direccion;
    }

    public void setDireccion(final String value) {
       
        this.direccion = value;
    }

    public String getCorreo() {
        
        return this.correo;
    }

    public void setCorreo(final String value) {
       
        this.correo = value;
    }
    
    @Override
    public String toString() {
        return "Cliente{Numero socio ='" + getNumSocio() + "', Nombre ='" + getNombre() + "', Direccion = '" + getDireccion() + "', Correo = '" + getCorreo() + " }";
    }
}