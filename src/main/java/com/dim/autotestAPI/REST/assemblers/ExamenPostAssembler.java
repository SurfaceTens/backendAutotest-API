package com.dim.autotestAPI.REST.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.ExamenPostModel;
import com.dim.autotestAPI.REST.models.ExamenPostModel;
import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.REST.controllers.ExamenController;

@Component
public class ExamenPostAssembler implements RepresentationModelAssembler<ExamenConID, ExamenPostModel> {
	
	@Override
	public ExamenPostModel toModel(ExamenConID entity) {
		ExamenPostModel model = new ExamenPostModel();
		model.setFecha(entity.getFecha());
		
		// Para la relacion
//		model.add(
//				linkTo(methodOn(ExamenController.class).one(((ExamenConID) entity).getId())).withSelfRel(),
//		     	linkTo(methodOn(ExamenController.class).preguntasExamen(entity.getId())).withRel("preguntas")
//     			linkTo(methodOn(ExamenController.class).alumnosExamen(entity.getId())).withRel("alumnos")
//				);
		return model;
	}
	
	public ExamenConID toEntity(ExamenPostModel model) {
		ExamenConID entity = new ExamenConID();
		entity.setFecha(model.getFecha());
		
		// Para la releacion
//		Preguntas
//		Alumnos
		
		
		return entity;
	}

}
