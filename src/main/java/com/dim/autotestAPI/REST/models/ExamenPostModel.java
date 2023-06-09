package com.dim.autotestAPI.REST.models;

import java.time.LocalDate;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(itemRelation = "examen")
public class ExamenPostModel extends RepresentationModel<ExamenPostModel> {

	private LocalDate fecha;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}
