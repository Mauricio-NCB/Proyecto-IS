package presentacion;

import negocio.dto.TCliente;
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

    public void registrarCliente(String nombre, String direccion, String correo) {
        TCliente nuevoCliente = new TCliente(nombre, direccion, correo);
        boolean exito = servicioCliente.altaCliente(nuevoCliente);

        if (exito) {
            System.out.println("Cliente registrado correctamente");
        } else {
            System.out.println("Error al registrar el cliente");
        }
    }

    public void mostrarFacturasCliente(int numSocio) {
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
    }
}
