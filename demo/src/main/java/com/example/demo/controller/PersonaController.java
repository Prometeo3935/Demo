package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.demo.Dto.PeliculaDto;
import com.example.demo.Dto.PersonaDto;
import com.example.demo.entities.Pelicula;
import com.example.demo.entities.Persona;
import com.example.demo.service.PersonaService;

@RestController
public class PersonaController {

	@Autowired
	PersonaService personaService;

	@RequestMapping(value = "/healts", method = RequestMethod.GET)
	public String prueba() {
		return "Url de Prueba ";
	}

	@PostMapping(value = "/persona/alta", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> SavePersona(@RequestBody PersonaDto personaDto) throws ParseException {

		System.out.println("Esta es la pelicula 0: " + personaDto.getFavouriteMovies().get(0));

		Persona persona = new Persona();
		persona.setId(Long.parseLong(personaDto.getId()));
		persona.setFirstName(personaDto.getFirstName());
		persona.setLastName(personaDto.getLastName());
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = null;
		fechaDate = formato.parse(personaDto.getBirthdate());
		persona.setBirthdate(fechaDate);
		
		List<PeliculaDto> lstpeliculadto = personaDto.getFavouriteMovies();
		
		List<Pelicula> lstPelicula = null;
		
		Pelicula pelicula = null;
		
		for (PeliculaDto peli : lstpeliculadto) {
			
			pelicula.setTitle(peli.getTitle());
			pelicula.setGenre(peli.getGenre());      
			lstPelicula.add(pelicula);
		     
		}		

		 persona.setPeliculas(lstPelicula);

		try {

			personaService.save(persona);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return new ResponseEntity(personaDto, HttpStatus.OK);

	}
	
	@PutMapping(value = "/persona/modificar", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> updatePersona(@RequestBody PersonaDto personaDto) throws ParseException {		

		Persona persona = new Persona();
		persona.setId(Long.parseLong(personaDto.getId()));
		persona.setFirstName(personaDto.getFirstName());
		persona.setLastName(personaDto.getLastName());
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = null;
		fechaDate = formato.parse(personaDto.getBirthdate());
		persona.setBirthdate(fechaDate);


		try {

			personaService.update(persona);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return new ResponseEntity(personaDto.toString(), HttpStatus.OK);

	}

	@GetMapping(value = "/persona/consultar/{personaId}")

	ResponseEntity<String> getPersona(@PathVariable String personaId)
			throws ParseException, NumberFormatException, SQLException {

		Optional<Persona> persona = personaService.findById(Long.parseLong(personaId));

		return new ResponseEntity(persona.toString(), HttpStatus.OK);

	}

	@GetMapping(value = "/persona/consultar/nombre/{nombre}")

	ResponseEntity<String> listarPorNombre(@PathVariable String nombre) throws ParseException, NumberFormatException, SQLException {

		List<Persona> lstpersona = personaService.findByFirstName(nombre);

		return new ResponseEntity(lstpersona, HttpStatus.OK);

	}
	
	@GetMapping(value = "/persona/listar")

	ResponseEntity<String> listarPersonas( ) throws ParseException, NumberFormatException, SQLException {

		List<Persona> lstpersonas = personaService.findByAll();

		return new ResponseEntity(lstpersonas, HttpStatus.OK);

	}

	@DeleteMapping(value = "/persona/eliminar/{personaId}")

	ResponseEntity<String> deletePersona(@PathVariable String personaId) throws ParseException, NumberFormatException, SQLException {

		personaService.deleteById(Long.parseLong(personaId));

		return new ResponseEntity("Registro Eliminado con Exito", HttpStatus.OK);

	}	
	
	@GetMapping(value = "/persona/peliculas/listar/{personaId}")

	ResponseEntity<String> getPeliculas(@PathVariable String personaId) throws ParseException, NumberFormatException, SQLException {

		List<Pelicula> lstpeliculas = personaService.getPeliculas(Long.parseLong(personaId));

		return new ResponseEntity(lstpeliculas, HttpStatus.OK);

	}
	
	@DeleteMapping(value = "/persona/peliculas/eliminar/{personaId}/{titleMovie}")

	ResponseEntity<String> deletePelicula(@PathVariable String personaId,@PathVariable String titleMovie) {

		personaService.deletePelicula(personaId,titleMovie);
		
		return new ResponseEntity("Pelicula eliminada con exito", HttpStatus.OK);

	}
	
	@PostMapping(value = "/persona/pelicula/agregar/{personaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> SavePelicula(@RequestBody PeliculaDto peliculadto,@PathVariable String personaId) throws ParseException, NumberFormatException, SQLException {
		
	  Pelicula pelicula = null;	  
	  pelicula.setTitle(peliculadto.getTitle());
	  pelicula.setGenre(peliculadto.getGenre());
	  
	  // traigo la lista de peliculas y agrego la nueva
	  personaService.findById(Long.parseLong(personaId)).get().getPeliculas().add(pelicula);
	  	
	  return new ResponseEntity("Pelicula insertada con exito", HttpStatus.OK);
	}

}
