import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("6e7e07dd-b906-4e3c-85b4-9e2b3624241f")
public class Factura {
    @objid ("a73a46ee-218c-420d-9082-6923f34d7b6f")
    private String codigo;

    @objid ("1913736a-f195-4372-9cdd-d3b78218e465")
    private Date fecha;

    @objid ("c0f11f1a-8df4-453f-8da8-a1a014a510d0")
    private String hora;

    @objid ("9b2ccc40-a6d4-4371-862d-02e9807effe0")
    private float importe;

    @objid ("00c17f26-1477-4ec5-9095-ffc63ab0cf38")
    public Cliente tiene;

    @objid ("142113bb-875f-435c-9bc2-9e2f8b6613cd")
    public Venta ;

    @objid ("5e576978-c545-4348-9520-a6d2efb697b1")
    public Dependiente ;

    @objid ("0c204d55-6e13-4e9a-8379-90bf0fe77bb7")
    public Envio manda;

    @objid ("9eb64981-8c5a-4cf6-b277-e1cd5e3b0b94")
    public Factura(final Date fecha, final int hora, final float importe) {
    }

    @objid ("c36418d1-df06-4ad6-83c1-6a7a851c4d33")
    public String getCodigo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.codigo;
    }

    @objid ("64f3471e-2fab-48c6-bb2e-dec3a2fc3181")
    public Date getFecha() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.fecha;
    }

    @objid ("adcbaa6f-88e3-4413-bee6-339961bdbe2a")
    public void setFecha(final Date value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.fecha = value;
    }

    @objid ("5e3f3723-3f0d-473f-8699-8b6c4f3e44c9")
    public String getHora() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.hora;
    }

    @objid ("3091ad92-5eab-4366-b0c3-4126997ad7f6")
    public void setHora(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.hora = value;
    }

    @objid ("3869ecd6-1142-460c-9de8-06c5b29cbe68")
    public float getImporte() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.importe;
    }

    @objid ("bdede428-fa63-4cdf-8fca-4fc4d43b3760")
    public void setImporte(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.importe = value;
    }

}
