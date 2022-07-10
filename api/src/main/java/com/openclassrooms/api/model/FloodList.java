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
public class FloodList {
	 /*http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
	personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
	faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.*/
private String address;
private String firstname;
private String lastname;
private String phoneNumber;
private long age;
private String medications;
private String allergies;

public FloodList() {
	
}
public FloodList(String address, String firstname, String lastname, String phoneNumber, long age, String medications, String allergies)
{
	this.address = address;
	this.firstname = firstname;
	this.lastname = lastname;
	this.phoneNumber = phoneNumber;
	this.age = age;
	this.medications = medications;
	this.allergies = allergies;
}

}
