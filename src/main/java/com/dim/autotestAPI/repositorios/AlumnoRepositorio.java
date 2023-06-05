package com.dim.autotestAPI.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dim.autotestAPI.entidades.AlumnoConID;

@Repository
public interface AlumnoRepositorio extends JpaRepository<AlumnoConID, Long> {

}
