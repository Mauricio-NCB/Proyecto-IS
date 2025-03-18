import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("1d65d027-f262-4917-8336-f04a2323a1b2")
public class Juguete extends Producto {
    @objid ("418fa550-9a6f-41a7-8b05-dd05b624f6b1")
    private String tipo;

    @objid ("a760e0b1-d278-406b-9720-0888bc6ae762")
    private String tamaño;

    @objid ("ac9fa88e-e575-4fc6-a7c7-fd3cd6925d0c")
    public Juguete(final String tipo, final String tamaño) {
    }

    @objid ("6a8fd6f8-b569-4fda-9d2e-1a48ba08e5a1")
    public String getTipo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.tipo;
    }

    @objid ("f999d7b6-4e5c-4fd2-b241-832a408831e9")
    public void setTipo(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.tipo = value;
    }

    @objid ("2f0bb730-06c7-46b6-b256-1358e772333f")
    public String getTamaño() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.tamaño;
    }

    @objid ("529a70d0-e7d2-4052-b632-233def8eed97")
    public void setTamaño(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.tamaño = value;
    }

}
