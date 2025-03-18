import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("96c1d5af-d097-4bf1-9046-b010eaaa9a84")
public class Camiseta extends Producto {
    @objid ("8ca45cfe-5517-4bef-bcd3-0106a61f3eaa")
    private int talla;

    @objid ("67c4970f-fb47-45a4-a53a-f43ce28e8ca2")
    private String dorsalJugador;

    @objid ("e4667c1e-87ba-4cf0-99a5-56ee08677e0d")
    private int numeroJugador;

    @objid ("7dd2d637-5add-44cc-babd-67badbae40a0")
    public Camiseta(final int talla, final String dorsalJug, final int numJug) {
    }

    @objid ("bc689346-624c-44bd-9fd9-8a41d2b5674c")
    public int getTalla() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.talla;
    }

    @objid ("9afff20c-8004-4f1b-b3c3-b0caed633af8")
    public void setTalla(final int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.talla = value;
    }

    @objid ("f0b1858a-1f28-434c-bf62-1267e94bfd2e")
    public String getDorsalJugador() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.dorsalJugador;
    }

    @objid ("78023c12-9f77-4847-ac6e-4cdef8b026c6")
    public void setDorsalJugador(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.dorsalJugador = value;
    }

    @objid ("920afe52-b304-4e3d-a97d-71ec2b8616d3")
    public int getNumeroJugador() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.numeroJugador;
    }

    @objid ("f37b1845-555d-442a-b797-30e4936e22ac")
    public void setNumeroJugador(final int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.numeroJugador = value;
    }

}
