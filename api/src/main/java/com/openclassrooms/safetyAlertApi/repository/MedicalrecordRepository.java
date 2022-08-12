
package com.openclassrooms.safetyAlertApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyAlertApi.model.Medicalrecord;

@Repository
public interface MedicalrecordRepository extends CrudRepository<Medicalrecord, Long> {

	Medicalrecord findMedicalrecordByFirstnameAndLastname(String firstname, String lastname);
}