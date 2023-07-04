package com.dim.autotestAPI.REST.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.PreguntaModel;
import com.dim.autotestAPI.entidades.PreguntaConID;
import com.dim.autotestAPI.entidades.PreguntaConImagen;
import com.dim.autotestAPI.entidades.PreguntaConVideo;
import com.dim.autotestAPI.REST.controllers.PreguntaController;

import es.mde.acing.utils.ConImagen;
import es.mde.acing.utils.ConVideo;
import es.mde.acing.utils.Pregunta;
import es.mde.acing.utils.PreguntaImpl.Adjunto;

@Component
public class PreguntaListAssembler<T extends Pregunta> implements RepresentationModelAssembler<T, PreguntaModel> {

	@Override
	public PreguntaModel toModel(T entity) {
		PreguntaModel model = new PreguntaModel();
		model.setId(((PreguntaConID) entity).getId());
		model.setTematica(entity.getTematica());
		model.setDificultad(entity.getDificultad());
		model.setEnunciado(entity.getEnunciado());
		model.setOpcionCorrecta(entity.getOpcionCorrecta());
		model.setOpcionIncorrecta1(entity.getOpcionInCorrecta1());
		model.setOpcionIncorrecta2(entity.getOpcionInCorrecta2());
		model.setOpcionIncorrecta3(entity.getOpcionInCorrecta3());
		
		if (entity.getAdjunto() == Adjunto.video) {
			String videoYoutube = ((ConVideo) entity).getVideoURL();
			videoYoutube = PreguntaConVideo.extraerIdVideoYoutube(videoYoutube);
			model.setVideoURL(videoYoutube);
			model.setImagenBase64("");
			model.setAdjunto(Adjunto.video);
		} else if (entity.getAdjunto() == Adjunto.imagen) {
			String imagenbase64 = ((ConImagen) entity).getImagenBase64();
			imagenbase64 = PreguntaConImagen.calcularCabeceraBase64(imagenbase64) + imagenbase64;
			model.setImagenBase64(imagenbase64);
			model.setVideoURL(null);
			model.setAdjunto(Adjunto.imagen);
		} else {
			model.setImagenBase64(null);
			model.setVideoURL(null);
			model.setAdjunto(Adjunto.ninguno);
		}

		int numExamenes = entity.getExamenes() != null ? entity.getExamenes().size() : 0;
		model.setNumExamenes(numExamenes);

		model.add(
				linkTo(methodOn(PreguntaController.class).one(((PreguntaConID) entity).getId())).withSelfRel()
				);
		return model;
	}

	public CollectionModel<PreguntaModel> toCollection(List<T> lista) {
		CollectionModel<PreguntaModel> collection = CollectionModel
				.of(lista.stream().map(this::toModel).collect(Collectors.toList()));
		return collection;
	}

}
