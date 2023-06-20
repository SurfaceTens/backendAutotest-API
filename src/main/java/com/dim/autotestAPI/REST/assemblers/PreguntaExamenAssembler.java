package com.dim.autotestAPI.REST.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dim.autotestAPI.REST.models.PreguntaExamenModel;
import com.dim.autotestAPI.REST.models.PreguntaExamenPostModel;
import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.entidades.PreguntaConID;
import com.dim.autotestAPI.entidades.PreguntaConImagen;
import com.dim.autotestAPI.entidades.PreguntaConVideo;
import com.dim.autotestAPI.entidades.PreguntaExamenConID;

import es.mde.acing.utils.ConImagen;
import es.mde.acing.utils.ConVideo;
import es.mde.acing.utils.PreguntaExamen;
import es.mde.acing.utils.PreguntaImpl.Adjunto;

import com.dim.autotestAPI.REST.controllers.ExamenController;

import jakarta.persistence.EntityManager;

@Component
public class PreguntaExamenAssembler<T extends PreguntaExamen>
		implements RepresentationModelAssembler<T, PreguntaExamenModel> {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public PreguntaExamenModel toModel(T entity) {
		PreguntaExamenModel model = new PreguntaExamenModel();
		model.setId(((PreguntaExamenConID) entity).getId());
		model.setAcertada(entity.isAcertada());
		model.setRespuesta(entity.getRespuesta());
		model.setCorrecta(entity.getPregunta().getOpcionCorrecta());
		model.setTematica(entity.getPregunta().getTematica());
		model.setDificultad(entity.getPregunta().getDificultad());
		model.setEnunciado(entity.getPregunta().getEnunciado());
		String[] incorrectas = { entity.getPregunta().getOpcionInCorrecta1(),
				entity.getPregunta().getOpcionInCorrecta2(), entity.getPregunta().getOpcionInCorrecta3() };
		model.setIncorrectas(incorrectas);
		
		// Este es el if que yo hice
//		if (entity.getPregunta().getAdjunto() == Adjunto.video) {
//			model.setAdjuntoURL(((ConVideo) entity.getPregunta()).getVideoURL());
//			model.setAdjunto(Adjunto.video);
//		} else if (entity.getPregunta().getAdjunto() == Adjunto.imagen) {
//			model.setAdjuntoURL(((ConImagen) entity.getPregunta()).getImagenURL());
//			model.setAdjunto(Adjunto.imagen);
//		}
		
		// Este es el if que hace la ia para solucionar el problema del cast
		if (entity.getPregunta().getAdjunto() == Adjunto.video) {
            Session session = entityManager.unwrap(Session.class);
            PreguntaConVideo preguntaConVideo = session.get(PreguntaConVideo.class, ((PreguntaConID) entity.getPregunta()).getId());
            model.setAdjuntoURL(preguntaConVideo.getVideoURL());
            model.setAdjunto(Adjunto.video);
        } else if (entity.getPregunta().getAdjunto() == Adjunto.imagen) {
            Session session = entityManager.unwrap(Session.class);
            PreguntaConImagen preguntaConImagen = session.get(PreguntaConImagen.class, ((PreguntaConID) entity.getPregunta()).getId());
            model.setAdjuntoURL(preguntaConImagen.getImagenURL());
            model.setAdjunto(Adjunto.imagen);
        }


		model.add(
				linkTo(methodOn(ExamenController.class).one(((ExamenConID) entity.getExamen()).getId())).withRel("examen")
				);
		return model;
	}

	public PreguntaExamenConID toEntity(PreguntaExamenPostModel model) {
		PreguntaExamenConID entity = new PreguntaExamenConID();
		entity.setAcertada(false); // Hasta que no se haga el examen es false
		entity.setRespuesta(model.getRespuesta());
		entity.setCorrecta(model.getCorrecta());

		entity.setExamen(model.getExamen());
		entity.setPregunta(model.getPregunta());
		return entity;
	}

	public CollectionModel<PreguntaExamenModel> toCollection(List<T> lista) {
		CollectionModel<PreguntaExamenModel> collection = CollectionModel
				.of(lista.stream().map(this::toModel).collect(Collectors.toList()));
		return collection;
	}

}
