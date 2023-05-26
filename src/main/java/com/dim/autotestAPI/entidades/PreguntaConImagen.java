package com.dim.autotestAPI.entidades;

import es.mde.acing.utils.conImagen;

public class PreguntaConImagen extends PreguntaConID implements conImagen{
	
	private String imagenURL;

	@Override
	public String getImagenURL() {
		return imagenURL;
	}

}
