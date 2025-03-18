import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("01f9bc95-ffd2-4b65-a368-37c0125aaf9c")
public abstract class Empleado {
    @objid ("0ca9002c-e776-4e4d-8076-19438bd01cae")
    private String identificador;

    @objid ("421339e4-ee9b-426c-8159-3fb3da76a68d")
    private String nombre;

    @objid ("a691bcf2-d173-4d31-b04a-ac486a8eb891")
    private float sueldo;

    @objid ("0281cbd4-02cc-4d8d-bc98-dc26c7453c9a")
    protected Empleado(final String identificador, final String nombre, final float sueldo);

    @objid ("48c2e4aa-15c3-4a69-8ce3-ad34e524ab1c")
    public String getIdentificador() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.identificador;
    }

    @objid ("043226e6-595e-4351-8266-620184fe9574")
    public String getNombre() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.nombre;
    }

    @objid ("d354c739-3fec-4d73-a49e-4c39957c9b1f")
    public void setNombre(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.nombre = value;
    }

    @objid ("94333eb1-13f1-4762-9ede-2e113c3e155e")
    public float getSueldo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.sueldo;
    }

    @objid ("bd20d96c-96af-469b-843f-94caa01e2058")
    public void setSueldo(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.sueldo = value;
    }

}
