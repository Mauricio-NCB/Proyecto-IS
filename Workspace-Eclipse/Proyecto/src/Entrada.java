import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("bc02bda4-f549-4186-a437-c208c06daab6")
public class Entrada extends Producto {
    @objid ("2afbb870-99ab-4415-8bd5-5d09ace55c0d")
    private Date fecha;

    @objid ("01f92be2-2652-4967-98f6-89b2fcfcb48f")
    private String hora;

    @objid ("b0724ac9-05ed-4848-bcb3-d68b5a51c5d0")
    private String ubicacion;

    @objid ("47edae32-d60d-4aca-8c70-4dc037b91cb5")
    private String numeroAsiento;

    @objid ("acb9bfd6-9c7a-40fe-a6b9-fd2edb51d1ae")
    private String partido;

    @objid ("4d3ba8e4-a39a-45aa-aeca-1abb453018be")
    public Entrada(final Date fecha, final String hora, final String ubicacion, final String numeroAsiento, final String partido) {
    }

    @objid ("9213d939-bcd7-45be-84e0-64ed5f9499c4")
    public Date getFecha() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.fecha;
    }

    @objid ("086df56d-8d2f-439e-9cf0-f298dee029bc")
    public void setFecha(final Date value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.fecha = value;
    }

    @objid ("4f5c3ebc-92ea-4fa9-be9c-eed90ac338c2")
    public String getHora() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.hora;
    }

    @objid ("67ec8917-f6d4-457b-a644-9121110bfd7a")
    public void setHora(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.hora = value;
    }

    @objid ("faa655a2-27b7-41ad-9387-03867323f1a0")
    public String getNumeroAsiento() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.numeroAsiento;
    }

    @objid ("b4d5266c-3ecf-4fc5-a37c-c1ef94253262")
    public void setNumeroAsiento(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.numeroAsiento = value;
    }

    @objid ("74625c23-af66-4de0-aacf-7a8cdc120a98")
    public String getPartido() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.partido;
    }

    @objid ("1c9153e5-1e35-4fc8-8e5a-e2c6359a36b1")
    public void setPartido(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.partido = value;
    }

}
