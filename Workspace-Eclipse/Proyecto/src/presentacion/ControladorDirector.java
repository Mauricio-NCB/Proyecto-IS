package presentacion;

import java.util.List;

import negocio.dto.TDirector;
import negocio.sa.SADirector;
import negocio.sa.SADirectorImp;

public class ControladorDirector { 
    private static ControladorDirector instancia;
    private SADirector servicioDirector;

    private ControladorDirector() {
        this.servicioDirector = new SADirectorImp();
    }

    public static ControladorDirector getInstance() {
        if (instancia == null) {
            instancia = new ControladorDirector();
        }
        return instancia;
    }

    public void registrarDirector(String id, String nombre, Float sueldo, String contrasena) {
        TDirector nuevoDirector = new TDirector(id, nombre, sueldo, contrasena, "Director");
        boolean exito = servicioDirector.altaDirector(nuevoDirector);
        
        if (exito) {
            System.out.println("Director registrado correctamente");
        } else {
            System.out.println("Error al registrar el cliente");
        }
    }

    public void mostrarTodosLosDirectores() {
        System.out.println("\n--- Listado de Directores ---");
        List<TDirector> directores = servicioDirector.mostrarDirectores();

        if (directores == null || directores.isEmpty()) {
            System.out.println("No se encontraron directores registrados.");
        } else {
            for (TDirector director : directores) {
                System.out.println(director);
            }
        }
    }
}