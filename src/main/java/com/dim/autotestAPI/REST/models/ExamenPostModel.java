package com.dim.autotestAPI.REST.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.dim.autotestAPI.entidades.AlumnoConID;

import es.mde.acing.utils.PreguntaExamen;

@Relation(itemRelation = "examen")
public class ExamenPostModel extends RepresentationModel<ExamenPostModel> {

	private LocalDate fecha;

	// Relaciones
	private AlumnoConID alumno;
	private List<PreguntaExamen> preguntas;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public AlumnoConID getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoConID alumno) {
		this.alumno = alumno;
	}

	public List<PreguntaExamen> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<PreguntaExamen> preguntas) {
		this.preguntas = preguntas;
	}

}
