package com.dim.autotestAPI.REST.assemblers;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.PreguntaModel;
import com.dim.autotestAPI.entidades.PreguntaConID;

import es.mde.acing.utils.ConImagen;
import es.mde.acing.utils.ConVideo;

@Component
public class PreguntaAssembler implements RepresentationModelAssembler<PreguntaConID, PreguntaModel> {
	
	@Override
	public PreguntaModel toModel(PreguntaConID entity) {
		PreguntaModel model = new PreguntaModel();
		model.setId(entity.getId());
		model.setTematica(entity.getTematica());
		model.setDificultad(entity.getDificultad());
		model.setEnunciado(entity.getEnunciado());
		model.setOpcionCorrecta(entity.getOpcionCorrecta());
		model.setOpcionIncorrecta1(entity.getOpcionInCorrecta1());
		model.setOpcionIncorrecta2(entity.getOpcionInCorrecta2());
		model.setOpcionIncorrecta3(entity.getOpcionInCorrecta3());
		model.setOpcionIncorrecta4(entity.getOpcionInCorrecta4());
		model.setAlumno(entity.getAlumno());
		
		// Para las clases hijas
		if (entity instanceof ConVideo) {
			model.setVideoURL(((ConVideo) entity).getVideoURL());
		} else if (entity instanceof ConImagen) {
			model.setVideoURL(((ConImagen) entity).getImagenURL());
		}
		
		// Para sacar conclusiones de la entidad
		int numExamenes = entity.getExamenes() != null ? entity.getExamenes().size() :0;
		model.setNumExamenes(numExamenes);

		// Para la relacion
//		model.add(
//				linkTo(methodOn(PreguntaController.class).one(((PreguntaConID) entity).getId())).withSelfRel(),
//		     	linkTo(methodOn(PreguntaController.class).examenesPregunta(entity.getId())).withRel("examenes"),
//		     	linkTo(methodOn(PreguntaController.class).preguntasPregunta(entity.getId())).withRel("alumnos")
//				);
		return model;
	}
	
	
	
	
	public PreguntaConID toEntity(PreguntaModel model) {
		PreguntaConID entity = new PreguntaConID();
		entity.setId(model.getId());
		entity.setTematica(model.getTematica());
		entity.setDificultad(model.getDificultad());
		entity.setEnunciado(model.getEnunciado());
		entity.setOpcionCorrecta(model.getOpcionCorrecta());
		entity.setOpcionInCorrecta1(model.getOpcionIncorrecta1());
		entity.setOpcionInCorrecta2(model.getOpcionIncorrecta2());
		entity.setOpcionInCorrecta3(model.getOpcionIncorrecta3());
		entity.setOpcionInCorrecta4(model.getOpcionIncorrecta4());
		entity.setAlumno(model.getAlumno());
		
		// Para la releacion
//		Examenes
		
		
		return entity;
	}

}
