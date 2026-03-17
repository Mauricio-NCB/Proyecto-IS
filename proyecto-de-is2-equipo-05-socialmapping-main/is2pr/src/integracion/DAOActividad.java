package integracion;

import java.util.List;
import negocio.dto.TActividad;


public interface DAOActividad {

	void crearActividad(TActividad actividad) throws Exception;

	void actualizarActividad(TActividad actividad) throws Exception;

	TActividad obtenerActividad(String id) throws Exception;

	List<TActividad> obtenerTodasActividades() throws Exception;

	void eliminarActividad(String id) throws Exception;
}