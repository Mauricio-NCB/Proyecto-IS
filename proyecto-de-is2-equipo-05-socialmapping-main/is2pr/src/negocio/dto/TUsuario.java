
package negocio.dto;
import java.util.List;

public class TUsuario {
	private String id;
	private String email;
	private String constraseña;
	private List <String> gustos;
	private String personalidad;
	
	
	public String getId() {
	    return id;
	}

	public void setId(String id) {
	    this.id = id;
	}

	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public String getConstrasena() {
	    return constraseña;
	}

	public void setConstrasena(String constraseña) {
	    this.constraseña = constraseña;
	}

	public List<String> getGustos() {
	    return gustos;
	}

	public void setGustos(List<String> gustos) {
	    this.gustos = gustos;
	}

	public String getPersonalidad() {
	    return personalidad;
	}

	public void setPersonalidad(String personalidad) {
	    this.personalidad = personalidad;
	}
}
