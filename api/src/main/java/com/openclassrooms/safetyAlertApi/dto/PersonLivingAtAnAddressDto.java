package com.openclassrooms.safetyAlertApi.dto;
import java.util.ArrayList;
import java.util.List;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;


@Data
public class PersonLivingAtAnAddressDto {
	/* nom, le numéro de téléphone, l'âge et les antécédents
	médicaux (médicaments, posologie et allergies) de chaque personne. */
	
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private long age;
	private List<String> medications;
	private List<String> allergies;
	private String firestationNumber;
	
	public PersonLivingAtAnAddressDto(String firstname, String lastname, String phoneNumber, long age, List<String> medications, List<String> allergies, String firestationNumber)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.medications = medications == null ? null : new ArrayList<>(medications);
		this.allergies = allergies == null ? null : new ArrayList<>(allergies);
		this.firestationNumber = firestationNumber;
		
	}
	public PersonLivingAtAnAddressDto()
	{
		
	}
	public void setMedications(List<String> medications)
	  {
		  this.medications = medications == null ? null : new ArrayList<>(medications);
	  }
	  public List<String> getMedications()
	  {
		  return medications = medications == null ? null : new ArrayList<>(medications);
	  }
	  public void setAllergies(List<String> allergies)
	  {
		  this.allergies = allergies == null ? null : new ArrayList<>(allergies);
	  }
	  public List<String> getAllergies()
	  {
		return allergies = allergies == null ? null : new ArrayList<>(allergies);  
	  }
	
}
