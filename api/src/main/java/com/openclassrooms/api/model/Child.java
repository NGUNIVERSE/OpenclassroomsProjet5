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
public class Child {
	
	private String firstname;
	private String lastname;
	private long age;
	private List<Person> homeMembres ;
	
	public Child(String firstname, String lastname, long age, List<Person> homeMembres)
	{
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.homeMembres = homeMembres;
	}

}
