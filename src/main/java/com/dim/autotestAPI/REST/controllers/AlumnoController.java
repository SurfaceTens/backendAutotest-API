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
		AlumnoConID uno = (AlumnoConID) repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Alumno"));
		return assembler.toModel(uno);
	}
	
	@GetMapping
	public CollectionModel<AlumnoModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
	@PostMapping
	public AlumnoModel add(@RequestBody AlumnoPostModel model) {
		AlumnoConID post = repositorio.save(postAssembler.toEntity(model));
		// Los log
//		log.info("Añadido " + post);
		return assembler.toModel(post);
	}
	
	@PutMapping("{id}")
	public AlumnoModel edit(@PathVariable Long id, @RequestBody AlumnoPostModel model) {
		  
		AlumnoConID editar = repositorio.findById(id).map(edt -> {
			edt.setNombre(model.getNombre());
			edt.setApellidos(model.getApellidos());
			
			// Para las relaciones
//			edt.setExamenes(model.getExamenes());
//			edt.setPreguntas(model.getPreguntas());
			
		return repositorio.save(edt);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "Alumno"));
		// Los log
//		log.info("Actualizado " + editar);
		return assembler.toModel(editar);
}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		// Los log
//	    log.info("Borrado alumno " + id);
	    AlumnoConID delete = repositorio.findById(id).map(del -> {
				repositorio.deleteById(id);	
				return del;
			})
			.orElseThrow(() -> new RegisterNotFoundException(id, "Alumno"));
	}

}
