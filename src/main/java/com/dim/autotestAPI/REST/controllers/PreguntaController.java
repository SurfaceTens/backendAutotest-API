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
import com.dim.autotestAPI.REST.assemblers.AlumnoListAssembler;
import com.dim.autotestAPI.REST.excepciones.RegisterNotFoundException;
import com.dim.autotestAPI.REST.models.AlumnoModel;
import com.dim.autotestAPI.REST.models.PreguntaModel;
import com.dim.autotestAPI.entidades.AlumnoConID;
import com.dim.autotestAPI.entidades.PreguntaConID;
import com.dim.autotestAPI.repositorios.AlumnoRepositorio;
import com.dim.autotestAPI.repositorios.PreguntaRepositorio;
import com.dim.autotestAPI.repositorios.PreguntaRepositorio;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/preguntas")
public class PreguntaController {
	
	private final PreguntaRepositorio repositorio;
	private final AlumnoRepositorio alRepositorio;
	private final PreguntaAssembler assembler;
	private final PreguntaListAssembler listaAssembler;
	private final AlumnoListAssembler alListaAssembler;

	PreguntaController(PreguntaRepositorio repositorio, PreguntaAssembler assembler, AlumnoRepositorio alRepositorio,
			PreguntaListAssembler listaAssembler, AlumnoListAssembler alListaAssembler) {
		this.repositorio = repositorio;
		this.alRepositorio = alRepositorio;
		this.assembler = assembler;
		this.alListaAssembler = alListaAssembler;
		this.listaAssembler = listaAssembler;
	}
	
	@GetMapping("{id}")
	public PreguntaModel one(@PathVariable Long id) {
		PreguntaConID alumno = (PreguntaConID) repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Pregunta"));
		return assembler.toModel(alumno);
	}
	
	@GetMapping
	public CollectionModel<PreguntaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
	@PostMapping // Post o no post en el model?
	public PreguntaModel add(@RequestBody PreguntaModel model) {
		PreguntaConID categoria = repositorio.save(assembler.toEntity(model));
		// Los log
//		log.info("Añadido " + categoria);
		return assembler.toModel(categoria);
	}
	
}
