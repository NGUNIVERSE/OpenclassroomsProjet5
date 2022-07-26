package com.openclassrooms.safetyAlertApi.dto;

public class HomeMembresDto {

	private String firstname;
	private String lastname;
	
	public HomeMembresDto()
	{
		
	}
	
	public HomeMembresDto(String firstname,String lastname)
	{
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
}
