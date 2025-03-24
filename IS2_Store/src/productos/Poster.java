package productos;

public class Poster extends Producto {

    private String tamaño;
    public Poster(final String tamaño) {
    	super(null, 0, 0);
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
