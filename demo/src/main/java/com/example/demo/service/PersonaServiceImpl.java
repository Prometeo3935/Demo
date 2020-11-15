package com.example.demo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Pelicula;
import com.example.demo.entities.Persona;
import com.example.demo.respository.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	PersonaRepository personaRep;

	@Override
	public List<Persona> findByFirstName(String nombre) {
		// TODO Auto-generated method stub

		List<Persona> lstPersona = new ArrayList<>();

		try {

			lstPersona = personaRep.findByFirstName(nombre);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lstPersona;

	}

	@Override
	public Optional<Persona> findById(Long Id) throws SQLException {
		// TODO Auto-generated method stub
		return personaRep.findById(Id);
	}

	@Override
	public void deleteById(Long Id) throws SQLException {
		
		personaRep.deleteById(Id);
		
	}

	@Override
	public Persona save(Persona persona) throws SQLException {
		// TODO Auto-generated method stub
		return personaRep.save(persona);
		
	}

	@Override
	public List<Persona> findByAll() throws SQLException {
		// TODO Auto-generated method stub
		return personaRep.findAll();
	}

	@Override
	public void update(Persona persona) {
	
		// CrudRepository solo tiene metodo save pero actua como update tambien
		personaRep.save(persona);
	
		
	}

	@Override
	public List<Pelicula> getPeliculas(Long PersonaId) {
		// TODO Auto-generated method stub
		// retorno peliculas de la persona
		return personaRep.findById(PersonaId).get().getPeliculas();
	}

	@Override
	public void deletePelicula(String personaId, String titleMovie) {
		
		Long indice;
		
		List<Pelicula> lstPeliculas = personaRep.findById(Long.parseLong(personaId)).get().getPeliculas();	
		
		// busco la peli por titulo y elimino
		
		for (Pelicula peli : lstPeliculas) {
		    if (peli.getTitle() == "titleMovie" ) {
		    	indice = peli.getId();
		    	personaRep.findById(Long.parseLong(personaId)).get().getPeliculas().remove(indice);
		    	
		    }
		}
		
		
	}

}
