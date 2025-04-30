package negocio.sa;

import java.util.List;
import negocio.dto.TCliente;

public interface SACliente {
    boolean altaCliente(TCliente cliente);
    List<TCliente> getAllClientes(); // ← este es el nuevo método
    List<Object[]> obtenerFacturasCliente(int numSocio); // si ya lo tienes
}
