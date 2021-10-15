package com.evaluacion;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.evaluacion.controllers.PersonasController;
import com.evaluacion.models.Personas;
import com.evaluacion.services.PersonasService;

@SpringBootApplication
public class EvaluacionApplication implements CommandLineRunner {

	@Autowired
	private PersonasService personaService;
	
	@Autowired
	private PersonasController personasController;
	
	public static void main(String[] args) {
		SpringApplication.run(EvaluacionApplication.class, args);
	}
	
	public void run(String... args) throws Exception{
		System.out.println("Hola mundo");

		//personaService.crearPersona(new Personas("1803","Leo123","Torres","ld.torres@","Tungurahua","Ambato", "Urdaneta", "21/07/2018", "09874563211"));
		
		String sDate1="31/12/1998";  
		    Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
		    System.out.println(sDate1+"\t"+date1);  
		personasController.crearPersona(new Personas("1801687805","Rosa","Valverde","rosa.valverde@","Tungurahua","Ambato", "Urdaneta", date1, "09874563211"));
		personaService.listarPersonas().forEach(p->{
			System.out.println(p.getCedula());
		});

	}

}
