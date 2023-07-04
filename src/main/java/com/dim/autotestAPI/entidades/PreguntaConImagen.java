package com.dim.autotestAPI.entidades;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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
	
	public static String calcularCabeceraBase64(String imagenBase64) {
		String respuesta = "";
	    Map<String, String> tiposMIME = new HashMap<>();
	    tiposMIME.put("/9j/", "image/jpeg");
	    tiposMIME.put("iVBORw0KG", "image/png");
	    tiposMIME.put("R0lGODlh", "image/gif");
	    tiposMIME.put("UklGR", "image/webp");
	    tiposMIME.put("Qk0x", "image/bmp");
	    tiposMIME.put("SUkq", "image/tiff");
	    tiposMIME.put("PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMDAiIGhlaWdodD0iMTAwIj4KICA8Y2lyY2xlIGN4PSI1MCIgY3k9IjUwIiByPSI1MCIgc3R5bGU9ImZpbGw6cmVkOyIgLz4KPC9zdmc+", "image/svg+xml");

		for (Map.Entry<String, String> entry : tiposMIME.entrySet()) {
			String inicio = entry.getKey();
			String tipo = entry.getValue();
			if (imagenBase64.startsWith(inicio)) {
				respuesta = "data:" + tipo + ";base64,";
			} else {
				System.out.println("No se encontró un tipo MIME válido.");
			}
		}
		return respuesta;
	}

	public static String quitarCabeceraBase64(String base64String) {
		String respuesta = base64String;
		String cabeceraFin = ";base64,";
		int finIndex = base64String.indexOf(cabeceraFin);
		if (finIndex != -1) {
			String contenidoBase64 = base64String.substring(finIndex + cabeceraFin.length());
			respuesta = contenidoBase64;
		} else {
			System.out.println("No se encontró el terminador de cabecera válido.");
		}
		return respuesta;
	}

}
