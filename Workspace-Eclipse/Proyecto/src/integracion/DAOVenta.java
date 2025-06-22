package integracion;

import java.util.List;

import negocio.dto.TVenta;

public interface DAOVenta {
	void crearVenta(TVenta venta) throws Exception;
    void actualizarVenta(TVenta venta) throws Exception;
    TVenta obtenerVenta(String codigo) throws Exception;
    List<TVenta> obtenerTodasVentas() throws Exception;

}
