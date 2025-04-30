package negocio.sa;

import negocio.dto.Envio;
import integracion.DAOEnvio;
import integracion.DAOEnvioImp;
public class SAEnvioImp implements SAEnvio {

    private DAOEnvio daoEnvio = new DAOEnvioImp();

    @Override
    public boolean registrarEnvio(Envio envio) {
        return daoEnvio.crearEnvio(envio);
    }
}
