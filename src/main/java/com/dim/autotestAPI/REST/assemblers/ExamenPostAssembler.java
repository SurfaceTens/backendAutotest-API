package com.dim.autotestAPI.REST.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.ExamenPostModel;
import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.REST.controllers.AlumnoController;
import com.dim.autotestAPI.REST.controllers.ExamenController;
import com.dim.autotestAPI.REST.controllers.PreguntaController;

@Component
public class ExamenPostAssembler implements RepresentationModelAssembler<ExamenConID, ExamenPostModel> {
	
	@Override
	public ExamenPostModel toModel(ExamenConID entity) {
		ExamenPostModel model = new ExamenPostModel();
		model.setFecha(entity.getFecha());
		
		// Para la relacion
		model.add(
				linkTo(methodOn(ExamenController.class).one(((ExamenConID) entity).getId())).withSelfRel(),
				linkTo(methodOn(AlumnoController.class).one(entity.getId())).withRel("alumno"),
		     	linkTo(methodOn(PreguntaController.class).all()).withRel("preguntas")
				);
		return model;
	}
	
	public ExamenConID toEntity(ExamenPostModel model) {
		ExamenConID entity = new ExamenConID();
		entity.setFecha(model.getFecha());
		entity.setAlumno(model.getAlumno());
		entity.setPreguntas(model.getPreguntas());
		
		// Para la releacion
		// No necesitamos nada aqui.
		return entity;
	}

}
