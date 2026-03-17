package integracion;

import negocio.dto.TCliente;

public interface DAOCliente {
    void readCliente();

    void createCliente(TCliente cliente) throws Exception;

    void updateCliente(TCliente cliente) throws Exception;

    void deleteCliente();

}
