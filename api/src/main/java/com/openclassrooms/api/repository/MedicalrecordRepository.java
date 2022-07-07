
package com.openclassrooms.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.api.model.Medicalrecord;
import com.openclassrooms.api.model.Person;



@Repository
public interface MedicalrecordRepository extends CrudRepository<Medicalrecord, Long> {

//	Medicalrecord findFirstMedicalrecordByPersonId(int personId);
	Medicalrecord findMedicalrecordByFirstnameAndLastname(String firstname, String lastname);
}