package negocio.sa;

import java.util.List;
import negocio.dto.TCliente;

public interface SACliente {
    boolean altaCliente(TCliente cliente);
    boolean eliminarCliente(int numSocio); 
    boolean modificarCliente(int numSocio, String direccion, String correo); 
    TCliente obtenerCliente(int numSocio);
    List<TCliente> listarClientes(); // ← este es el nuevo método
    List<Object[]> obtenerFacturasCliente(int numSocio) throws Exception; // si ya lo tienes
}
