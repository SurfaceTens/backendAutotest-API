package com.dim.autotestAPI.REST.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.PreguntaPostModel;
import com.dim.autotestAPI.entidades.PreguntaConID;
import com.dim.autotestAPI.REST.controllers.PreguntaController;

import es.mde.acing.utils.ConImagen;
import es.mde.acing.utils.ConVideo;

@Component
public class PreguntaPostAssembler implements RepresentationModelAssembler<PreguntaConID, PreguntaPostModel> {

	@Override
	public PreguntaPostModel toModel(PreguntaConID entity) {
		PreguntaPostModel model = new PreguntaPostModel();
		model.setTematica(entity.getTematica());
		model.setDificultad(entity.getDificultad());
		model.setEnunciado(entity.getEnunciado());

		// Para la relacion
//			model.add(
//					linkTo(methodOn(PreguntaController.class).one(((PreguntaConID) entity).getId())).withSelfRel(),
//			     	linkTo(methodOn(PreguntaController.class).examenesPregunta(entity.getId())).withRel("examenes"),
//					);
		return model;
	}

	public PreguntaConID toEntity(PreguntaPostModel model) {
		PreguntaConID entity = new PreguntaConID();
		entity.setTematica(model.getTematica());
		entity.setDificultad(model.getDificultad());
		entity.setEnunciado(model.getEnunciado());

		// Para la releacion
//			Examenes

		return entity;
	}

}
