package com.example.demo.Dto;

import java.util.List;

public class PersonaDto {

	private String id;
	private String firstName;
	private String lastName;
	private String birthdate;
	private String hasinsurance;
	private List<PeliculaDto> favouriteMovies;	

	public PersonaDto() {		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getHasinsurance() {
		return hasinsurance;
	}

	public void setHasinsurance(String hasinsurance) {
		this.hasinsurance = hasinsurance;
	}

	public List<PeliculaDto> getFavouriteMovies() {
		return favouriteMovies;
	}

	public void setFavouriteMovies(List<PeliculaDto> favouriteMovies) {
		this.favouriteMovies = favouriteMovies;
	}


}
