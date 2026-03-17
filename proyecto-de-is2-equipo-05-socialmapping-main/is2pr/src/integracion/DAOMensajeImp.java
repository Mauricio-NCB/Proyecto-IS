package integracion;

import java.util.ArrayList;
import java.util.List;

import negocio.dto.TMensaje;
public class DAOMensajeImp {
	
	private List<TMensaje> mensajes = new ArrayList<>();
	
	public void crearMensaje(TMensaje m) {
	    mensajes.add(m);
	}
	
	public List<TMensaje> obtenerMensajes() {
	    return mensajes;
	}
	
	public List<TMensaje> obtenerMensajesPorUsuario(int idUsuario) {
	    List<TMensaje> resultado = new ArrayList<>();
	    for (TMensaje m : mensajes) {
	        if (m.getIdUsuario() == idUsuario) {
	            resultado.add(m);
	        }
	    }
	    return resultado;
	}
	
	
	public boolean actualizarMensaje(int indice, String nuevoTexto) {
	    if (indice >= 0 && indice < mensajes.size()) {
	        mensajes.get(indice).setTexto(nuevoTexto);
	        return true;
	    }
	    return false;
	}
	
	public boolean eliminarMensaje(int indice) {
	    if (indice >= 0 && indice < mensajes.size()) {
	        mensajes.remove(indice);
	        return true;
	    }
	    return false;
	}
}
