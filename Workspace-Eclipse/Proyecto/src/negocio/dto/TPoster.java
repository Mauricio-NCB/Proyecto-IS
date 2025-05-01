package negocio.dto;

public class TPoster extends TProducto {

    private String tamano;
    public TPoster(final int ID, final String nombre, final float precio, final int stock, final String tamano) {
    	super(ID ,nombre, precio, stock);
    	this.tamano = tamano;
    }
    
    public String getTamano() {
        return this.tamano;
    }
    
    public void setTamano(final String value) {
        this.tamano = value;
    }

}
