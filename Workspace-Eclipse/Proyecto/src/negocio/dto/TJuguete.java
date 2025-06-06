package negocio.dto;

public class TJuguete extends TProducto {
    private String tipo;
    private String tamano;

    public TJuguete(final int ID, final String nombre, final float precio, final int stock, final String tipo, final String tamano) {
    	super(ID, nombre, precio, stock);
    	this.tipo = tipo;
    	this.tamano = tamano;
    }

    public TJuguete(final String nombre, final float precio, final int stock, final String tipo, final String tamano) {
    	super(nombre, precio, stock);
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

	@Override
	protected void validarDatosEspecificos() throws Exception {
		// TODO Auto-generated method stub
		if (tipo == null)
            throw new IllegalArgumentException("El tipo de juguete no puede estar vaío");
		if (tamano == null)
            throw new IllegalArgumentException("El tamaño del juguete no puede estar vaío");
			
	}

}