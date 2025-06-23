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
    /*public void mostrarFacturasCliente(int numSocio) {
        List<Object[]> facturas = servicioCliente.obtenerFacturasCliente(numSocio);

        if (facturas.isEmpty()) {
            System.out.println("Este cliente no tiene facturas.");
        } else {
            System.out.println("Facturas del cliente con número de socio: " + numSocio);
            for (Object[] f : facturas) {
                System.out.println("Código: " + f[0]
                    + " | Fecha: " + f[1]
                    + " | Hora: " + f[2]
                    + " | Importe: $" + f[3]);
            }
        }
    }*/

}
