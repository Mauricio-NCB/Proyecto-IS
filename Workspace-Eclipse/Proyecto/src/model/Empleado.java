package model;

public abstract class Empleado {
    private String identificador;
    private String nombre;
    private float sueldo;

    protected Empleado(final String identificador, final String nombre, final float sueldo) {};

    public String getIdentificador() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.identificador;
    }

    public String getNombre() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.nombre;
    }

    public void setNombre(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.nombre = value;
    }

    public float getSueldo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.sueldo;
    }

    public void setSueldo(final float value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.sueldo = value;
    }

}
