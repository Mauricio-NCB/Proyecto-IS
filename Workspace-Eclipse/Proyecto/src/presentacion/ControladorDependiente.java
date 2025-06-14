package presentacion;

import java.util.List;

import negocio.dto.TDependiente;
import negocio.dto.TFactura;
import negocio.sa.SADependiente;
import negocio.sa.SADependienteImp;

public class ControladorDependiente {
	private static ControladorDependiente instancia;
    private SADependiente servicioDependiente;

    private ControladorDependiente() {
        this.servicioDependiente = new SADependienteImp();
    }

    public static ControladorDependiente getInstance() {
        if (instancia == null) {
            instancia = new ControladorDependiente();
        }
        return instancia;
    }

    public void crearFactura(TFactura factura) {
        boolean exito = servicioDependiente.generarFactura(factura);

        if (exito) {
            System.out.println("La factura se ha generado correctamente");
        } else {
            System.out.println("Error al generar la factura");
        }
    }
    
    public List<TDependiente> listarDependientes() {
        return servicioDependiente.listarDependientes();
    }
}
