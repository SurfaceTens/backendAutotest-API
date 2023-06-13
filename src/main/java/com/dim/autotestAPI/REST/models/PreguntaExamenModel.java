package com.dim.autotestAPI.REST.models;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.entidades.PreguntaConID;

@Relation(itemRelation = "preguntaexamen")
public class PreguntaExamenModel extends RepresentationModel<PreguntaExamenModel> {

	private Long id;
	private int respuesta;
	private boolean acertada;

	// Relaciones
	private ExamenConID examen;
	private PreguntaConID pregunta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
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
