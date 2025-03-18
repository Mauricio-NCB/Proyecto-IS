import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("664d1f2c-587f-43ea-919e-4e8caf546f79")
public class Director extends Empleado {
    @objid ("bb36d594-66d5-45d6-b425-cf603abd25bc")
    private String cargo;

    @objid ("44ae3ac3-aa14-46e0-8c35-499b1e4b94de")
    public List<Dependiente> gestionar = new ArrayList<Dependiente> ();

    @objid ("1f31567e-b57b-418b-af42-8fb2ef67ed58")
    public List<Venta> revisar = new ArrayList<Venta> ();

    @objid ("828478be-bfa2-4b8f-9f14-916e27617a27")
    public Director(final String cargo) {
    }

    @objid ("144d302b-c2f1-447a-9d56-f72a2d53acd7")
    public String getCargo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.cargo;
    }

    @objid ("22300580-b92e-4c3e-81c8-f6aceb1c3b42")
    public void setCargo(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.cargo = value;
    }

}
