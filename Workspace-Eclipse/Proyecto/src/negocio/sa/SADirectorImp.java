package negocio.sa;

import integracion.DAODirector;
import integracion.DAODirectorImp;
import negocio.dto.TDirector;

public class SADirectorImp implements SADirector {
    private DAODirector daoDirector = new DAODirectorImp();

    @Override
    public boolean altaDirector(TDirector director) {
        return daoDirector.createDirector(director);
    }
}