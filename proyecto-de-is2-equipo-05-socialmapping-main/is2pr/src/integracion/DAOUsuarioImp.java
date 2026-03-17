package integracion;

import java.util.ArrayList;
import java.util.List;

import dominio.Usuario;
import negocio.dto.TUsuario;

public class DAOUsuarioImp implements DAOUsuario {
   
    private List<TUsuario> usuarios = new ArrayList<>();

    @Override
    public void crearUsuario(TUsuario u) {
        usuarios.add(u);
    }

    @Override
    public List<TUsuario> obtenerUsuarios() {
        return usuarios;
    }

    @Override
    public TUsuario buscarUsuario(String id) {
        for (TUsuario u : usuarios) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public boolean actualizarUsuario(String id, String nuevoEmail, String nuevaContrasena, 
                                     List<String> nuevosGustos, String nuevaPersonalidad) {
    	TUsuario u = buscarUsuario(id);
        if (u != null) {
            u.setEmail(nuevoEmail);
            u.setConstrasena(nuevaContrasena);
            u.setGustos(nuevosGustos);
            u.setPersonalidad(nuevaPersonalidad);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarUsuario(String id) {
    	TUsuario u = buscarUsuario(id);
        if (u != null) {
            usuarios.remove(u);
            return true;
        }
        return false;
    }
}
