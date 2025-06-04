package negocio.sa;

import java.util.List;

import negocio.dto.TDirector;
import negocio.dto.TEmpleado;

public interface SADirector {
    void altaDirector(TDirector director);
    public boolean eliminarDirector(String id);
    public boolean actualizaDatosDirector(String id, Float sueldo, String contrasena);
    public List<TDirector> mostrarDirectores();
}