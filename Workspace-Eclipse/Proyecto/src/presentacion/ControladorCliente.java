package presentacion;

import negocio.dto.TCliente;
import negocio.dto.TFactura;
import negocio.sa.SACliente;
import negocio.sa.SAClienteImp;

import java.util.List;

public class ControladorCliente {
    private static ControladorCliente instancia;
    private SACliente servicioCliente;

    private ControladorCliente() {
        this.servicioCliente = new SAClienteImp();
    }

    public static ControladorCliente getInstance() {
        if (instancia == null) {
            instancia = new ControladorCliente();
        }
        return instancia;
    }

    public boolean registrarCliente(String nombre, String direccion, String correo) {
        TCliente nuevoCliente = new TCliente(nombre, direccion, correo);
        return servicioCliente.altaCliente(nuevoCliente);
    }

    public List<TCliente> listarClientes() {
        List<TCliente> clientes = servicioCliente.listarClientes();
        return clientes;
    }

    public List<TFactura> mostrarFacturasCliente(int numSocio) throws Exception {
        return servicioCliente.obtenerFacturasCliente(numSocio);
    }
}
