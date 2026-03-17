package Presentacion;

import java.util.List;

import negocio.dto.TActividad;
import negocio.sa.SAActividad;
import negocio.sa.SAActividadImp;

public class ControladorActividad {

	private static ControladorActividad instancia;
	private SAActividad servicioActividad;

	private ControladorActividad() {
		servicioActividad = new SAActividadImp();
	}

	public static ControladorActividad getInstance() {

		if (instancia == null)
			instancia = new ControladorActividad();

		return instancia;
	}

	public boolean crearActividad(TActividad actividad) {

		try {
			servicioActividad.crearActividad(actividad);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public TActividad obtenerActividad(String id) {

		try {
			return servicioActividad.obtenerActividad(id);
		} catch (Exception e) {
			return null;
		}
	}

	public List<TActividad> listarActividades() {

		try {
			return servicioActividad.listarActividades();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean eliminarActividad(String id) {

		try {
			servicioActividad.eliminarActividad(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}