package com.dim.autotestAPI.entidades;

import es.mde.acing.utils.AlumnoImpl;

public class AlumnoConID extends AlumnoImpl{

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "ALUMNO [" + getNombre() + "]";
	}

}
