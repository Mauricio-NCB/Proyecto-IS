package model;

public class Juguete extends Producto {
    private String tipo;
    private String tamaño;

    public Juguete(final String tipo, final String tamaño) {
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

    public String getTamaño() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.tamaño;
    }

    public void setTamaño(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.tamaño = value;
    }

}