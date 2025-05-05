package negocio.sa;

import java.util.List;

import negocio.dto.TDependiente;
import negocio.dto.TFactura;


public interface SADependiente {
	  boolean generarFactura(TFactura factura);
	  List<TDependiente> listarDependientes();
	  boolean eliminarDependiente(String id);
}
