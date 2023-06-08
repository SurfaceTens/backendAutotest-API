package com.dim.autotestAPI.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dim.autotestAPI.entidades.PreguntaConID;

@Repository
public interface PreguntaRepositorio extends JpaRepository<PreguntaConID, Long>  {
	
	// Para la herencia en el put
//	void actualizarImagen(
//		    @Param("imagenURL") String imagenURL,
//		    @Param("id") Long id
//		);
	
	// Para la herencia en el put
//	void actualizarVideo(
//		    @Param("videoURL") String videoURL,
//		    @Param("id") Long id
//		);

}
