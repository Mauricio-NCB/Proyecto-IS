package negocio.sa;

import java.util.List;

import integracion.DAOActividad;
import integracion.DAOActividadImp;
import negocio.dto.TActividad;

public class SAActividadImp implements SAActividad {

	private DAOActividad daoActividad;

	public SAActividadImp() {
		daoActividad = new DAOActividadImp();
	}

	@Override
	public void crearActividad(TActividad actividad) throws Exception {

		if (actividad == null)
			throw new Exception("Actividad invalida");

		daoActividad.crearActividad(actividad);
	}

	@Override
	public void actualizarActividad(TActividad actividad) throws Exception {

		if (actividad == null)
			throw new Exception("Actividad invalida");

		daoActividad.actualizarActividad(actividad);
	}

	@Override
	public TActividad obtenerActividad(String id) throws Exception {

		TActividad actividad = daoActividad.obtenerActividad(id);

		if (actividad == null)
			throw new Exception("Actividad no encontrada");

		return actividad;
	}

	@Override
	public List<TActividad> listarActividades() throws Exception {

		return daoActividad.obtenerTodasActividades();
	}

	@Override
	public void eliminarActividad(String id) throws Exception {

		daoActividad.eliminarActividad(id);
	}
}