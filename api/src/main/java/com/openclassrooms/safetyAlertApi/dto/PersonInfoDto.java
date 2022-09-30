package com.openclassrooms.safetyAlertApi.dto;

import java.util.List;

import lombok.Data;

@Data
public class PersonInfoDto {

	private String firstname;
	private String lastname;
	private String address;
	private long age;
	private String email;

	private List<String> medications;

	private List<String> allergies;

	public PersonInfoDto() {

	}

	public PersonInfoDto(String firstname, String lastname, String address, long age, String email,
			List<String> medications, List<String> allergies) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.age = age;
		this.email = email;
		this.medications = medications;
		this.allergies = allergies;
	}

}
