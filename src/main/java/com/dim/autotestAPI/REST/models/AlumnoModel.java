package com.dim.autotestAPI.REST.models;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(itemRelation = "alumno")
public class AlumnoModel extends RepresentationModel<AlumnoModel> {

	private Long id;
	private String nombre;
	private String apellidos;
	private int numExamenes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getNumExamenes() {
		return numExamenes;
	}

	public void setNumExamenes(int numExamenes) {
		this.numExamenes = numExamenes;
	}

	@Override
	public String toString() {
		return "AlumnoModel [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}

}
