package negocio.dto;
import java.util.Date;

public class TEntrada extends TProducto {

    private Date fecha;
    private String hora;
    private String ubicacion;
    private String numeroAsiento;
    private String partido;

    public TEntrada(final int ID, final String nombre, final float precio, final int stock,
    		final Date fecha, final String hora, final String ubicacion, final String numeroAsiento, final String partido) {
    	super(ID, nombre, precio, stock);
    	this.fecha = fecha;
    	this.hora = hora;
    	this.ubicacion = ubicacion;
    	this.numeroAsiento = numeroAsiento;
    	this.partido = partido;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(final Date value) {
        this.fecha = value;
    }

    public String getHora() {
        return this.hora;
    }

    public void setHora(final String value) {
        this.hora = value;
    }

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
    
    public String getNumeroAsiento() {
        return this.numeroAsiento;
    }

    public void setNumeroAsiento(final String value) {
        this.numeroAsiento = value;
    }

    public String getPartido() {
        return this.partido;
    }

    public void setPartido(final String value) {
        this.partido = value;
    }


}
