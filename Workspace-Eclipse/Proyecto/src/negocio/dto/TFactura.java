package negocio.dto;

import java.time.LocalDate;
import java.time.LocalTime;


public class TFactura {

    private String codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private float importe;

    public TFactura(final String codigo, final LocalDate fecha, final LocalTime hora, final float importe) {
    	this.codigo = codigo;
    	this.fecha = fecha;
    	this.hora = hora;
    	this.importe = importe;
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
}
