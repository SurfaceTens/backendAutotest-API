package com.dim.autotestAPI.entidades;

import es.mde.acing.utils.ExamenImpl;

public class ExamenConID extends ExamenImpl {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ExamenConID [id=" + id + "]";
	}

}
