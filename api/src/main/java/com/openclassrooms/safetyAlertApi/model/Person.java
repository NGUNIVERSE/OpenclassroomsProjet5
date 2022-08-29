package com.openclassrooms.safetyAlertApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Person {

	private Long id;

	@JsonProperty("firstName")
	private String firstname;

	@JsonProperty("lastName")
	private String lastname;

	private String address;

	private String city;

	private String zip;

	private String phone;

	private String email;

}