
package com.openclassrooms.safetyAlertApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyAlertApi.model.Medicalrecord;
import com.openclassrooms.safetyAlertApi.model.Person;



@Repository
public interface MedicalrecordRepository extends CrudRepository<Medicalrecord, Long> {

//	Medicalrecord findFirstMedicalrecordByPersonId(int personId);
	Medicalrecord findMedicalrecordByFirstnameAndLastname(String firstname, String lastname);
}