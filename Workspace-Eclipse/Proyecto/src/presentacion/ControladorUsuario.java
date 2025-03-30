package presentacion;

import negocio.dto.TCliente;
import negocio.sa.SACliente;
import negocio.sa.SAClienteImp;

public class ControladorUsuario {
    private static ControladorUsuario instancia;
    private SACliente servicioCliente;

    private ControladorUsuario() {
        this.servicioCliente = new SAClienteImp();
    }

    public static ControladorUsuario getInstance() {
        if (instancia == null) {
            instancia = new ControladorUsuario();
        }
        return instancia;
    }

    public void registrarCliente(String dni, String nombre) {
        TCliente nuevoCliente = new TCliente(dni, nombre);
        boolean exito = servicioCliente.altaCliente(nuevoCliente);

        if (exito) {
            System.out.println("Cliente registrado correctamente");
        } else {
            System.out.println("Error al registrar el cliente");
        }
    }
}