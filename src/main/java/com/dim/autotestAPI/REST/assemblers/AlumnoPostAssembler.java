package com.dim.autotestAPI.REST.assemblers;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.AlumnoPostModel;
import com.dim.autotestAPI.entidades.AlumnoConID;

@Component
public class AlumnoPostAssembler implements RepresentationModelAssembler<AlumnoConID, AlumnoPostModel> {

	@Override
	public AlumnoPostModel toModel(AlumnoConID entity) {
		AlumnoPostModel model = new AlumnoPostModel();
		model.setNombre(entity.getNombre());
		model.setApellidos(entity.getApellidos());

		// Para la relacion
//			model.add(
//					linkTo(methodOn(AlumnoController.class).one(((AlumnoConID) entity).getId())).withSelfRel(),
//			     	linkTo(methodOn(AlumnoController.class).examenesAlumno(entity.getId())).withRel("examenes"),
//			     	linkTo(methodOn(AlumnoController.class).preguntasAlumno(entity.getId())).withRel("preguntas")
//					);
		return model;
	}

	public AlumnoConID toEntity(AlumnoPostModel model) {
		AlumnoConID entity = new AlumnoConID();
		entity.setNombre(model.getNombre());
		entity.setApellidos(model.getApellidos());

		// Para la releacion
//			Examenes
//			Preguntas

		return entity;
	}

}
