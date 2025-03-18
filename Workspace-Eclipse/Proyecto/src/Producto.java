import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("5e694bfa-ceb8-4db4-85bf-ff2a69c2959f")
public class Producto {
    @objid ("0515e565-efb0-4abb-8d3a-a046a4fd7dae")
    private int ID;

    @objid ("6a5fb0cb-04b7-4676-872d-6b549e0f782c")
    private String nombre;

    @objid ("82b2ea89-5dde-4ea6-84d6-8cf42d5b68ff")
    private float precio;

    @objid ("165b0410-2483-4215-b288-5305d6397cd1")
    private int stock;

    @objid ("76168da0-e9e1-4d48-9943-ea80472c21f3")
    protected Producto(final String nombre, final float precio, final int stock) {
    }

    @objid ("b1568765-afb4-4df7-9a02-24287cffbbc8")
    public void disminuirStock(final int cantidad) {
    }

    @objid ("893dea4a-099c-47af-8e48-86c8023ac99d")
    public void aumentarStock(final int cantidad) {
    }

    @objid ("85a23549-9689-4a49-9493-8192d280a405")
    public int getID() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.ID;
    }

    @objid ("3fefe405-2f50-454a-8ab8-9d2889b0380d")
    public String getNombre() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.nombre;
    }

    @objid ("98787298-d50b-457b-a656-8dc2e42b079e")
    public void setNombre(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.nombre = value;
    }

    @objid ("58735d8f-84e9-4ac1-a3ad-002f78814d3e")
    public float getPrecio() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.precio;
    }

    @objid ("b5de34fb-29ce-408c-bf5a-d5e1bcec3734")
    public void setPrecio(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.precio = value;
    }

    @objid ("57ebf902-b323-43ec-9161-a06fa724595e")
    public int getStock() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.stock;
    }

    @objid ("65af63e0-6dcc-427d-acd5-3daa8ae63db5")
    public void setStock(final int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.stock = value;
    }

}
