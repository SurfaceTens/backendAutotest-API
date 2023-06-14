package com.dim.autotestAPI.REST.models;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.entidades.PreguntaConID;

import es.mde.acing.utils.Pregunta;

@Relation(itemRelation = "preguntaexamen")
public class PreguntaExamenModel extends RepresentationModel<PreguntaExamenModel> {

	private Long id;
	private String respuesta;
	private String correcta;
	private String[] incorrectas;
	private boolean acertada;

	// Relaciones
	private String enunciado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getCorrecta() {
		return correcta;
	}

	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}

	public boolean isAcertada() {
		return acertada;
	}

	public void setAcertada(boolean acertada) {
		this.acertada = acertada;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String[] getIncorrectas() {
		return incorrectas;
	}

	public void setIncorrectas(String[] incorrectas) {
		this.incorrectas = incorrectas;
	}

}
