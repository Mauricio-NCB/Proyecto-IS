package negocio.sa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import integracion.DAODirector;
import integracion.DAODirectorImp;
import integracion.DAOEmpleado;
import integracion.DAOEmpleadoImp;
import negocio.dto.TDirector;
import negocio.dto.TEmpleado;
import util.HashUtil;
import integracion.BDConexion;

public class SADirectorImp implements SADirector {
    private DAODirector daoDirector = new DAODirectorImp();

    public boolean altaDirector(TDirector director) {
        if (director == null || director.getIdentificador() == null || director.getIdentificador().trim().isEmpty()) {
            System.err.println("Datos del director inválidos o identificador vacío.");
        }
        if (director.getCargo() == null || director.getCargo().trim().isEmpty()){
            System.err.println("El cargo del director no puede ser vacío.");
        }
        
        TDirector existe = daoDirector.obtenerPorId(director.getIdentificador());
		if (existe != null) {
		    System.err.println("Ya existe un director con el identificador: " + director.getIdentificador());
		}
        
        return daoDirector.createDirector(director);
    }

    public boolean eliminarDirector(String id) {
    	return daoDirector.eliminar(id);
    }
    
    public boolean actualizaDatosDirector(String id, Float sueldo, String contrasena) {
    	TDirector director = daoDirector.obtenerPorId(id);
    	if (sueldo != null) {director.setSueldo(sueldo);}
    	if (!contrasena.trim().isEmpty()) {director.setContrasena(HashUtil.hashPassword(contrasena));}
    	return daoDirector.actualizar(director);
    }
    
    public List<TDirector> mostrarDirectores() {
    	return daoDirector.obtenerTodos();
    }
}