package negocio.dto;

public class Juguete extends Producto {
    private String tipo;
    private String tamano;

    public Juguete(final String tipo, final String tamano) {
    	super(tipo, 0, 0);
    }

    public String getTipo() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.tipo;
    }

    public void setTipo(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.tipo = value;
    }

    public String getTamano() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.tamano;
    }

    public void setTamano(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.tamano = value;
    }

}