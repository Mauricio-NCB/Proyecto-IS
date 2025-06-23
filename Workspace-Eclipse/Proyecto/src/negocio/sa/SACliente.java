package negocio.sa;

import java.util.List;
import negocio.dto.TCliente;
import negocio.dto.TFactura;

public interface SACliente {
    boolean altaCliente(TCliente cliente);
    boolean eliminarCliente(int numSocio); 
    boolean modificarCliente(int numSocio, String direccion, String correo); 
    TCliente obtenerCliente(int numSocio);
    List<TCliente> listarClientes(); 
    List<TFactura> obtenerFacturasCliente(int numSocio) throws Exception; 
}