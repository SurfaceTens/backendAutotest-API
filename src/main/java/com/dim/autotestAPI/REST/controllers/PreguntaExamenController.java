package com.dim.autotestAPI.REST.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dim.autotestAPI.REST.assemblers.ExamenAssembler;
import com.dim.autotestAPI.REST.assemblers.PreguntaExamenAssembler;
import com.dim.autotestAPI.REST.excepciones.RegisterNotFoundException;
import com.dim.autotestAPI.REST.models.ExamenModel;
import com.dim.autotestAPI.REST.models.ExamenPostModel;
import com.dim.autotestAPI.REST.models.PreguntaExamenModel;
import com.dim.autotestAPI.REST.models.PreguntaExamenPostModel;
import com.dim.autotestAPI.entidades.AlumnoConID;
import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.entidades.PreguntaConID;
import com.dim.autotestAPI.entidades.PreguntaExamenConID;
import com.dim.autotestAPI.repositorios.AlumnoRepositorio;
import com.dim.autotestAPI.repositorios.ExamenRepositorio;
import com.dim.autotestAPI.repositorios.PreguntaExamenRepositorio;
import com.dim.autotestAPI.repositorios.PreguntaRepositorio;

import es.mde.acing.utils.PreguntaExamen;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/preguntaExamen")
public class PreguntaExamenController {

	private final PreguntaExamenRepositorio relacionRepositorio;
	private final ExamenRepositorio examenRepositorio;
	private final AlumnoRepositorio alumnoRepositorio;
	private final PreguntaRepositorio preguntaRepositorio;
	private final PreguntaExamenAssembler assembler;
	private final ExamenAssembler examenAssembler;

	PreguntaExamenController(PreguntaExamenRepositorio relacionRepositorio, PreguntaExamenAssembler assembler, ExamenAssembler examenAssembler,
			ExamenRepositorio examenRepositorio, AlumnoRepositorio alumnoRepositorio, PreguntaRepositorio preguntaRepositorio) {
		this.relacionRepositorio = relacionRepositorio;
		this.examenRepositorio = examenRepositorio;
		this.alumnoRepositorio = alumnoRepositorio;
		this.preguntaRepositorio = preguntaRepositorio;
		this.assembler = assembler;
		this.examenAssembler = examenAssembler;
	}

	@GetMapping("{id}")
	public PreguntaExamenModel one(@PathVariable Long id) {
		PreguntaExamenConID uno = relacionRepositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "PreguntaExamen"));
		return assembler.toModel(uno);
	}

	@GetMapping
	public CollectionModel<PreguntaExamenModel> all() {
		return assembler.toCollection(relacionRepositorio.findAll());
	}

	@PostMapping
	public PreguntaExamenModel add(@RequestBody PreguntaExamenPostModel model) {
		PreguntaExamenConID post = relacionRepositorio.save(assembler.toEntity(model));

		// Los log
//		log.info("Añadido " + post);
		return assembler.toModel(post);
	}

	@PostMapping("generarExamen/{numPreguntas}/{idAlumno}")
	public CollectionModel<PreguntaExamenModel> generarExamen(@PathVariable Long numPreguntas, @PathVariable Long idAlumno) {
		PreguntaExamenConID relacion = new PreguntaExamenConID();
		
		// Buscar alumno
		AlumnoConID alumno = (AlumnoConID) alumnoRepositorio.findById(idAlumno)
				.orElseThrow(() -> new RegisterNotFoundException(idAlumno, "Alumno"));
		
		// Crear examen
		ExamenConID examen = new ExamenConID();
		examen.setAlumno(alumno);
		examenRepositorio.save(examen);
		relacion.setExamen(examen);
		
		// Conseguir preguntas
		List<PreguntaConID> preguntas = new ArrayList<>();
		
		for (Long i = (long) 1; i <= numPreguntas; i++) {
			PreguntaConID pregunta = (PreguntaConID) preguntaRepositorio.findById(i)
					.orElseThrow(() -> new RegisterNotFoundException(idAlumno, "Pregunta"));
			relacion.setPregunta(pregunta);
			relacion.setCorrecta(pregunta.getOpcionCorrecta());
			
			if (relacion.getCorrecta().equals(pregunta.getOpcionCorrecta())) {
				relacion.setAcertada(true);
			}
			
			preguntas.add(pregunta);
			
			System.err.println(pregunta.getId());
			
			// Guardar relacion
			relacionRepositorio.save(relacion);
		}

		return assembler.toCollection(preguntas);
	}

}