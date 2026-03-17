
package integracion;

import java.util.List;

import negocio.dto.TUsuario;

public interface DAOUsuario {
    void crearUsuario(TUsuario u);
    List<TUsuario> obtenerUsuarios();
    // Es buena idea hacer público el método de buscar
    TUsuario buscarUsuario(String id); 
    boolean actualizarUsuario(String id, String nuevoEmail, String nuevaContrasena, 
                              List<String> nuevosGustos, String nuevaPersonalidad);
    boolean eliminarUsuario(String id);
}
