package com.dim.autotestAPI.REST.models;

import java.time.LocalDate;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mde.acing.utils.Alumno;

@Relation(itemRelation = "examen")
public class ExamenModel extends RepresentationModel<ExamenModel> {

	private Long id;
	private LocalDate fecha;
	
	// Relaciones
	private Alumno alumno;
	private int numPreguntas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public int getNumPreguntas() {
		return numPreguntas;
	}

	public void setNumPreguntas(int numPreguntas) {
		this.numPreguntas = numPreguntas;
	}

}
