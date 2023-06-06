package com.dim.autotestAPI.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dim.autotestAPI.entidades.ExamenConID;

@Repository
public interface ExamenRepositorio extends JpaRepository<ExamenConID, Long> {

}
