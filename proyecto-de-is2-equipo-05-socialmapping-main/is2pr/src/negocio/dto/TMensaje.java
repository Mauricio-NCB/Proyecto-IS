package negocio.dto;

public class TMensaje {

	
	private String contenido; 
	private int idUsuario;
	
	
	
	
	public TMensaje(String contenido, int idUsuario) {
		super();
		this.contenido = contenido;
		this.idUsuario = idUsuario;
	}
	public String getContenido() {
		return contenido;
	}
	public void setTexto(String contenido) {
		this.contenido = contenido;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	} 
	
	
}
