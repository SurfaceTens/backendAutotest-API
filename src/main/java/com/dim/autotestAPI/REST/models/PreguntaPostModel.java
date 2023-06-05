package com.dim.autotestAPI.REST.models;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(itemRelation = "pregunta")
public class PreguntaPostModel extends RepresentationModel<PreguntaPostModel> {

	private String tematica;
	private int dificultad;
	private String enunciado;

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

	@Override
	public String toString() {
		return "PreguntaPostModel [tematica=" + tematica + ", dificultad=" + dificultad + ", enunciado=" + enunciado
				+ "]";
	}

}
