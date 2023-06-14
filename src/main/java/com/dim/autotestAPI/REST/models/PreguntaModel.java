package com.dim.autotestAPI.REST.models;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mde.acing.utils.PreguntaExamen;
import es.mde.acing.utils.PreguntaImpl.Adjunto;

@Relation(itemRelation = "pregunta")
public class PreguntaModel extends RepresentationModel<PreguntaModel> {

	private Long id;
	private String tematica;
	private int dificultad;
	private String enunciado;
	private String opcionCorrecta;
	private String opcionIncorrecta1;
	private String opcionIncorrecta2;
	private String opcionIncorrecta3;
	
	// Relaciones
	private List<PreguntaExamen> examenes;
	private int numExamenes;

	// Clases Hijas
	private String imagenURL;
	private String videoURL;
	private Adjunto adjunto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public int getNumExamenes() {
		return numExamenes;
	}

	public void setNumExamenes(int numExamenes) {
		this.numExamenes = numExamenes;
	}

	public String getImagenURL() {
		return imagenURL;
	}

	public void setImagenURL(String imagenURL) {
		this.imagenURL = imagenURL;
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
