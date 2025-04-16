package presentacion;

import negocio.dto.TCliente;
import negocio.sa.SACliente;
import negocio.sa.SAClienteImp;

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
        } 
        else {
            System.out.println("Error al registrar el cliente");
        }
    }
}