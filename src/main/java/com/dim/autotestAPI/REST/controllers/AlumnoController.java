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

import com.dim.autotestAPI.REST.assemblers.AlumnoAssembler;
import com.dim.autotestAPI.REST.assemblers.ExamenListAssembler;
import com.dim.autotestAPI.REST.assemblers.PreguntaListAssembler;
import com.dim.autotestAPI.REST.excepciones.RegisterNotFoundException;
import com.dim.autotestAPI.REST.models.AlumnoModel;
import com.dim.autotestAPI.REST.models.AlumnoPostModel;
import com.dim.autotestAPI.entidades.AlumnoConID;
import com.dim.autotestAPI.repositorios.AlumnoRepositorio;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

	private final AlumnoRepositorio alumnoRepositorio;
	private final AlumnoAssembler alumnoAssembler;
	private final ExamenListAssembler exListaAssembler;
	private final PreguntaListAssembler preguntaListAssembler;

	AlumnoController(AlumnoRepositorio alumnoRepositorio, AlumnoAssembler alumnoAssembler,
			ExamenListAssembler exListaAssembler, PreguntaListAssembler preguntaListAssembler) {
		this.alumnoRepositorio = alumnoRepositorio;
		this.alumnoAssembler = alumnoAssembler;
		this.exListaAssembler = exListaAssembler;
		this.preguntaListAssembler = preguntaListAssembler;
	}

	@GetMapping("{id}")
	public AlumnoModel one(@PathVariable Long id) {
		AlumnoConID uno = (AlumnoConID) alumnoRepositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Alumno"));
		return alumnoAssembler.toModel(uno);
	}

	@GetMapping
	public CollectionModel<AlumnoModel> all() {
		return alumnoAssembler.toCollection(alumnoRepositorio.findAll());
	}
	
	@GetMapping("/total")
	public int total() {
	    return alumnoRepositorio.findAll().size();
	}

	@GetMapping("{id}/examenes")
	public CollectionModel<AlumnoModel> examenes(@PathVariable Long id) {
		AlumnoConID origen = alumnoRepositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Examen"));
		return exListaAssembler.toCollection(origen.getExamenes());
	}

	@GetMapping("{id}/preguntas")
	public CollectionModel<AlumnoModel> preguntas(@PathVariable Long id) {
		AlumnoConID origen = alumnoRepositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Pregunta"));
		return preguntaListAssembler.toCollection(origen.getExamenes());
	}

	@PostMapping
	public AlumnoModel add(@RequestBody AlumnoPostModel model) {
		AlumnoConID post = alumnoRepositorio.save(alumnoAssembler.toEntity(model));
		return alumnoAssembler.toModel(post);
	}

	@PutMapping("{id}")
	public AlumnoModel edit(@PathVariable Long id, @RequestBody AlumnoPostModel model) {

		AlumnoConID editar = alumnoRepositorio.findById(id).map(edt -> {
			edt.setNombre(model.getNombre());
			edt.setApellidos(model.getApellidos());

			return alumnoRepositorio.save(edt);
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Alumno"));
		return alumnoAssembler.toModel(editar);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		alumnoRepositorio.findById(id).map(del -> {
			alumnoRepositorio.deleteById(id);
			return del;
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Alumno"));
	}

}
