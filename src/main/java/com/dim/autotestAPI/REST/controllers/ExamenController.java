package com.dim.autotestAPI.REST.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dim.autotestAPI.REST.assemblers.ExamenAssembler;
import com.dim.autotestAPI.REST.assemblers.ExamenListAssembler;
import com.dim.autotestAPI.REST.assemblers.PreguntaAssembler;
import com.dim.autotestAPI.REST.assemblers.PreguntaExamenAssembler;
import com.dim.autotestAPI.REST.assemblers.PreguntaListAssembler;
import com.dim.autotestAPI.REST.assemblers.AlumnoAssembler;
import com.dim.autotestAPI.REST.excepciones.RegisterNotFoundException;
import com.dim.autotestAPI.REST.models.ExamenModel;
import com.dim.autotestAPI.REST.models.ExamenPostModel;
import com.dim.autotestAPI.REST.models.PreguntaExamenModel;
import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.repositorios.AlumnoRepositorio;
import com.dim.autotestAPI.repositorios.ExamenRepositorio;
import com.dim.autotestAPI.repositorios.PreguntaExamenRepositorio;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/examenes")
public class ExamenController {

	private final ExamenRepositorio examenRepositorio;
	private final ExamenAssembler examenAssembler;
	private final ExamenListAssembler listaAssembler;
	private final PreguntaExamenAssembler preguntaExamenAssembler;

	ExamenController(ExamenRepositorio examenRepositorio, ExamenAssembler examenAssembler,
			AlumnoRepositorio alRepositorio, PreguntaExamenRepositorio relacionRepositorio,
			ExamenListAssembler listaAssembler, AlumnoAssembler alAssembler, PreguntaAssembler preguntaAssembler,
			PreguntaListAssembler preguntaListAssembler, PreguntaExamenAssembler preguntaExamenAssembler) {
		this.examenRepositorio = examenRepositorio;
		this.examenAssembler = examenAssembler;
		this.listaAssembler = listaAssembler;
		this.preguntaExamenAssembler = preguntaExamenAssembler;
	}

	@GetMapping("{id}")
	public ExamenModel one(@PathVariable Long id) {
		ExamenConID uno = (ExamenConID) examenRepositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Examen"));
		return examenAssembler.toModel(uno);
	}

	@GetMapping
	public CollectionModel<ExamenModel> all() {
		return listaAssembler.toCollection(examenRepositorio.findAll());
	}

	@PostMapping
	public ExamenModel add(@RequestBody ExamenPostModel model) {
		ExamenConID post = examenRepositorio.save(examenAssembler.toEntity(model));
		return examenAssembler.toModel(post);
	}

	@PutMapping("{id}")
	public ExamenModel edit(@PathVariable Long id, @RequestBody ExamenModel model) {

		ExamenConID editar = examenRepositorio.findById(id).map(edt -> {

			edt.setAlumno(model.getAlumno());

			return examenRepositorio.save(edt);
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Examen"));
		// Los log
//		log.info("Actualizado " + editar);
		return examenAssembler.toModel(editar);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		examenRepositorio.findById(id).map(del -> {
			examenRepositorio.deleteById(id);
			return del;
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Examen"));
	}

	@GetMapping("{id}/preguntas")
	public CollectionModel<PreguntaExamenModel> preguntasExamen(@PathVariable Long id) {
		System.out.println("sacando coleccion del examan" + id);
		ExamenConID examen = examenRepositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Examen"));
		System.out.println("el alumno es" + examen.getAlumno());
		System.out.println("el examen tiene" + examen.getPreguntas().size());
		return preguntaExamenAssembler.toCollection(examen.getPreguntas());
	}

}
