package com.dim.autotestAPI.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dim.autotestAPI.entidades.PreguntaExamenConID;

@Repository
public interface PreguntaExamenRepositorio extends JpaRepository<PreguntaExamenConID, Long> {

}
