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

import com.dim.autotestAPI.REST.assemblers.AlumnoAssembler;
import com.dim.autotestAPI.REST.assemblers.AlumnoListAssembler;
import com.dim.autotestAPI.REST.assemblers.AlumnoPostAssembler;
import com.dim.autotestAPI.REST.assemblers.ExamenListAssembler;
import com.dim.autotestAPI.REST.excepciones.RegisterNotFoundException;
import com.dim.autotestAPI.REST.models.AlumnoModel;
import com.dim.autotestAPI.REST.models.AlumnoPostModel;
import com.dim.autotestAPI.entidades.AlumnoConID;
import com.dim.autotestAPI.repositorios.AlumnoRepositorio;
import com.dim.autotestAPI.repositorios.ExamenRepositorio;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

	private final AlumnoRepositorio repositorio;
	private final ExamenRepositorio exRepositorio;
	private final AlumnoAssembler assembler;
	private final AlumnoPostAssembler postAssembler;
	private final AlumnoListAssembler listaAssembler;
	private final ExamenListAssembler exListaAssembler;

	AlumnoController(AlumnoRepositorio repositorio, AlumnoAssembler assembler, AlumnoPostAssembler postAssembler, ExamenRepositorio exRepositorio,
			ExamenListAssembler exListaAssembler, AlumnoListAssembler listaAssembler) {
		this.repositorio = repositorio;
		this.exRepositorio = exRepositorio;
		this.assembler = assembler;
		this.postAssembler = postAssembler;
		this.exListaAssembler = exListaAssembler;
		this.listaAssembler = listaAssembler;
	}

	@GetMapping("{id}")
	public AlumnoModel one(@PathVariable Long id) {
		AlumnoConID alumno = (AlumnoConID) repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Alumno"));
		return assembler.toModel(alumno);
	}
	
	@GetMapping
	public CollectionModel<AlumnoModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
	@PostMapping // Post o no post en el model?
	public AlumnoModel add(@RequestBody AlumnoModel model) {
		AlumnoConID categoria = repositorio.save(assembler.toEntity(model));
		// Los log
//		log.info("Añadido " + categoria);
		return assembler.toModel(categoria);
	}

}
