package negocio.sa;

import negocio.dto.Factura;
import integracion.DAODependiente;
import integracion.DAODependienteImp;

public class SADependienteImp implements SADependiente{
    private DAODependiente daoFactura = new DAODependienteImp();

    @Override
    public boolean generarFactura(Factura factura) {
        return daoFactura.crearFactura(factura);
    }
}
