package integracion;

import java.util.List;

import negocio.dto.TDirector;

public interface DAODirector {
	public boolean createDirector(TDirector director);
	TDirector obtenerPorId(String id);
	List<TDirector> obtenerTodos();
	void actualizar(TDirector director);
	void eliminar(String id);
}
