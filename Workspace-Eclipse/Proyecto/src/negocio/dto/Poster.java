package negocio.dto;

public class Poster extends Producto {

    private String tamano;
    public Poster(final String tamano) {
    	super(null, 0, 0);
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
