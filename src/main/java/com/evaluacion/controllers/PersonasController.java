package com.evaluacion.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.models.Personas;
import com.evaluacion.services.PersonasService;
import com.evaluacion.utils.Utilitarios;


@RestController
@RequestMapping
public class PersonasController {

	private static final Logger logger =  LoggerFactory.getLogger(PersonasController.class);
	private  PersonasService personaService;
	
	
	
	public PersonasController(PersonasService personaService) {
		super();
		this.personaService = personaService;
	}

	@GetMapping(value = "/persona")
	public List<Personas> listarPersonas() throws Exception{
		try {
			return personaService.listarPersonas();
		} catch (Exception e) {
			logger.info("Error en el consumo del servicio listar persona, "+e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	
	@PostMapping(value = "/persona")
	public String crearPersona(@RequestBody @Validated Personas persona)  {
		try {
			validarPersona(persona);
			if(personaService.buscarCedula(persona)!=null)
				throw new Exception("Esta persona ya existe en el sistema");
			personaService.crearPersona(persona);
			return "Persona creada";
		} catch (Exception e) {
			logger.info("Error en el consumo del servicio guardar persona, "+e.getMessage());
			//throw new Exception(e.getMessage());
			return e.getMessage();
		}
	}
	
	@PutMapping(value = "/persona")
	public String actualizarPersona(@RequestBody @Validated Personas persona)  {
		try {
			validarPersona(persona);
			if(personaService.buscarCedula(persona)==null)
				throw new Exception("Esta persona no existe en el sistema, no se puede actualizar");
			personaService.crearPersona(persona);
			return "Persona actualizada";
		} catch (Exception e) {
			logger.info("Error en el consumo del servicio actualizar persona, "+e.getMessage());
			return e.getMessage();
		}
	}
	
	@DeleteMapping(value = "/persona")
	public String eliminarPersona(@RequestBody @Validated Personas persona) throws Exception {
		try {
			personaService.eliminarPersona(persona);
			return "Persona eliminada";
		} catch (Exception e) {
			logger.info("Error en el consumo del servicio eliminar persona, "+e.getMessage());
			throw new Exception(e.getMessage());
		}
		
	}
	
	public void validarPersona(Personas p) throws Exception{
		if(Utilitarios.verificarNoVacio(p.getCedula()))
			throw new Exception("La cedula es un campo requerido");
		if(!Utilitarios.verificarNumeros(p.getCedula()))
			throw new Exception("La cedula solo puede contener numeros");
		if(!Utilitarios.verificarCedula(p.getCedula()))
			throw new Exception("La cedula es incorrecta");
		if(Utilitarios.verificarLetras(p.getNombres()))
			throw new Exception("El nombre solo puede contener letras");
		if(Utilitarios.verificarLetras(p.getApellidos()))
			throw new Exception("El apellido solo puede contener letras");
		if(Utilitarios.verificarLetras(p.getProvincia()))
			throw new Exception("La provincia solo puede contener letras");
		if(Utilitarios.verificarLetras(p.getCanton()))
			throw new Exception("El canton solo puede contener letras");
		if(!Utilitarios.verificarNumeros(p.getTelefono()))
			throw new Exception("El telefono solo puede contener numeros");
		if(Utilitarios.verificarCorreo(p.getEmail()))
			throw new Exception("El correo ingresado es erroneo");
	}
}
