package integracion;

import java.util.ArrayList;
import java.util.List;

import dominio.Chat;

public class ChatController {
	
	
	
	private List<Chat> chats = new ArrayList<>();
	
	
	
	//create
	public void crearChat(Chat chat) {
	    chats.add(chat);
	}
	
	//read
	public List<Chat> obtenerChats() {
	    return chats;
	}
	
	public Chat obtenerChatPorId(String id) {
	    for (Chat c : chats) {
	        if (c.getId().equals(id)) {
	            return c;
	        }
	    }
	    return null;
	}
	
	//update
	public boolean actualizarChat(String id, String nuevaDescripcion, int nuevosIntegrantes) {
	    Chat c = obtenerChatPorId(id);
	    if (c != null) {
	        c.setDescripcion(nuevaDescripcion);
	        c.setIntegrantes(nuevosIntegrantes);
	        return true;
	    }
	    return false;
	}
	
	//delete
	public boolean eliminarChat(String id) {
	    Chat c = obtenerChatPorId(id);
	    if (c != null) {
	        chats.remove(c);
	        return true;
	    }
	    return false;
	}
	
}
