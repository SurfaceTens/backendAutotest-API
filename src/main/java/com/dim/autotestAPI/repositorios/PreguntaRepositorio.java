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
	
	// Para la herencia en el put
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.preguntas SET "
					+ "adjunto = 'I', "
					+ "imagenurl = :imagenURL, "
					+ "videourl = null "
					+ "WHERE id = :id", nativeQuery = true)
	void actualizarImagen(
		    @Param("imagenURL") String imagenURL,
		    @Param("id") Long id
		);
	
	// Para la herencia en el put
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.preguntas SET "
					+ "adjunto = 'V', "
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
	
}
