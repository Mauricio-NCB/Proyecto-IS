package negocio.dto;

import java.util.ArrayList;
import java.util.List;


public class TDependiente extends TEmpleado {
    private float sumVentas;
    public List<TFactura> genera = new ArrayList<TFactura> ();

    public TDependiente(final String identificador, final String nombre, final float sueldo, final String contrasena, final float sumVentas) {
    	super(identificador, nombre, sueldo, contrasena);
    	this.sumVentas = sumVentas;
    }
    
    public float getSumVentas() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.sumVentas;
    }
    
    public void setSumVentas(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.sumVentas = value;
    }

    @Override
    public String toString() {
        return "Dependiente " + getNombre() + "{ ID='" + getIdentificador() + '\'' +
               ", Nombre='" + getNombre() + '\'' +
               ", Sueldo=" + getSueldo() +
               ", Suma de ventas='" + this.sumVentas + '\'' + '}';
    }

}
