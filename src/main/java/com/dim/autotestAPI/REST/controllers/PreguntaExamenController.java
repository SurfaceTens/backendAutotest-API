package com.dim.autotestAPI.REST.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.dim.autotestAPI.REST.assemblers.ExamenAssembler;
import com.dim.autotestAPI.REST.assemblers.PreguntaExamenAssembler;
import com.dim.autotestAPI.REST.excepciones.RegisterNotFoundException;
import com.dim.autotestAPI.REST.models.PreguntaExamenModel;
import com.dim.autotestAPI.REST.models.PreguntaExamenPostModel;
import com.dim.autotestAPI.entidades.AlumnoConID;
import com.dim.autotestAPI.entidades.ExamenConID;
import com.dim.autotestAPI.entidades.PreguntaConID;
import com.dim.autotestAPI.entidades.PreguntaConID.NivelDificultad;
import com.dim.autotestAPI.entidades.PreguntaExamenConID;
import com.dim.autotestAPI.repositorios.AlumnoRepositorio;
import com.dim.autotestAPI.repositorios.ExamenRepositorio;
import com.dim.autotestAPI.repositorios.PreguntaExamenRepositorio;
import com.dim.autotestAPI.repositorios.PreguntaRepositorio;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/preguntasExamen")
public class PreguntaExamenController {

	private final PreguntaExamenRepositorio relacionRepositorio;
	private final ExamenRepositorio examenRepositorio;
	private final AlumnoRepositorio alumnoRepositorio;
	private final PreguntaRepositorio preguntaRepositorio;
	private final PreguntaExamenAssembler assembler;

	PreguntaExamenController(PreguntaExamenRepositorio relacionRepositorio, PreguntaExamenAssembler assembler,
			ExamenAssembler examenAssembler, ExamenRepositorio examenRepositorio, AlumnoRepositorio alumnoRepositorio,
			PreguntaRepositorio preguntaRepositorio) {
		this.relacionRepositorio = relacionRepositorio;
		this.examenRepositorio = examenRepositorio;
		this.alumnoRepositorio = alumnoRepositorio;
		this.preguntaRepositorio = preguntaRepositorio;
		this.assembler = assembler;
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

		return assembler.toModel(post);
	}
	
	@PutMapping("{id}")
	public PreguntaExamenModel edit(@PathVariable Long id, @RequestBody PreguntaExamenModel model) {		
		PreguntaExamenConID editar = relacionRepositorio.findById(id).map(edt -> {

			edt.setAcertada(model.isAcertada());
			edt.setRespuesta(model.getRespuesta());

			return relacionRepositorio.save(edt);
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Examen"));

		return assembler.toModel(editar);
	}

	@GetMapping("generarExamen/{numPreguntas}/{idAlumno}/{dificultad}")
	public CollectionModel<PreguntaExamenModel> generarExamen(@PathVariable int numPreguntas,
			@PathVariable Long idAlumno, @PathVariable NivelDificultad dificultad) {
		List<PreguntaExamenConID> preguntasExamen = new ArrayList<>();
		List<PreguntaConID> preguntas = new ArrayList<>();
		
		if ((dificultad != NivelDificultad.facil) && (dificultad != NivelDificultad.dificil)) {
			dificultad = NivelDificultad.aleatorio;
		}		
		
		switch (dificultad) {
		case facil :
			preguntas = preguntaRepositorio.traerNPreguntasDificultadMenor(numPreguntas);
			break;
			
		case dificil:
			preguntas = preguntaRepositorio.traerNPreguntasDificultadMayor(numPreguntas);
			break;
			
		case aleatorio:
			preguntas = preguntaRepositorio.traerNPreguntas(numPreguntas);
			break;

		default:
			preguntas = preguntaRepositorio.traerNPreguntas(numPreguntas);
			break;
		}
		
		Collections.shuffle(preguntas);

		AlumnoConID alumno = (AlumnoConID) alumnoRepositorio.findById(idAlumno)
				.orElseThrow(() -> new RegisterNotFoundException(idAlumno, "Alumno"));

		ExamenConID examen = new ExamenConID();
		examen.setAlumno(alumno);
		
		// Vacios porque todavia no se ha hecho
		examen.setNota("");
		examen.setAciertos(0);
		examen.setFallos(0);
		
		
		examenRepositorio.save(examen);

		for (PreguntaConID pregunta : preguntas) {
			PreguntaExamenConID relacion = new PreguntaExamenConID();
			relacion.setPregunta(pregunta);
			relacion.setCorrecta(pregunta.getOpcionCorrecta());
			relacion.setExamen(examen);
			relacionRepositorio.save(relacion);
			preguntasExamen.add(relacion);
		}

		return assembler.toCollection(preguntasExamen);
	}

}