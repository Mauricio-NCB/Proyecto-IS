package negocio.sa;

import java.util.List;

import negocio.dto.Factura;
import negocio.dto.TDependiente;


public interface SADependiente {
	  boolean generarFactura(Factura factura);
	  List<TDependiente> listarDependientes();
}
