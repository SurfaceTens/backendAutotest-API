package com.dim.autotestAPI.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dim.autotestAPI.entidades.PreguntaConID;

import jakarta.transaction.Transactional;

@Repository
public interface PreguntaRepositorio extends JpaRepository<PreguntaConID, Long>  {
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.preguntas SET "
					+ "adjunto = 'ninguno', "
					+ "imagenurl = null, "
					+ "videourl = null "
					+ "WHERE id = :id", nativeQuery = true)
	void actualizarNinguno(
		    @Param("id") Long id
		);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.preguntas SET "
					+ "adjunto = 'imagen', "
					+ "imagenurl = :imagenURL, "
					+ "videourl = null "
					+ "WHERE id = :id", nativeQuery = true)
	void actualizarImagen(
		    @Param("imagenURL") String imagenURL,
		    @Param("id") Long id
		);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.preguntas SET "
					+ "adjunto = 'video', "
					+ "videourl = :videoURL, "
					+ "imagenurl = null "
					+ "WHERE id = :id", nativeQuery = true)
	void actualizarVideo(
		    @Param("videoURL") String videoURL,
		    @Param("id") Long id
		);
	
	@Transactional
	@Query(value = "SELECT * FROM public.preguntas ORDER BY RANDOM() "
					+ "LIMIT :numPreguntas", nativeQuery = true)
	List<PreguntaConID> traerNPreguntas (@Param("numPreguntas") int numPreguntas);
	
	@Transactional
	@Query(value = "SELECT * FROM public.preguntas "
					+ "WHERE dificultad >= 50 "
					+ "ORDER BY RANDOM() "
					+ "LIMIT :numPreguntas", nativeQuery = true)
	List<PreguntaConID> traerNPreguntasDificultadMayor (@Param("numPreguntas") int numPreguntas);
	
	@Transactional
	@Query(value = "SELECT * FROM public.preguntas "
					+ "WHERE dificultad <= 50 "
					+ "ORDER BY RANDOM() "
					+ "LIMIT :numPreguntas", nativeQuery = true)
	List<PreguntaConID> traerNPreguntasDificultadMenor (@Param("numPreguntas") int numPreguntas);
	
}
