package com.dim.autotestAPI.REST.assemblers;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.ExamenModel;
import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.REST.controllers.ExamenController;

@Component
public class ExamenListAssembler implements RepresentationModelAssembler<ExamenConID, ExamenModel> {
	
	@Override
	public ExamenModel toModel(ExamenConID entity) {
		ExamenModel model = new ExamenModel();
		model.setId(entity.getId());
		model.setFecha(entity.getFecha());
		
		// Para sacar conclusiones de la entidad
		int numPreguntas = entity.getPreguntas() != null ? entity.getPreguntas().size() : 0;
		model.setNumPreguntas(numPreguntas);

		// Para la relacion
//		model.add(
//				linkTo(methodOn(ExamenController.class).one(((ExamenConID) entity).getId())).withSelfRel(),
//		     	linkTo(methodOn(ExamenController.class).preguntasExamen(entity.getId())).withRel("preguntas")
//     			linkTo(methodOn(ExamenController.class).alumnosExamen(entity.getId())).withRel("alumnos")
//				);
		return model;
	}
	
	
	
	
	public CollectionModel<ExamenModel> toCollection(List<ExamenConID> lista) {
		CollectionModel<ExamenModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);	
		return collection;
	}

}
