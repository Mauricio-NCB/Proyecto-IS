package integracion;

import java.util.List;
import negocio.dto.TCliente;

public interface DAOCliente {
    boolean createCliente(TCliente cliente);
    boolean deleteCliente(int numSocio);
    boolean updateCliente(int numSocio, String direccion, String correo);
    TCliente readCliente(int numSocio);
    List<TCliente> getAllClientes(); 
}
