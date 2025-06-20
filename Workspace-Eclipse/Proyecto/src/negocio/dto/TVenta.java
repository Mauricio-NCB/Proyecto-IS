package negocio.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TVenta {

	private String codigo;
	private LocalDate fecha;
    private LocalTime hora;
    private TCliente cliente;
    private TDependiente dependiente;
    private TFactura factura;
    private List<TLineaVenta> lineasVenta = new ArrayList<>();
	
	public TVenta(String codigo, LocalDate fecha, LocalTime hora, TCliente cliente,  TDependiente dependiente) {
		this.codigo = codigo;
		this.fecha = fecha;
		this.hora = hora;
		this.dependiente = dependiente;
		this.cliente = cliente;
	}
	
	public String getCodigo() {
        return this.codigo;
    }
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(final LocalDate localDate) {
        this.fecha = localDate;
    }

    public LocalTime getHora() {
        return this.hora;
    }

    public void setHora(final LocalTime localTime) {
        this.hora = localTime;
    }
    
	public void setTiene(TCliente cliente) {
		this.cliente = cliente;
	}
	
	public TCliente getTiene() {
		return cliente;
	}

	public void setDependiente(TDependiente dep) {
		this.dependiente = dep;
	}

	public TDependiente getDependiente() {
		return dependiente;
	}
	
	public void setFactura(TFactura factura) {
		this.factura = factura;
	}
	
	public TFactura getFactura() {
		return factura;
	}
	
	public List<TLineaVenta> getLineasVenta() {
	        return new ArrayList<>(lineasVenta); 
	}
	 
	public void setLineasVenta(List<TLineaVenta> lineasVenta) {
	        this.lineasVenta = new ArrayList<>(lineasVenta);
    }
	
	public void addLineaVenta(TLineaVenta l) {
		this.lineasVenta.add(l);
	}
	
	public double getImporteTotal() {
		double aux = 0;
		for (TLineaVenta l: lineasVenta) {
			aux += l.getPrecioTotal();
		}
		return aux;
	}
	
}