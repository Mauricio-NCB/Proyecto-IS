package negocio.sa;

import java.util.List;
import negocio.dto.TActividad;

public interface SAActividad {

	void crearActividad(TActividad actividad) throws Exception;

	void actualizarActividad(TActividad actividad) throws Exception;

	TActividad obtenerActividad(String id) throws Exception;

	List<TActividad> listarActividades() throws Exception;

	void eliminarActividad(String id) throws Exception;
}