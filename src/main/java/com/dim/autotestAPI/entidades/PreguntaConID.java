package com.dim.autotestAPI.entidades;

import es.mde.acing.utils.PreguntaImpl;

public class PreguntaConID extends PreguntaImpl{

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PREGUNTA [" + getEnunciado() + "]";
	}

}
