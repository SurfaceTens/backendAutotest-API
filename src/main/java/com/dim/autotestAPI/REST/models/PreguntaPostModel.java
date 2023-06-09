package com.dim.autotestAPI.REST.models;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mde.acing.utils.PreguntaExamen;
import es.mde.acing.utils.PreguntaImpl.Adjunto;

@Relation(itemRelation = "pregunta")
public class PreguntaPostModel extends RepresentationModel<PreguntaPostModel> {

	private String tematica;
	private int dificultad;
	private String enunciado;
	private String opcionCorrecta;
	private String opcionIncorrecta1;
	private String opcionIncorrecta2;
	private String opcionIncorrecta3;

	private List<PreguntaExamen> examenes;

	private String imagenBase64;
	private String videoURL;
	private Adjunto adjunto;

	public String getTematica() {
		return tematica;
	}

	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getOpcionCorrecta() {
		return opcionCorrecta;
	}

	public void setOpcionCorrecta(String opcionCorrecta) {
		this.opcionCorrecta = opcionCorrecta;
	}

	public String getOpcionIncorrecta1() {
		return opcionIncorrecta1;
	}

	public void setOpcionIncorrecta1(String opcionIncorrecta1) {
		this.opcionIncorrecta1 = opcionIncorrecta1;
	}

	public String getOpcionIncorrecta2() {
		return opcionIncorrecta2;
	}

	public void setOpcionIncorrecta2(String opcionIncorrecta2) {
		this.opcionIncorrecta2 = opcionIncorrecta2;
	}

	public String getOpcionIncorrecta3() {
		return opcionIncorrecta3;
	}

	public void setOpcionIncorrecta3(String opcionIncorrecta3) {
		this.opcionIncorrecta3 = opcionIncorrecta3;
	}

	public String getImagenBase64() {
		return imagenBase64;
	}

	public void setImagenBase64(String imagenBase64) {
		this.imagenBase64 = imagenBase64;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public Adjunto getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(Adjunto adjunto) {
		this.adjunto = adjunto;
	}

	public List<PreguntaExamen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<PreguntaExamen> examenes) {
		this.examenes = examenes;
	}

}
