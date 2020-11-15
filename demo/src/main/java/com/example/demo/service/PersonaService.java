package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Pelicula;
import com.example.demo.entities.Persona;

public interface PersonaService {
		
	List<Persona> findByFirstName(String nombre) throws SQLException;
	
	List<Persona> findByAll() throws SQLException;
	
	Optional<Persona> findById(Long Id) throws SQLException;
	
	
	void deleteById(Long Id) throws SQLException;
	
	Persona save(Persona persona) throws SQLException;

	void update(Persona persona) throws SQLException;

	List<Pelicula> getPeliculas(Long PersonaId);

	void deletePelicula(String personaId, String titleMovie);

}
