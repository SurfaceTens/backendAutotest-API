package com.dim.autotestAPI.REST.models;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(itemRelation = "pregunta")
public class PreguntaQuickModel extends RepresentationModel<PreguntaQuickModel> {

	private Long id;
	private String tematica;
	private int dificultad;
	private String enunciado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTematica() {
		return tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

}
