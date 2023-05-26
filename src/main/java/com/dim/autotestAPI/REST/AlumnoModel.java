package com.dim.autotestAPI.REST;

import java.util.List;

import es.mde.acing.utils.Pregunta;

public class AlumnoModel {

	private Long id;
	private List<Pregunta> propuestas;

	private String nombre;
	private String apellidos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Pregunta> getPropuestas() {
		return propuestas;
	}

	public void setPropuestas(List<Pregunta> propuestas) {
		this.propuestas = propuestas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Override
	public String toString() {
		return "AlumnoModel [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}

}
