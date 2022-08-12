package com.openclassrooms.safetyAlertApi.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;

import com.openclassrooms.safetyAlertApi.model.converter.StringListConverter;

import lombok.Data;

@Data
public class PersonInfoDto {

	/*
	 * Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les
	 * antécédents médicaux (médicaments, posologie, allergies) de chaque habitant.
	 * Si plusieurs personnes portent le même nom, elles doivent toutes apparaître.
	 */
	private String firstname;
	private String lastname;
	private String address;
	private long age;
	private String email;
	// private String medications;
	@Convert(converter = StringListConverter.class)
	private List<String> medications;
	@Convert(converter = StringListConverter.class)
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
		this.medications = medications == null ? null : new ArrayList<>(medications);
		this.allergies = allergies == null ? null : new ArrayList<>(allergies);
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies == null ? null : new ArrayList<>(allergies);
	}

	public List<String> getAllergies() {
		return allergies = allergies == null ? null : new ArrayList<>(allergies);
	}

	public void setMedications(List<String> medications) {
		this.medications = medications == null ? null : new ArrayList<>(medications);
	}

	public List<String> getMedications() {
		return medications = medications == null ? null : new ArrayList<>(medications);
	}
}
