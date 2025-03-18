import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("e091cff1-70b3-4080-a5d9-36da8373a2f1")
public class Venta {
    @objid ("360754c1-6c52-4ace-b7ed-d5bd40590c2b")
    private int ID;

    @objid ("a91854c4-70cf-43e5-a553-1610fdd3b5e8")
    private float precio;

    @objid ("3c4a1053-69cb-4c4d-b97d-31198b9e409e")
    private int cantidad;

    @objid ("89bd787a-2b37-45e0-829e-681620e00e06")
    public Factura asociado;

    @objid ("6a36807d-4df5-4921-9a6b-a4edbfeb67d6")
    public List<Producto>  = new ArrayList<Producto> ();

    @objid ("898d8e80-8bca-4489-bb51-a5f61942bebd")
    public Cliente recibe;

    @objid ("72e99dcc-3804-4eeb-90cf-6df5e62f1877")
    public Venta(final float precio, final int cantidad) {
    }

    @objid ("399ce510-ff19-45e3-bd07-b303644a8619")
    public int getID() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.ID;
    }

    @objid ("41387c90-8f48-439e-8f9d-40b7bb531ed9")
    public float getPrecio() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.precio;
    }

    @objid ("fda89868-200a-411f-8d29-5c61ed9426c5")
    public void setPrecio(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.precio = value;
    }

    @objid ("4b70ce91-f2c3-4562-b128-a7229c3ec723")
    public int getCantidad() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.cantidad;
    }

    @objid ("693aef9e-ba1b-4114-8754-5765ed62e258")
    public void setCantidad(final int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.cantidad = value;
    }

}
