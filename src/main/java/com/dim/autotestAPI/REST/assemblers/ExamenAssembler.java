package com.dim.autotestAPI.REST.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.ExamenModel;
import com.dim.autotestAPI.REST.models.ExamenPostModel;
import com.dim.autotestAPI.entidades.AlumnoConID;
import com.dim.autotestAPI.entidades.ExamenConID;

import es.mde.acing.utils.PreguntaExamen;

import com.dim.autotestAPI.REST.controllers.AlumnoController;
import com.dim.autotestAPI.REST.controllers.ExamenController;

@Component
public class ExamenAssembler implements RepresentationModelAssembler<ExamenConID, ExamenModel> {

	@Override
	public ExamenModel toModel(ExamenConID entity) {
		ExamenModel model = new ExamenModel();
		model.setId(entity.getId());
		model.setEntregado(entity.isEntregado());		
		
		model.setAlumnoID(((AlumnoConID) entity.getAlumno()).getId());
		model.setAlumnoDatos(entity.getAlumno().getNombre() + " " + entity.getAlumno().getApellidos());

		int numPreguntas = entity.getPreguntas() != null ? entity.getPreguntas().size() : 0;
		model.setNumPreguntas(numPreguntas);
		
		List<PreguntaExamen> preguntas = entity.getPreguntas();
		int aciertos = 0;
		int fallos = 0;
		for (PreguntaExamen pregunta : preguntas) {
			if (pregunta.isAcertada()) {
				aciertos++;
			} else if (pregunta.getRespuesta() != null) {
				fallos++;
			}
		}
		String nota = "NO APTO";
		if ((double) aciertos / numPreguntas >= ExamenModel.UMBRAL_APROBADO) {
			nota = "APTO";
		}
		
		model.setAciertos(aciertos);
		model.setFallos(fallos);
		model.setNota(nota);
		

		model.add(linkTo(methodOn(ExamenController.class).one(((ExamenConID) entity).getId())).withSelfRel(),
				linkTo(methodOn(AlumnoController.class).one(((AlumnoConID) entity.getAlumno()).getId()))
						.withRel("alumno"),
				linkTo(methodOn(ExamenController.class).preguntasExamen(entity.getId())).withRel("preguntas"));
		return model;
	}

	public ExamenConID toEntity(ExamenPostModel model) {
		ExamenConID entity = new ExamenConID();
		entity.setAlumno(model.getAlumno());
		entity.setEntregado(model.isEntregado());

		return entity;
	}

}
