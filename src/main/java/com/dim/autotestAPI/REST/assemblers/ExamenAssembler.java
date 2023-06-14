package com.dim.autotestAPI.REST.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.ExamenModel;
import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.REST.controllers.AlumnoController;
import com.dim.autotestAPI.REST.controllers.ExamenController;
import com.dim.autotestAPI.REST.controllers.PreguntaController;

@Component
public class ExamenAssembler implements RepresentationModelAssembler<ExamenConID, ExamenModel> {
	
	@Override
	public ExamenModel toModel(ExamenConID entity) {
		ExamenModel model = new ExamenModel();
		model.setId(entity.getId());
		
		// Para sacar conclusiones de la entidad
		int numPreguntas = entity.getPreguntas() != null ? entity.getPreguntas().size() : 0;
		model.setNumPreguntas(numPreguntas);

		// Para la relacion
		model.add(
				linkTo(methodOn(ExamenController.class).one(((ExamenConID) entity).getId())).withSelfRel(),
				linkTo(methodOn(AlumnoController.class).one(entity.getId())).withRel("alumno"),
		     	linkTo(methodOn(PreguntaController.class).all()).withRel("preguntas")
				);
		return model;
	}
	
	public ExamenConID toEntity(ExamenModel model) {
		ExamenConID entity = new ExamenConID();
		entity.setId(model.getId());
		entity.setAlumno(model.getAlumno());

		// Para la releacion
		// No necesitamos nada aqui.
		return entity;
	}

}
