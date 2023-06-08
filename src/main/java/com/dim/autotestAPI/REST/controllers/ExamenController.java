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

import com.dim.autotestAPI.REST.assemblers.ExamenAssembler;
import com.dim.autotestAPI.REST.assemblers.ExamenListAssembler;
import com.dim.autotestAPI.REST.assemblers.AlumnoListAssembler;
import com.dim.autotestAPI.REST.excepciones.RegisterNotFoundException;
import com.dim.autotestAPI.REST.models.AlumnoModel;
import com.dim.autotestAPI.REST.models.ExamenModel;
import com.dim.autotestAPI.entidades.AlumnoConID;
import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.repositorios.AlumnoRepositorio;
import com.dim.autotestAPI.repositorios.ExamenRepositorio;
import com.dim.autotestAPI.repositorios.ExamenRepositorio;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/examen")
public class ExamenController {
	
	private final ExamenRepositorio repositorio;
	private final AlumnoRepositorio alRepositorio;
	private final ExamenAssembler assembler;
	private final ExamenListAssembler listaAssembler;
	private final AlumnoListAssembler alListaAssembler;

	ExamenController(ExamenRepositorio repositorio, ExamenAssembler assembler, AlumnoRepositorio alRepositorio,
			ExamenListAssembler listaAssembler, AlumnoListAssembler alListaAssembler) {
		this.repositorio = repositorio;
		this.alRepositorio = alRepositorio;
		this.assembler = assembler;
		this.alListaAssembler = alListaAssembler;
		this.listaAssembler = listaAssembler;
	}
	
	@GetMapping("{id}")
	public ExamenModel one(@PathVariable Long id) {
		ExamenConID alumno = (ExamenConID) repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Examen"));
		return assembler.toModel(alumno);
	}
	
	@GetMapping
	public CollectionModel<ExamenModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
	@PostMapping // Post o no post en el model?
	public ExamenModel add(@RequestBody ExamenModel model) {
		ExamenConID categoria = repositorio.save(assembler.toEntity(model));
		// Los log
//		log.info("Añadido " + categoria);
		return assembler.toModel(categoria);
	}

}
