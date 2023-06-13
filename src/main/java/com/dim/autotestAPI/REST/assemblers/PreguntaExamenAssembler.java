package com.dim.autotestAPI.REST.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.PreguntaExamenModel;
import com.dim.autotestAPI.entidades.PreguntaExamenConID;
import com.dim.autotestAPI.REST.controllers.ExamenController;
import com.dim.autotestAPI.REST.controllers.PreguntaExamenController;

@Component
public class PreguntaExamenAssembler implements RepresentationModelAssembler<PreguntaExamenConID, PreguntaExamenModel> {

	@Override
	public PreguntaExamenModel toModel(PreguntaExamenConID entity) {
		PreguntaExamenModel model = new PreguntaExamenModel();
		model.setId(entity.getId());
		model.setAcertada(entity.isAcertada());
		model.setRespuesta(entity.getRespuesta());
		
		// Para la relacion
		model.add(
//				linkTo(methodOn(PreguntaExamenController.class).one(((PreguntaExamenConID) entity).getId())).withSelfRel(),
		     	linkTo(methodOn(ExamenController.class).one(entity.getId())).withRel("examenes") // No hace falta este endpoint por ahora
				);
		return model;
	}

	public PreguntaExamenConID toEntity(PreguntaExamenModel model) {
		PreguntaExamenConID entity = new PreguntaExamenConID();
		entity.setId(model.getId());
		entity.setAcertada(model.isAcertada());
		entity.setRespuesta(model.getRespuesta());

		// Para la releacion
		entity.setExamen(model.getExamen());
		entity.setPregunta(model.getPregunta());
		return entity;
	}

}
