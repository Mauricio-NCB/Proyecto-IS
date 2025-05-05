package negocio.sa;

import negocio.dto.Factura;
import negocio.dto.TDependiente;

import java.util.List;

import integracion.DAODependiente;
import integracion.DAODependienteImp;

public class SADependienteImp implements SADependiente{
    private DAODependiente daoFactura = new DAODependienteImp();

    @Override
    public boolean generarFactura(Factura factura) {
        return daoFactura.crearFactura(factura);
    }
    public List<TDependiente> listarDependientes() {
        return daoFactura.listarDependientes();
    }
}
