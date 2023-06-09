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
import com.dim.autotestAPI.REST.assemblers.ExamenPostAssembler;
import com.dim.autotestAPI.REST.assemblers.AlumnoListAssembler;
import com.dim.autotestAPI.REST.assemblers.AlumnoPostAssembler;
import com.dim.autotestAPI.REST.excepciones.RegisterNotFoundException;
import com.dim.autotestAPI.REST.models.AlumnoModel;
import com.dim.autotestAPI.REST.models.ExamenModel;
import com.dim.autotestAPI.REST.models.ExamenPostModel;
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
	private final ExamenPostAssembler postAssembler;
	private final ExamenListAssembler listaAssembler;
	private final AlumnoListAssembler alListaAssembler;

	ExamenController(ExamenRepositorio repositorio, ExamenAssembler assembler, ExamenPostAssembler postAssembler, AlumnoRepositorio alRepositorio,
			ExamenListAssembler listaAssembler, AlumnoListAssembler alListaAssembler) {
		this.repositorio = repositorio;
		this.alRepositorio = alRepositorio;
		this.assembler = assembler;
		this.postAssembler = postAssembler;
		this.alListaAssembler = alListaAssembler;
		this.listaAssembler = listaAssembler;
	}
	
	@GetMapping("{id}")
	public ExamenModel one(@PathVariable Long id) {
		ExamenConID uno = (ExamenConID) repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Examen"));
		return assembler.toModel(uno);
	}
	
	@GetMapping
	public CollectionModel<ExamenModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
	@PostMapping 
	public ExamenModel add(@RequestBody ExamenPostModel model) {
		ExamenConID post = repositorio.save(postAssembler.toEntity(model));
		// Los log
//		log.info("Añadido " + post);
		return assembler.toModel(post);
	}
	
	@PutMapping("{id}")
	public ExamenModel edit(@PathVariable Long id, @RequestBody ExamenModel model) {
		  
		ExamenConID editar = repositorio.findById(id).map(edt -> {
			edt.setFecha(model.getFecha());
			
			// Para las relaciones
			edt.setAlumno(model.getAlumno());
//			edt.setPreguntas(model.getPreguntas());
			
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
//	    log.info("Borrado examen " + id);
		ExamenConID delete = repositorio.findById(id).map(del -> {
				repositorio.deleteById(id);	
				return del;
			})
			.orElseThrow(() -> new RegisterNotFoundException(id, "Examen"));
	}

}
