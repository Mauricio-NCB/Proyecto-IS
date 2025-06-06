package negocio.dto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
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

    public TEntrada(final String nombre, final float precio, final int stock,
            final Date fecha, final String hora, final String ubicacion, final String numeroAsiento, final String partido) {
        super(nombre, precio, stock);
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

	@Override
	protected void validarDatosEspecificos() throws Exception {
		// TODO Auto-generated method stub
		if (fecha == null)
            throw new IllegalArgumentException("La fecha no puede estar vacío");
        if (hora == null)
            throw new IllegalArgumentException("La hora no puede estar vacío");
        if (!fechaValida(fecha, hora)) 
        	throw new IllegalArgumentException("Formato de fecha inválido");
        if (ubicacion == null)
            throw new IllegalArgumentException("La ubicación no puede estar vacío");
        if (numeroAsiento == null)
            throw new IllegalArgumentException("El número de asiento no puede estar vacío");
        if (partido == null)
            throw new IllegalArgumentException("El partido no puede estar vaío");
	}
	
	private boolean fechaValida(Date fecha, String hora) {
		
		try {
			LocalDate miFecha = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalTime miHora = LocalTime.parse(hora);
			
			LocalDateTime fechaHora = LocalDateTime.of(miFecha, miHora);
			LocalDateTime ahora = LocalDateTime.now();
			
			return fechaHora.isAfter(ahora);
		} catch (DateTimeParseException e) {
	        return false;
	    }
	}


}
