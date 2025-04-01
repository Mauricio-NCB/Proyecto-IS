package negocio.dto;

// SOLO DE PRUEBA PARA EL REGISTRO
// Aquí irá el modelo Cliente.java que creamos en Modelio pero necesitaba
// algo sencillo como prueba del registro

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