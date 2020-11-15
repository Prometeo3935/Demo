package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Pelicula;


public interface PeliculaRepository extends JpaRepository <Pelicula, String> {
	
	
	

}
