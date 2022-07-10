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
public class PersonInfo {

	/*Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
toutes apparaître.*/
	private String firstname;
	private String lastname;
	private String address;
	private long age;
	private String email;
	private String medications;
	private String allergies;
	
	public PersonInfo()
	{
		
	}
	
	public PersonInfo(String firstname, String lastname, String address, long age, String email, String medications, String allergies)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.age = age;
		this.email = email;
		this.medications = medications;
		this.allergies = allergies;
	}
	
}
