package com.openclassrooms.safetyAlertApi.model;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//@Column(name = "firstname")
    @JsonProperty("firstName")
    private String firstname;

//@Column(name = "lastname")
    @JsonProperty("lastName")
    private String lastname;
    
    private String address;
    
    private String city;
    
    private String zip;
    
    private String phone;
    
    private String email;


}