package negocio.dto;

import java.util.List;
import negocio.dto.TUsuario;


public class TActividad {
	private List <TUsuario> usuarios;
	private int integrantes;
	private String localizacion;
	private int anyo;
	private int mes;
	private int dia;
	private String id;
	private String nombre;
	private String descripcion;
	
	public List<TUsuario> getUsuarios() {
	    return usuarios;
	}

	public void setUsuarios(List<TUsuario> usuarios) {
	    this.usuarios = usuarios;
	}

	public int getIntegrantes() {
	    return integrantes;
	}

	public void setIntegrantes(int integrantes) {
	    this.integrantes = integrantes;
	}

	public String getLocalizacion() {
	    return localizacion;
	}

	public void setLocalizacion(String localizacion) {
	    this.localizacion = localizacion;
	}

	public int getAnyo() {
	    return anyo;
	}

	public void setAnyo(int anyo) {
	    this.anyo = anyo;
	}

	public int getMes() {
	    return mes;
	}

	public void setMes(int mes) {
	    this.mes = mes;
	}

	public int getDia() {
	    return dia;
	}

	public void setDia(int dia) {
	    this.dia = dia;
	}

	public String getId() {
	    return id;
	}

	public void setId(String id) {
	    this.id = id;
	}

	public String getNombre() {
	    return nombre;
	}

	public void setNombre(String nombre) {
	    this.nombre = nombre;
	}

	public String getDescripcion() {
	    return descripcion;
	}

	public void setDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	}
}
