package negocio.sa;

import integracion.DAOCliente;
import integracion.DAOClienteImp;
import negocio.dto.TCliente;

public class SAClienteImp implements SACliente {
    private DAOCliente daoCliente = new DAOClienteImp();

    @Override
    public boolean altaCliente(TCliente cliente) {
        return daoCliente.createCliente(cliente);
    }
}