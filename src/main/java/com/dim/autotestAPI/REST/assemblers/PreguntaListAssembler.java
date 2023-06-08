package com.dim.autotestAPI.REST.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.ExamenModel;
import com.dim.autotestAPI.REST.models.PreguntaModel;
import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.entidades.PreguntaConID;
import com.dim.autotestAPI.entidades.PreguntaConImagen;
import com.dim.autotestAPI.entidades.PreguntaConVideo;
import com.dim.autotestAPI.REST.controllers.PreguntaController;

import es.mde.acing.utils.ConImagen;
import es.mde.acing.utils.ConVideo;
import es.mde.acing.utils.PreguntaImpl.Adjunto;

@Component
public class PreguntaListAssembler implements RepresentationModelAssembler<PreguntaConID, PreguntaModel> {
	
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
		if (entity.getAdjunto() == Adjunto.video) {
			model.setVideoURL(((ConVideo) entity).getVideoURL());
		} else if (entity.getAdjunto() == Adjunto.imagen) {
			model.setVideoURL(((ConImagen) entity).getImagenURL());
		}

		// Para sacar conclusiones de la entidad
		int numExamenes = entity.getExamenes() != null ? entity.getExamenes().size() : 0;
		model.setNumExamenes(numExamenes);

		// Para la relacion
//		model.add(
//				linkTo(methodOn(PreguntaController.class).one(((PreguntaConID) entity).getId())).withSelfRel(),
//		     	linkTo(methodOn(PreguntaController.class).examenesPregunta(entity.getId())).withRel("examenes"),
//				);
		return model;
	}

	public CollectionModel<PreguntaModel> toCollection(List<PreguntaConID> lista) {
		CollectionModel<PreguntaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);	
		return collection;
	}

}
