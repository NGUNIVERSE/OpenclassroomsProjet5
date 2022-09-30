package com.openclassrooms.safetyAlertApi.dto;

import java.util.List;

import lombok.Data;

@Data
public class PersonLivingAtAnAddressDto {

	private String firstname;
	private String lastname;
	private String phoneNumber;
	private long age;
	private List<String> medications;
	private List<String> allergies;
	private String firestationNumber;

	public PersonLivingAtAnAddressDto(String firstname, String lastname, String phoneNumber, long age,
			List<String> medications, List<String> allergies, String firestationNumber) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
		this.firestationNumber = firestationNumber;

	}

	public PersonLivingAtAnAddressDto() {

	}

}
