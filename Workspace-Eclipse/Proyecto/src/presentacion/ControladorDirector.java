package presentacion;

import negocio.dto.TDirector;
import negocio.sa.SADirector;
import negocio.sa.SADirectorImp;

public class ControladorDirector { 
    private static ControladorDirector instancia;
    private SADirector servicioCliente;

    private ControladorDirector() {
        this.servicioCliente = new SADirectorImp();
    }

    public static ControladorDirector getInstance() {
        if (instancia == null) {
            instancia = new ControladorDirector();
        }
        return instancia;
    }

    public void registrarDirector(String id, String nombre, String sueldo) {
        /*Director nuevoDirector = new Director(id, nombre, sueldo);
        boolean exito = servicioCliente.altaCliente(nuevoCliente);
        
        if (exito) {
            System.out.println("Director registrado correctamente");
        } else {
            System.out.println("Error al registrar el cliente");
        }*/
    }
}