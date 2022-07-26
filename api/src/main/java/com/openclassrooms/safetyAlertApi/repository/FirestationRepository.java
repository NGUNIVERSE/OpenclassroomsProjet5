package com.openclassrooms.safetyAlertApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyAlertApi.dto.AddressByFirestationNumberDto;
import com.openclassrooms.safetyAlertApi.model.Firestation;

import java.util.List;



@Repository
public interface FirestationRepository extends CrudRepository<Firestation, Long> {

	// List<Firestation> findFirestationByAddress(String address);
	// List<Firestation> findAddressByFirestation(String station);
	 List<Firestation> findByStation(String station);
	 Firestation findFirestationByAddress(String address);
}