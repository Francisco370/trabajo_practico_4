package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

@Component
public class Carrera {
	private int codCarrera;
	private String nombre;
	private int cantAnios;
	private boolean estado;
	
	public Carrera(){
		
	}

	public Carrera(int codCarrera, String nombre, int cantAnios, boolean estado) {
		this.codCarrera = codCarrera;
		this.nombre = nombre;
		this.cantAnios = cantAnios;
		this.estado = estado;
	}

	public int getCodCarrera() {
		return codCarrera;
	}

	public void setCodCarrera(int codCarrera) {
		this.codCarrera = codCarrera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantAnios() {
		return cantAnios;
	}

	public void setCantAnios(int cantAnios) {
		this.cantAnios = cantAnios;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}