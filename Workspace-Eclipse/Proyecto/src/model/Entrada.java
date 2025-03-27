package model;
import java.util.Date;

public class Entrada extends Producto {

    private Date fecha;
    private String hora;
    private String ubicacion;
    private String numeroAsiento;
    private String partido;

    public Entrada(final Date fecha, final String hora, final String ubicacion, final String numeroAsiento, final String partido) {
    	super(partido, 0, 0);
    }

    public Date getFecha() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.fecha;
    }

    public void setFecha(final Date value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.fecha = value;
    }

    public String getHora() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.hora;
    }

    public void setHora(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.hora = value;
    }

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
    
    public String getNumeroAsiento() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.numeroAsiento;
    }

    public void setNumeroAsiento(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.numeroAsiento = value;
    }

    public String getPartido() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.partido;
    }

    public void setPartido(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.partido = value;
    }


}
