package com.openclassrooms.safetyAlertApi.dto;

import lombok.Data;

@Data
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
