package com.dim.autotestAPI.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dim.autotestAPI.entidades.PreguntaConID;

@Repository
public interface PreguntaRepositorio extends JpaRepository<PreguntaConID, Long>  {

}
