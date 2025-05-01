package negocio.dto;

public class TJuguete extends TProducto {
    private String tipo;
    private String tamano;

    public TJuguete(final int ID, final String nombre, final float precio, final int stock, final String tipo, final String tamano) {
    	super(ID, nombre, precio, stock);
    	this.tipo = tipo;
    	this.tamano = tamano;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(final String value) {
        this.tipo = value;
    }

    public String getTamano() {
        return this.tamano;
    }

    public void setTamano(final String value) {
        this.tamano = value;
    }

}