	package com.openclassrooms.api.model;
	//import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;
	import lombok.Data;
	
	@Data
	@Entity
	@Table(name = "firestations")
	public class Firestation {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;


	    private String address;

	    
	    private String station;

public Firestation( Long id, String address, String station)
{
	this.id = id;
	this.address = address;
	this.station = station;
}
public Firestation()
{
	
}

	}
	

