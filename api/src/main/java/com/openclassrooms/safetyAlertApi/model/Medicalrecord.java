package com.openclassrooms.safetyAlertApi.model;

import java.util.List;

import javax.persistence.Convert;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.safetyAlertApi.model.converter.StringListConverter;

import lombok.Data;



@Data
@Entity
@Table(name = "medicalrecords")
public class Medicalrecord {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty("firstName")
  private String firstname;
  @JsonProperty("lastName")
  private String lastname;
  
  private String birthdate;
  
  @Convert(converter = StringListConverter.class)
  private List<String> medications;
  
  @Convert(converter = StringListConverter.class)
  private List<String> allergies;
  
  //private String allergies;
  


}