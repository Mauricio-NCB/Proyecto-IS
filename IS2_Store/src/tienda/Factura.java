package tienda;
import java.util.Date;

import usuarios.Cliente;
import usuarios.Dependiente;

public class Factura {

    private String codigo;
    private Date fecha;
    private String hora;
    private float importe;
    public Cliente tiene;
    public Venta ventas;
    public Dependiente dependientes;
    public Envio manda;

    public Factura(final Date fecha, final int hora, final float importe) {
    }

    public String getCodigo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.codigo;
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

    public float getImporte() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.importe;
    }

    public void setImporte(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.importe = value;
    }

}
