package com.dim.autotestAPI.entidades;

import es.mde.acing.utils.PreguntaImpl;

public class PreguntaConID extends PreguntaImpl{

	private Long id;
	
	public static enum NivelDificultad {
		facil, dificil, aleatorio
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Pregunta con " + getAdjunto() + "[" + getEnunciado() + "]";
	}

}
