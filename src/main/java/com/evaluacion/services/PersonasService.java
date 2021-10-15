package com.evaluacion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluacion.models.Personas;
import com.evaluacion.repository.PersonasRepository;

@Service
public class PersonasService {

	@Autowired
	private PersonasRepository personasRepository;


	public PersonasService(PersonasRepository personasRepository) {
		super();
		this.personasRepository = personasRepository;
	}

	public List<Personas> listarPersonas(){
		return (List<Personas> )personasRepository.findAll();
	}
	
	public Personas crearPersona(Personas persona) {
		return personasRepository.save(persona);
	}
	
	public void eliminarPersona(Personas persona) {
		personasRepository.delete(persona);
	}
}
