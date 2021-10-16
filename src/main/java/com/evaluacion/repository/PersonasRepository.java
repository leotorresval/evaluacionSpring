package com.evaluacion.repository;

import org.springframework.data.repository.CrudRepository;

import com.evaluacion.models.Personas;

public interface PersonasRepository extends CrudRepository<Personas, String>{

	Personas findByCedula(String cedula);
}
