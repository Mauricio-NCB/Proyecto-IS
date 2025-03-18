import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("26d94a5a-426d-43df-b764-256cd33f72f2")
public class Envio {
    @objid ("07f69de1-af18-42b6-a1b7-35434d3ea75e")
    private String ID;

    @objid ("f147e832-9273-4636-8b14-78a73956e965")
    private float coste;

    @objid ("4f771b2e-360a-499b-a9aa-f7dba6bc4973")
    private String direccion;

    @objid ("0e1f5024-86a1-497b-afda-6792d778c477")
    private String estado;

    @objid ("cac95cb9-c326-47d2-8db2-5fd65d643951")
    private Date fechaEnvio;

    @objid ("ab104e5a-4dd3-4bc4-84a1-c5caf4e95e9a")
    private Date fechaEntrega;

    @objid ("1c41fc6b-97b4-4957-90cd-f8d7abc25f12")
    public Envio(final float coste, final String direccion, final String estado, final String fechaEnvio, final String fechaEntrega) {
    }

    @objid ("c2eb84cc-e568-4508-beaa-4076d281d033")
    public String getID() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.ID;
    }

    @objid ("e8cc3a45-eca4-4aff-aeff-3d789cefefaf")
    public float getCoste() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.coste;
    }

    @objid ("89ad8324-6d25-443f-9457-4a5fede41d1b")
    public void setCoste(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.coste = value;
    }

    @objid ("f9e4db9b-0afc-40b3-b01b-c14b225d8cb5")
    public String getDireccion() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.direccion;
    }

    @objid ("b4155985-4b67-43e4-9aa0-9be842e602fe")
    public void setDireccion(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.direccion = value;
    }

    @objid ("cdc4b6ff-ff66-47c8-b728-95a7cce85670")
    public String getEstado() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.estado;
    }

    @objid ("0f74b881-9128-4735-9a5a-0fea7f105f29")
    public void setEstado(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.estado = value;
    }

    @objid ("4345ab9c-e44e-46da-8f8c-ff14e7119ada")
    public Date getFechaEnvio() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.fechaEnvio;
    }

    @objid ("135f58ad-bfa3-448e-8476-422bcfebc7b6")
    public void setFechaEnvio(final Date value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.fechaEnvio = value;
    }

    @objid ("42af0e3f-8439-4b2a-86e1-91e16c30d05f")
    public Date getFechaEntrega() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.fechaEntrega;
    }

    @objid ("61ba7af2-fdf2-408b-a490-1adac9d44651")
    public void setFechaEntrega(final Date value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.fechaEntrega = value;
    }

}
