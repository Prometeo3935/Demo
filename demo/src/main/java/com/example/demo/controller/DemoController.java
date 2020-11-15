package com.example.demo.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {	

	@RequestMapping(value= "/cliente/{clienteId}", method = RequestMethod.GET)
	public String index(@PathVariable String clienteId) {
		return "El Numero de cliente es: " + clienteId;
	}
	
	@RequestMapping(value= "/prueba", method = RequestMethod.GET)
	public String prueba() {
		return "Esto es una prueba " ;
	}

}
