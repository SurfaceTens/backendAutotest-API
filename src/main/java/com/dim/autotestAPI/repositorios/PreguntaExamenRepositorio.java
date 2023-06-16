package com.dim.autotestAPI.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dim.autotestAPI.entidades.PreguntaExamenConID;

import jakarta.transaction.Transactional;

@Repository
public interface PreguntaExamenRepositorio extends JpaRepository<PreguntaExamenConID, Long> {

}
