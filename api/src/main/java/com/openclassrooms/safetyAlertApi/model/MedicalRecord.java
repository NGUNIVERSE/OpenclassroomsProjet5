package com.openclassrooms.safetyAlertApi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MedicalRecord {

	private Long id;

	@JsonProperty("firstName")
	private String firstname;
	@JsonProperty("lastName")
	private String lastname;

	private String birthdate;

	private List<String> medications;

	private List<String> allergies;

}