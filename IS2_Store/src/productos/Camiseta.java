package productos;

public class Camiseta extends Producto {

    private int talla;
    private String dorsalJugador;
    private int numeroJugador;
    public Camiseta(final int talla, final String dorsalJug, final int numJug) {
    	super(dorsalJug, 0, 0);
    }

    public int getTalla() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.talla;
    }

    public void setTalla(final int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.talla = value;
    }

    public String getDorsalJugador() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.dorsalJugador;
    }

    public void setDorsalJugador(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.dorsalJugador = value;
    }

    public int getNumeroJugador() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.numeroJugador;
    }

    public void setNumeroJugador(final int value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.numeroJugador = value;
    }

}