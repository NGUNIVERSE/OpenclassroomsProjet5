package com.openclassrooms.safetyAlertApi.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChildDto {

	private String firstname;
	private String lastname;
	private long age;
	private List<HomeMembresDto> homeMembres;

	public ChildDto(String firstname, String lastname, long age, List<HomeMembresDto> homeMembres) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	 this.homeMembres = homeMembres;
	
	}

	public ChildDto(String firstname, String lastname, long age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;

	}


}
