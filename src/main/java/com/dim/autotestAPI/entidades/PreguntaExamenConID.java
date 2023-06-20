package com.dim.autotestAPI.entidades;

import es.mde.acing.utils.PreguntaExamenImpl;

public class PreguntaExamenConID extends PreguntaExamenImpl {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PreguntaExamenConID [id=" + id + "]";
	}

}