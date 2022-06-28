package com.openclassrooms.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.api.model.Firestation;




@Repository
public interface FirestationRepository extends CrudRepository<Firestation, Long> {

}