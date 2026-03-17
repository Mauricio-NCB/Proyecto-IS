package negocio.dto;

public class  TCliente {
    private int id;

    private String dni;

    private String nombre;

    private boolean activo;

    public int getId() {
    	return this.id;
    }

    public void setId(int id) {
    	this.id = id;
    }

    public String getDni() {
    	return this.dni;
    }

    public void setDni(String dni) {
    	this.dni = dni;
    }

    public String getNombre() {
    	return this.nombre;
    }

    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }

    public boolean getActivo() {
    	return this.activo;
    }

    public void setActivo(boolean activo) {
    	this.activo = activo;
    }

    public void tCliente() {
    }

    public String toString() {
    	return "a";
    }
}
