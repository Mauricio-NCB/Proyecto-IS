package negocio.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


public class TFactura {

    private String codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private float importe;
    public TCliente tiene;
    public TDependiente dependientes;

    public TFactura(final Date fecha, final int hora, final float importe) {
    }

    public TFactura() {
		// TODO Auto-generated constructor stub
	}

	public String getCodigo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.codigo;
    }
	
	public void setCodigo(String codigo) {
		// TODO Auto-generated method stub
		this.codigo = codigo;
	}

    public LocalDate getFecha() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.fecha;
    }

    public void setFecha(final LocalDate localDate) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.fecha = localDate;
    }

    public LocalTime getHora() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.hora;
    }

    public void setHora(final LocalTime localTime) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.hora = localTime;
    }

    public float getImporte() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.importe;
    }

    public void setImporte(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.importe = value;
    }

	public void setTiene(TCliente cliente) {
		// TODO Auto-generated method stub
		this.tiene = cliente;
	}
	
	public TCliente getTiene() {
		// TODO Auto-generated method stub
		return tiene;
	}

	public void setDependientes(TDependiente dep) {
		// TODO Auto-generated method stub
		this.dependientes = dep;
	}

	public TDependiente getDependientes() {
		// TODO Auto-generated method stub
		return dependientes;
	}

	


}
