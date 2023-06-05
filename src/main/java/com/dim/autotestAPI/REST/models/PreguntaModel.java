package com.dim.autotestAPI.REST.models;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mde.acing.utils.Alumno;

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
	private String opcionIncorrecta4;
	private int numExamenes;
	private Alumno alumno;

	// Clases Hijas
	private String imagenURL;
	private String videoURL;

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

	public String getOpcionIncorrecta4() {
		return opcionIncorrecta4;
	}

	public void setOpcionIncorrecta4(String opcionIncorrecta4) {
		this.opcionIncorrecta4 = opcionIncorrecta4;
	}

	public int getNumExamenes() {
		return numExamenes;
	}

	public void setNumExamenes(int numExamenes) {
		this.numExamenes = numExamenes;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
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

	@Override
	public String toString() {
		return "PreguntaModel [id=" + id + ", tematica=" + tematica + ", dificultad=" + dificultad + ", enunciado="
				+ enunciado + ", opcionCorrecta=" + opcionCorrecta + ", opcionIncorrecta1=" + opcionIncorrecta1
				+ ", opcionIncorrecta2=" + opcionIncorrecta2 + ", opcionIncorrecta3=" + opcionIncorrecta3
				+ ", opcionIncorrecta4=" + opcionIncorrecta4 + ", numExamenes=" + numExamenes + ", alumno=" + alumno
				+ ", imagenURL=" + imagenURL + ", videoURL=" + videoURL + "]";
	}

}
