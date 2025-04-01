package negocio.dto;

import java.util.ArrayList;
import java.util.List;


public class Director extends Empleado {
    private String cargo;
    public List<Dependiente> gestionar = new ArrayList<Dependiente> ();
    public List<Venta> revisar = new ArrayList<Venta> ();
    public Director(final String cargo) {
    	super(null, null, 0);
    }
    
    public String getCargo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.cargo;
    }
    public void setCargo(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.cargo = value;
    }

}
