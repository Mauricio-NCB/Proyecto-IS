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
    public boolean eliminarCliente(int numSocio) {
        return daoCliente.deleteCliente(numSocio);
    }

    @Override
    public boolean modificarCliente(int numSocio, String direccion, String correo) {
        return daoCliente.updateCliente(numSocio, direccion, correo);
    }

    @Override
    public TCliente obtenerCliente(int numSocio) {
        return daoCliente.readCliente(numSocio);
    }

    @Override
    public List<TCliente> listarClientes() {
        return daoCliente.getAllClientes(); 
    }

    @Override
    public List<Object[]> obtenerFacturasCliente(int numSocio) throws Exception {
        DAOFactura daoFactura = new DAOFacturaImp();
        return daoFactura.obtenerFacturasPorCliente(numSocio);
    }
}
