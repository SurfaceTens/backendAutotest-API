package com.dim.autotestAPI.REST.models;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.dim.autotestAPI.entidades.AlumnoConID;

@Relation(itemRelation = "examen")
public class ExamenPostModel extends RepresentationModel<ExamenPostModel> {

	// Relaciones
	private AlumnoConID alumno;

	public AlumnoConID getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoConID alumno) {
		this.alumno = alumno;
	}

}
