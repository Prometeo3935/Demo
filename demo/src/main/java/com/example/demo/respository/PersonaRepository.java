package com.example.demo.respository;




import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Persona;



public interface PersonaRepository extends JpaRepository <Persona, Long> {

 List<Persona> findByFirstName(String estado) throws SQLException;
 
}
