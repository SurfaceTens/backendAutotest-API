package com.dim.autotestAPI.entidades;

import java.util.Base64;

import es.mde.acing.utils.ConImagen;

public class PreguntaConImagen extends PreguntaConID implements ConImagen{
	
	private byte[] imagen;

	@Override
	public String getImagenBase64() {
		return Base64.getEncoder().encodeToString(imagen);
	}
	@Override
	public void setImagenBase64(String imagenBase64) {
		this.imagen = Base64.getDecoder().decode(imagenBase64);
	}
	
	@Override
	public boolean esImagenValida() {
		// No es nula y no esta vacia
        return imagen != null && imagen.length > 0;
    }
	
	@Override
	public Adjunto getAdjunto() {
		return Adjunto.imagen;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
}
