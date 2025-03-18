import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("af853eec-3f1b-428b-9392-6cf57f874cbd")
public class Poster extends Producto {
    @objid ("0819696b-f7e1-4a48-a991-66f0cfbb9537")
    private String tamaño;

    @objid ("aa8bda30-7d3a-4a97-a9fe-fa9503b64ae5")
    public Poster(final String tamaño) {
    }

    @objid ("06f9f420-e6cc-4961-ace6-d79adb17c579")
    public String getTamaño() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.tamaño;
    }

    @objid ("5c4ac23e-da9e-4a77-8054-df46d5a02ca7")
    public void setTamaño(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.tamaño = value;
    }

}
