package negocio.sa;

import negocio.dto.TDependiente;
import negocio.dto.TFactura;

import java.util.List;

import integracion.DAODependiente;
import integracion.DAODependienteImp;

public class SADependienteImp implements SADependiente{
    private DAODependiente daoFactura = new DAODependienteImp();

    @Override
    public boolean generarFactura(TFactura factura) {
        return daoFactura.crearFactura(factura);
    }
    public List<TDependiente> listarDependientes() {
        return daoFactura.listarDependientes();
    }

    @Override
    public boolean eliminarDependiente(String id) {
    	return daoFactura.eliminar(id);
    }
}
