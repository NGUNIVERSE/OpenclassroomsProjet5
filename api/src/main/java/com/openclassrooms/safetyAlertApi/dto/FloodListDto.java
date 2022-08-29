package com.openclassrooms.safetyAlertApi.dto;

import java.util.List;
import lombok.Data;

@Data
public class FloodListDto {
	/*
	 * http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	 * Cette url doit retourner une liste de tous les foyers desservis par la
	 * caserne. Cette liste doit regrouper les personnes par adresse. Elle doit
	 * aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et faire
	 * figurer leurs antécédents médicaux (médicaments, posologie et allergies) à
	 * côté de chaque nom.
	 */
	private String address;
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private long age;

	private List<String> medications;

	private List<String> allergies;

	public FloodListDto() {

	}

	public FloodListDto(String address, String firstname, String lastname, String phoneNumber, long age,
			List<String> medications, List<String> allergies) {
		this.address = address;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.medications = medications ;
		this.allergies = allergies ;
	}

}
