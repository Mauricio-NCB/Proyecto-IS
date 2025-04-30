package integracion;

import java.util.List;

public interface DAOFactura {
    List<Object[]> obtenerFacturasPorCliente(int numSocio);
}
