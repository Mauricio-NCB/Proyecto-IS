import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("7873372e-f8f9-4144-869f-aac7d3665a40")
public class Dependiente extends Empleado {
    @objid ("7b9291da-357b-4060-acdd-537a0d15f5e8")
    private float sumVentas;

    @objid ("a3fad58e-4f2a-431a-8d3e-3a9a1fc8932a")
    public List<Factura> genera = new ArrayList<Factura> ();

    @objid ("021d4db1-90dc-4107-8916-a1a8c1165a67")
    public Dependiente(final float SumVentas) {
    }

    @objid ("edf12dbd-7f4b-4afe-8ab0-fff9fe6539c3")
    public float getSumVentas() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.sumVentas;
    }

    @objid ("eda09e46-27c5-4deb-a345-cbba50741827")
    public void setSumVentas(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.sumVentas = value;
    }

}
