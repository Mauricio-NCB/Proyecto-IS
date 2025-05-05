package negocio.dto;

import java.util.ArrayList;
import java.util.List;


public class TDirector extends TEmpleado {
    private String cargo;
    public List<TDependiente> gestionar = new ArrayList<TDependiente> ();
    
    public TDirector(final String identificador, final String nombre, final float sueldo, final String contrasena, final String cargo) {
    	super(identificador, nombre, sueldo, contrasena);
    	this.cargo = cargo;
    }
    
    public String getCargo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.cargo;
    }
    public void setCargo(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.cargo = value;
    }
    
    @Override
    public String toString() {
        return "Director " + getNombre() + "{ ID='" + getIdentificador() + '\'' +
               ", Nombre='" + getNombre() + '\'' +
               ", Sueldo=" + getSueldo() +
               ", Cargo='" + this.cargo + '\'' + '}';
    }
}
