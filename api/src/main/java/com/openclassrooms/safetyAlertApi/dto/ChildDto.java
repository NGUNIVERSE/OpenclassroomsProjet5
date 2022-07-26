package com.openclassrooms.safetyAlertApi.dto;
import java.util.List;

import com.openclassrooms.safetyAlertApi.model.Person;

import lombok.Data;


@Data
public class ChildDto {
	
	private String firstname;
	private String lastname;
	private long age;
	private List<Person> homeMembres ;
	
	public ChildDto(String firstname, String lastname, long age, List<Person> homeMembres)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.homeMembres = homeMembres;
	}

}
