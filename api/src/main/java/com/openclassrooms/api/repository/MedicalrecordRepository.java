
package com.openclassrooms.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.api.model.Medicalrecord;



@Repository
public interface MedicalrecordRepository extends CrudRepository<Medicalrecord, Long> {

}