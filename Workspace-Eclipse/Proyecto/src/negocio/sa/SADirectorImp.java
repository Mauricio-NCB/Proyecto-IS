package negocio.sa;

import integracion.DAODirector;
import integracion.DAODirectorImp;
import negocio.dto.Director;

public class SADirectorImp implements SADirector {
    private DAODirector daoDirector = new DAODirectorImp();

    @Override
    public boolean altaDirector(Director director) {
        return daoDirector.createDirector(director);
    }
}