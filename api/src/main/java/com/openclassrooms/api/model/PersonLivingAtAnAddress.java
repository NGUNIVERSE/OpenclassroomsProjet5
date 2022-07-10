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
public class PersonLivingAtAnAddress {
	/* nom, le numéro de téléphone, l'âge et les antécédents
	médicaux (médicaments, posologie et allergies) de chaque personne. */
	
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private long age;
	private String medications;
	private String allergies;
	private String firestationNumber;
	
	public PersonLivingAtAnAddress(String firstname, String lastname, String phoneNumber, long age, String medications, String allergies, String firestationNumber)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
		this.firestationNumber = firestationNumber;
		
	}
	public PersonLivingAtAnAddress()
	{
		
	}
	
}
