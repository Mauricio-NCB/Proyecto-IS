package negocio.dto;
import java.util.Date;

public class Envio {
    private String ID;
    private float coste;
    private String direccion;
    private String estado;
    private Date fechaEnvio;
    private Date fechaEntrega;

    public Envio(final float coste, final String direccion, final String estado, final String fechaEnvio, final String fechaEntrega) {
    }

    public String getID() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.ID;
    }

    public float getCoste() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.coste;
    }

    public void setCoste(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.coste = value;
    }

    public String getDireccion() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.direccion;
    }

    public void setDireccion(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.direccion = value;
    }

    public String getEstado() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.estado;
    }

    public void setEstado(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.estado = value;
    }

    public Date getFechaEnvio() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.fechaEnvio;
    }

    public void setFechaEnvio(final Date value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.fechaEnvio = value;
    }

    public Date getFechaEntrega() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.fechaEntrega;
    }

    public void setFechaEntrega(final Date value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.fechaEntrega = value;
    }

}
