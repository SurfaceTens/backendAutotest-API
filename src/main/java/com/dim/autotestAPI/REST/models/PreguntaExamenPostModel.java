package com.dim.autotestAPI.REST.models;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.entidades.PreguntaConID;

@Relation(itemRelation = "preguntaexamen")
public class PreguntaExamenPostModel extends RepresentationModel<PreguntaExamenPostModel> {

	private String respuesta;
	private String correcta;
	private boolean acertada;

	private ExamenConID examen;
	private PreguntaConID pregunta;

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

	public ExamenConID getExamen() {
		return examen;
	}

	public void setExamen(ExamenConID examen) {
		this.examen = examen;
	}

	public PreguntaConID getPregunta() {
		return pregunta;
	}

	public void setPregunta(PreguntaConID pregunta) {
		this.pregunta = pregunta;
	}

}
