package negocio.sa;

import java.util.List;

import integracion.DAOCliente;
import integracion.DAOClienteImp;
import integracion.DAOFactura;
import integracion.DAOFacturaImp;

import negocio.dto.TCliente;

public class SAClienteImp implements SACliente {

    private DAOCliente daoCliente = new DAOClienteImp();

    @Override
    public boolean altaCliente(TCliente cliente) {
        return daoCliente.createCliente(cliente);
    }

    @Override
    public List<TCliente> getAllClientes() {
        return daoCliente.getAllClientes(); 
    }

    @Override
    public List<Object[]> obtenerFacturasCliente(int numSocio) {
        DAOFactura daoFactura = new DAOFacturaImp();
        return daoFactura.obtenerFacturasPorCliente(numSocio);
    }
}
