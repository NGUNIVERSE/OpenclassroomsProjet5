package com.openclassrooms.safetyAlertApi.dto;

import lombok.Data;

@Data
public class PersonCoveredDto {

	private String firstname;
	private String lastname;
	private String address;
	private String phoneNumber;
	private int nombreAdulte;
	private int nombreEnfant;

	public PersonCoveredDto(String firstname, String lastname, String address, String phoneNumber, int nombreAdulte,
			int nombreEnfant) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.nombreAdulte = nombreAdulte;
		this.nombreEnfant = nombreEnfant;
	}

	public PersonCoveredDto(String firstname, String lastname, String address, String phoneNumber) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.nombreAdulte = 0;
		this.nombreEnfant = 0;
	}

	public PersonCoveredDto() {

	}

}
