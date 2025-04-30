package integracion;

import java.util.List;
import negocio.dto.TCliente;

public interface DAOCliente {
    boolean createCliente(TCliente cliente);
    List<TCliente> getAllClientes(); 
}
