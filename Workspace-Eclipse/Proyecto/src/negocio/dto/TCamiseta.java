package negocio.dto;

public class TCamiseta extends TProducto {

    private int talla;
    private String dorsalJugador;
    private int numeroJugador;
    public TCamiseta(final int ID, final String nombre, final float precio, final int stock,
    		final int talla, final String dorsalJug, final int numJug) {
    	super(ID, nombre, precio, stock);
    	this.talla = talla;
    	this.dorsalJugador = dorsalJug;
    	this.numeroJugador = numJug;
    }

    public TCamiseta(final String nombre, final float precio, final int stock,
            final int talla, final String dorsalJug, final int numJug) {
        super(nombre, precio, stock);
        this.talla = talla;
        this.dorsalJugador = dorsalJug;
        this.numeroJugador = numJug;
    }

    public int getTalla() {
        return this.talla;
    }

    public void setTalla(final int value) {
        this.talla = value;
    }

    public String getDorsalJugador() {
        return this.dorsalJugador;
    }

    public void setDorsalJugador(final String value) {
        this.dorsalJugador = value;
    }

    public int getNumeroJugador() {
        return this.numeroJugador;
    }

    public void setNumeroJugador(final int value) {
        this.numeroJugador = value;
    }

	@Override
	protected void validarDatosEspecificos() throws Exception {
		// TODO Auto-generated method stub
        if (talla <= 0)
            throw new IllegalArgumentException("La talla debe ser mayor que 0");
        if (dorsalJugador == null)
            throw new IllegalArgumentException("El dorsal no puede estar vacío");
        if (numeroJugador <= 0)
            throw new IllegalArgumentException("El número de jugador debe ser mayor que 0");
	}

}