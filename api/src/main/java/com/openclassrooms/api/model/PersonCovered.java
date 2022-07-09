package com.openclassrooms.api.model;
import java.util.List;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


@Data
public class PersonCovered {
	
	private String firstname;
	private String lastname;
	private String address;
	private String phoneNumber;
	private int nombreAdulte;
	private int nombreEnfant;
	
	
	public PersonCovered(String firstname, String lastname, String address, String phoneNumber, int nombreAdulte, 
			int nombreEnfant)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.nombreAdulte = nombreAdulte;
		this.nombreEnfant = nombreEnfant;
	}
	
	public PersonCovered(String firstname, String lastname, String address, String phoneNumber)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.nombreAdulte = 0;
		this.nombreEnfant = 0;
	}


}



