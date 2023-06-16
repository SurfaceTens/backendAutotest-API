package com.dim.autotestAPI.REST.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.ExamenModel;
import com.dim.autotestAPI.entidades.AlumnoConID;
import com.dim.autotestAPI.entidades.ExamenConID;

import es.mde.acing.utils.Examen;

import com.dim.autotestAPI.REST.controllers.AlumnoController;
import com.dim.autotestAPI.REST.controllers.ExamenController;

@Component
public class ExamenListAssembler<T extends Examen> implements RepresentationModelAssembler<T, ExamenModel> {

	@Override
	public ExamenModel toModel(T entity) {
		ExamenModel model = new ExamenModel();
		model.setId(((ExamenConID) entity).getId());

		int numPreguntas = ((ExamenConID) entity).getPreguntas() != null ? ((ExamenConID) entity).getPreguntas().size()
				: 0;
		model.setNumPreguntas(numPreguntas);

		model.add(linkTo(methodOn(ExamenController.class).one(((ExamenConID) entity).getId())).withSelfRel(),
				linkTo(methodOn(AlumnoController.class).one(((AlumnoConID) entity.getAlumno()).getId()))
						.withRel("alumno"),
				linkTo(methodOn(ExamenController.class).preguntasExamen(((ExamenConID) entity).getId()))
						.withRel("preguntas"));
		return model;
	}

	public CollectionModel<ExamenModel> toCollection(List<T> lista) {
		CollectionModel<ExamenModel> collection = CollectionModel
				.of(lista.stream().map(this::toModel).collect(Collectors.toList()));
		return collection;
	}

}
