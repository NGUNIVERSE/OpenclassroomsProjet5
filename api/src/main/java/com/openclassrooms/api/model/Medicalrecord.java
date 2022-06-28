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
@Table(name = "medicalrecords")
public class Medicalrecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String firstname;

  private String lastname;
  
  private String birthdate;
  
  private String medications;
  
  private String allergies;
  


}