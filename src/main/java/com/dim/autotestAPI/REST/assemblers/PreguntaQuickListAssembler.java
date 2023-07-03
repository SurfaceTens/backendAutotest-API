package com.dim.autotestAPI.REST.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.PreguntaModel;
import com.dim.autotestAPI.REST.models.PreguntaQuickModel;
import com.dim.autotestAPI.entidades.PreguntaConID;
import com.dim.autotestAPI.REST.controllers.PreguntaController;

import es.mde.acing.utils.ConImagen;
import es.mde.acing.utils.ConVideo;
import es.mde.acing.utils.Pregunta;
import es.mde.acing.utils.PreguntaImpl.Adjunto;

@Component
public class PreguntaQuickListAssembler<T extends Pregunta> implements RepresentationModelAssembler<T, PreguntaQuickModel> {

	@Override
	public PreguntaQuickModel toModel(T entity) {
		PreguntaQuickModel model = new PreguntaQuickModel();
		model.setId(((PreguntaConID) entity).getId());
		model.setTematica(entity.getTematica());
		model.setDificultad(entity.getDificultad());
		model.setEnunciado(entity.getEnunciado());

		model.add(
				linkTo(methodOn(PreguntaController.class).one(((PreguntaConID) entity).getId())).withSelfRel()
				);
		return model;
	}

	public CollectionModel<PreguntaQuickModel> toCollection(List<T> lista) {
		CollectionModel<PreguntaQuickModel> collection = CollectionModel
				.of(lista.stream().map(this::toModel).collect(Collectors.toList()));
		return collection;
	}

}
