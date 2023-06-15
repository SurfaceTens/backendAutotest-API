package com.dim.autotestAPI.REST.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.AlumnoModel;
import com.dim.autotestAPI.REST.models.AlumnoPostModel;
import com.dim.autotestAPI.entidades.AlumnoConID;
import com.dim.autotestAPI.REST.controllers.AlumnoController;

@Component
public class AlumnoAssembler implements RepresentationModelAssembler<AlumnoConID, AlumnoModel> {

	@Override
	public AlumnoModel toModel(AlumnoConID entity) {
		AlumnoModel model = new AlumnoModel();
		model.setNombre(entity.getNombre());
		model.setApellidos(entity.getApellidos());
		model.setId(entity.getId());

		// Para sacar conclusiones de la entidad
		int numExamenes = entity.getExamenes() != null ? entity.getExamenes().size() : 0;
		model.setNumExamenes(numExamenes);

		// Para la relacion
		model.add(linkTo(methodOn(AlumnoController.class).one(((AlumnoConID) entity).getId())).withSelfRel(),
				linkTo(methodOn(AlumnoController.class).examenes(entity.getId())).withRel("examenes"));
		return model;
	}

	public AlumnoConID toEntity(AlumnoPostModel model) {
		AlumnoConID entity = new AlumnoConID();
		entity.setNombre(model.getNombre());
		entity.setApellidos(model.getApellidos());

		// Para la releacion
		// No necesitamos nada aqui.
		return entity;
	}

	public CollectionModel<AlumnoModel> toCollection(List<AlumnoConID> lista) {
		CollectionModel<AlumnoModel> collection = CollectionModel
				.of(lista.stream().map(this::toModel).collect(Collectors.toList()));
		return collection;
	}

}
