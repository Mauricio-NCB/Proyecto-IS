package negocio.dto;

public class TPoster extends TProducto {

    private String tamano;
    
    public TPoster(final int ID, final String nombre, final float precio, final int stock, final String tamano) {
    	super(ID ,nombre, precio, stock);
    	this.tamano = tamano;
    }

    public TPoster(final String nombre, final float precio, final int stock, final String tamano) {
    	super(nombre, precio, stock);
    	this.tamano = tamano;
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
		if (tamano == null)
            throw new IllegalArgumentException("El tamaño del poster no puede estar vaío");
			
	}

}
