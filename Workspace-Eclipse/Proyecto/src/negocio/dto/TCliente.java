package negocio.dto;

public class TCliente {
    private int id;
    private String dni;
    private String nombre;

    public TCliente(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return "Cliente{DNI='" + dni + "', Nombre='" + nombre + "'}";
    }
}