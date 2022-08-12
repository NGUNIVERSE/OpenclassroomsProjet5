package com.openclassrooms.safetyAlertApi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyAlertApi.model.Firestation;

@Repository
public interface FirestationRepository extends CrudRepository<Firestation, Long> {

	List<Firestation> findByStation(String station);

	Firestation findFirestationByAddress(String address);
}