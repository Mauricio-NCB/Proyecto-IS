package integracion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import negocio.dto.TDirector;

public interface DAODirector {
	public boolean createDirector(TDirector director);
	TDirector obtenerPorId(String id);
	List<TDirector> obtenerTodos();
	boolean actualizar(TDirector director);
	boolean eliminar(String id);
}
