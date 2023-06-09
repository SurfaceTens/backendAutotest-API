package com.dim.autotestAPI.REST.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dim.autotestAPI.REST.assemblers.PreguntaAssembler;
import com.dim.autotestAPI.REST.assemblers.PreguntaListAssembler;
import com.dim.autotestAPI.REST.assemblers.PreguntaQuickListAssembler;
import com.dim.autotestAPI.REST.excepciones.RegisterNotFoundException;
import com.dim.autotestAPI.REST.models.PreguntaModel;
import com.dim.autotestAPI.REST.models.PreguntaPostModel;
import com.dim.autotestAPI.entidades.PreguntaConID;
import com.dim.autotestAPI.entidades.PreguntaConImagen;
import com.dim.autotestAPI.entidades.PreguntaConVideo;
import com.dim.autotestAPI.repositorios.AlumnoRepositorio;
import com.dim.autotestAPI.repositorios.PreguntaRepositorio;

import es.mde.acing.utils.PreguntaImpl.Adjunto;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/preguntas")
public class PreguntaController {

	private final PreguntaRepositorio repositorio;
	private final PreguntaAssembler preguntaAssembler;
	private final PreguntaListAssembler preguntaListAssembler;
	private final PreguntaQuickListAssembler preguntaQuickListAssembler;

	PreguntaController(PreguntaRepositorio repositorio, PreguntaAssembler preguntaAssembler,
			AlumnoRepositorio alRepositorio, PreguntaListAssembler preguntaListAssembler,
			PreguntaQuickListAssembler preguntaQuickListAssembler) {
		this.repositorio = repositorio;
		this.preguntaAssembler = preguntaAssembler;
		this.preguntaListAssembler = preguntaListAssembler;
		this.preguntaQuickListAssembler = preguntaQuickListAssembler;
	}

	@GetMapping("{id}")
	public PreguntaModel one(@PathVariable Long id) {
		PreguntaConID uno = (PreguntaConID) repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Pregunta"));
		return preguntaAssembler.toModel(uno);
	}

	public CollectionModel<PreguntaModel> getAleatorias(int numPreguntas) {
		List<PreguntaConID> preguntas = repositorio.findAll();
		int totalPreguntas = preguntas.size();

		if (numPreguntas >= totalPreguntas) {
			List<PreguntaModel> preguntasModel = preguntas.stream()
					.map(pregunta -> preguntaListAssembler.toModel(pregunta)).collect(Collectors.toList());

			return preguntaListAssembler.toCollection(preguntasModel);
		} else {
			List<PreguntaConID> preguntasAleatorias = new ArrayList<>();
			Random random = new Random();

			while (preguntasAleatorias.size() < numPreguntas) {
				int index = random.nextInt(totalPreguntas);
				PreguntaConID pregunta = preguntas.get(index);

				if (!preguntasAleatorias.contains(pregunta)) {
					preguntasAleatorias.add(pregunta);
				}
			}

			List<PreguntaModel> preguntaModels = preguntasAleatorias.stream()
					.map(pregunta -> preguntaListAssembler.toModel(pregunta)).collect(Collectors.toList());

			return preguntaListAssembler.toCollection(preguntaModels);
		}
	}

	@GetMapping
	public CollectionModel<PreguntaModel> all() {
		return preguntaQuickListAssembler.toCollection(repositorio.findAll());
	}
	
	@GetMapping("/total")
	public int total() {
	    return repositorio.findAll().size();
	}

	@PostMapping
	public PreguntaModel add(@RequestBody PreguntaPostModel model) {
		PreguntaConID post = repositorio.save(preguntaAssembler.toEntity(model));
		return preguntaAssembler.toModel(post);
	}

	@PutMapping("{id}")
	public PreguntaModel edit(@PathVariable Long id, @RequestBody PreguntaPostModel model) {
		PreguntaConID editar = repositorio.findById(id).map(edt -> {

			if (model.getAdjunto() == Adjunto.imagen) {
				new PreguntaConImagen();
				repositorio.actualizarImagen(((PreguntaConImagen) preguntaAssembler.toEntity(model)).getImagen(), id);
			} else if (model.getAdjunto() == Adjunto.video) {
				new PreguntaConVideo();
				repositorio.actualizarVideo(PreguntaConVideo.extraerIdVideoYoutube(model.getVideoURL()), id);
			} else {
				repositorio.actualizarNinguno(id);
			}

			edt.setTematica(model.getTematica());
			edt.setDificultad(model.getDificultad());
			edt.setEnunciado(model.getEnunciado());
			edt.setOpcionCorrecta(model.getOpcionCorrecta());
			edt.setOpcionInCorrecta1(model.getOpcionIncorrecta1());
			edt.setOpcionInCorrecta2(model.getOpcionIncorrecta2());
			edt.setOpcionInCorrecta3(model.getOpcionIncorrecta3());

			edt.setExamenes(model.getExamenes());

			return repositorio.save(edt);
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Examen"));
		return preguntaAssembler.toModel(editar);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		repositorio.findById(id).map(del -> {
			repositorio.deleteById(id);
			return del;
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Pregunta"));
	}

}
