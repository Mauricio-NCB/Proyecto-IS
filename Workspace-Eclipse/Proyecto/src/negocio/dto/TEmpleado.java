package negocio.dto;

public abstract class TEmpleado {
    private String identificador;
    private String nombre;
    private float sueldo;
    private String contrasena;

    protected TEmpleado(final String identificador, final String nombre, final float sueldo, final String contrasena) {
    	this.identificador = identificador;
    	this.nombre = nombre;
    	this.sueldo = sueldo;
    	this.contrasena = contrasena;
    };

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
    
    public String getContrasena() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.contrasena;
    }

    public void setContrasena(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.contrasena = value;
    }
    
    public String toString() {
    	return "";
    }

}
