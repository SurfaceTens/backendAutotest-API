package com.dim.autotestAPI.REST.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.PreguntaModel;
import com.dim.autotestAPI.entidades.PreguntaConID;
import com.dim.autotestAPI.entidades.PreguntaConImagen;
import com.dim.autotestAPI.entidades.PreguntaConVideo;
import com.dim.autotestAPI.REST.controllers.PreguntaController;

import es.mde.acing.utils.ConImagen;
import es.mde.acing.utils.ConVideo;
import es.mde.acing.utils.PreguntaImpl.Adjunto;

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

		// Para las clases hijas
		if (entity.getAdjunto() == Adjunto.video) {
			model.setVideoURL(((ConVideo) entity).getVideoURL());
			model.setImagenURL(null);
			model.setAdjunto(Adjunto.video);
		} else if (entity.getAdjunto() == Adjunto.imagen) {
			model.setImagenURL(((ConImagen) entity).getImagenURL());
			model.setVideoURL(null);
			model.setAdjunto(Adjunto.imagen);
		}

		// Para sacar conclusiones de la entidad
		int numExamenes = entity.getExamenes() != null ? entity.getExamenes().size() : 0;
		model.setNumExamenes(numExamenes);

		// Para la relacion
		model.add(
				linkTo(methodOn(PreguntaController.class).one(((PreguntaConID) entity).getId())).withSelfRel()
//		     	linkTo(methodOn(PreguntaController.class).examenes(entity.getId())).withRel("examenes") // No hace falta este endpoint por ahora
				);
		return model;
	}

	public PreguntaConID toEntity(PreguntaModel model) {
		PreguntaConID entity = new PreguntaConID();
		
		// Para las clases hijas		
		switch (model.getAdjunto()) {
			case imagen: {
				PreguntaConImagen imagen = new PreguntaConImagen();
				imagen.setImagenURL(model.getImagenURL());
				entity = imagen;
				break;
			}
			case video: {
				PreguntaConVideo video = new PreguntaConVideo();
				video.setVideoURL(model.getVideoURL());
				entity = video;
				break;
			}
		}

		
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
		// No necesitamos nada aqui.
		return entity;
	}

}
