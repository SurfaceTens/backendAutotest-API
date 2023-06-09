package com.dim.autotestAPI.REST.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dim.autotestAPI.REST.assemblers.PreguntaAssembler;
import com.dim.autotestAPI.REST.assemblers.PreguntaListAssembler;
import com.dim.autotestAPI.REST.assemblers.PreguntaPostAssembler;
import com.dim.autotestAPI.REST.assemblers.AlumnoListAssembler;
import com.dim.autotestAPI.REST.excepciones.RegisterNotFoundException;
import com.dim.autotestAPI.REST.models.AlumnoModel;
import com.dim.autotestAPI.REST.models.ExamenModel;
import com.dim.autotestAPI.REST.models.PreguntaModel;
import com.dim.autotestAPI.REST.models.PreguntaPostModel;
import com.dim.autotestAPI.entidades.AlumnoConID;
import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.entidades.PreguntaConID;
import com.dim.autotestAPI.entidades.PreguntaConImagen;
import com.dim.autotestAPI.entidades.PreguntaConVideo;
import com.dim.autotestAPI.repositorios.AlumnoRepositorio;
import com.dim.autotestAPI.repositorios.PreguntaRepositorio;

import es.mde.acing.utils.Alumno;
import es.mde.acing.utils.PreguntaImpl.Adjunto;

import com.dim.autotestAPI.repositorios.PreguntaRepositorio;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/preguntas")
public class PreguntaController {
	
	private final PreguntaRepositorio repositorio;
	private final AlumnoRepositorio alRepositorio;
	private final PreguntaAssembler assembler;
	private final PreguntaPostAssembler postAssembler;
	private final PreguntaListAssembler listaAssembler;
	private final AlumnoListAssembler alListaAssembler;

	PreguntaController(PreguntaRepositorio repositorio, PreguntaAssembler assembler, PreguntaPostAssembler postAssembler, AlumnoRepositorio alRepositorio,
			PreguntaListAssembler listaAssembler, AlumnoListAssembler alListaAssembler) {
		this.repositorio = repositorio;
		this.alRepositorio = alRepositorio;
		this.assembler = assembler;
		this.postAssembler = postAssembler;
		this.alListaAssembler = alListaAssembler;
		this.listaAssembler = listaAssembler;
	}
	
	@GetMapping("{id}")
	public PreguntaModel one(@PathVariable Long id) {
		PreguntaConID uno = (PreguntaConID) repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Pregunta"));
		return assembler.toModel(uno);
	}
	
	@GetMapping
	public CollectionModel<PreguntaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
	@PostMapping
	public PreguntaModel add(@RequestBody PreguntaPostModel model) {
		PreguntaConID post = repositorio.save(postAssembler.toEntity(model));
		// Los log
//		log.info("Añadido " + post);
		return assembler.toModel(post);
	}
	
	@PutMapping("{id}")
	public PreguntaModel edit(@PathVariable Long id, @RequestBody PreguntaPostModel model) {
		PreguntaConID editar = repositorio.findById(id).map(edt -> {
			
			// Para las clases hijas
//			if (model.getAdjunto() == Adjunto.imagen) {
//				PreguntaConImagen img = new PreguntaConImagen();
//				repositorio.actualizarImagen(model.getImagenURL(), id);
//				 edt = img;
//			} else if (model.getAdjunto() == Adjunto.video) {
//				PreguntaConVideo vid = new PreguntaConVideo();
//				repositorio.actualizarVideo(model.getVideoURL(), id);
//				 edt = vid;
//			}
			edt.setAdjunto(model.getAdjunto());
			
			// Resto de atributos
			edt.setTematica(model.getTematica());
			edt.setDificultad(model.getDificultad());
			edt.setEnunciado(model.getEnunciado());
			edt.setOpcionCorrecta(model.getOpcionCorrecta());
			edt.setOpcionInCorrecta1(model.getOpcionIncorrecta1());
			edt.setOpcionInCorrecta2(model.getOpcionIncorrecta2());
			edt.setOpcionInCorrecta3(model.getOpcionIncorrecta3());
			edt.setOpcionInCorrecta4(model.getOpcionIncorrecta4());
			
			// Para las relaciones
			edt.setAlumno(model.getAlumno());
//			edt.setExamenes(model.getExamenes());
			
		return repositorio.save(edt);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "Examen"));
		// Los log
//		log.info("Actualizado " + editar);
		return assembler.toModel(editar);
}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		// Los log
//	    log.info("Borrada pregunta " + id);
		PreguntaConID delete = repositorio.findById(id).map(del -> {
				repositorio.deleteById(id);	
				return del;
			})
			.orElseThrow(() -> new RegisterNotFoundException(id, "Pregunta"));
	}
	
}
