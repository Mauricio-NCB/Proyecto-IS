package negocio.dto;

import java.util.ArrayList;
import java.util.List;


public class Dependiente extends Empleado {
    private float sumVentas;
    public List<Factura> genera = new ArrayList<Factura> ();

    public Dependiente(final float SumVentas) {
    	super(null, null, 0);
    }
    
    public float getSumVentas() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.sumVentas;
    }
    
    public void setSumVentas(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.sumVentas = value;
    }

}
