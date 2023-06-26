package com.dim.autotestAPI.REST.models;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.dim.autotestAPI.entidades.AlumnoConID;

@Relation(itemRelation = "examen")
public class ExamenModel extends RepresentationModel<ExamenModel> {

	private Long id;

	private AlumnoConID alumno;
	private String alumnoDatos;
	private int numPreguntas;
	private boolean entregado;
	private String nota;
	private int aciertos;
	private int fallos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AlumnoConID getAlumno() {
		return alumno;
	}

	public int getNumPreguntas() {
		return numPreguntas;
	}

	public void setNumPreguntas(int numPreguntas) {
		this.numPreguntas = numPreguntas;
	}

	public void setAlumno(AlumnoConID alumno) {
		this.alumno = alumno;
	}

	public boolean isEntregado() {
		return entregado;
	}

	public void setEntregado(boolean entregado) {
		this.entregado = entregado;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public int getAciertos() {
		return aciertos;
	}

	public void setAciertos(int aciertos) {
		this.aciertos = aciertos;
	}

	public int getFallos() {
		return fallos;
	}

	public void setFallos(int fallos) {
		this.fallos = fallos;
	}

	public String getAlumnoDatos() {
		return alumnoDatos;
	}

	public void setAlumnoDatos(String alumnoDatos) {
		this.alumnoDatos = alumnoDatos;
	}

}
