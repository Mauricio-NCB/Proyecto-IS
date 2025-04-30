package negocio.sa;

import java.util.List;

import negocio.dto.TDirector;
import negocio.dto.TEmpleado;

public interface SADirector {
    boolean altaDirector(TDirector director);
    public void agregarEmpleadoADirector(String idDirector, TEmpleado empleado);
    public boolean eliminarDirector(String id);
    public boolean actualizaDatosDirector(String id, Float sueldo, String contrasena);
    public List<TDirector> mostrarDirectores();
}