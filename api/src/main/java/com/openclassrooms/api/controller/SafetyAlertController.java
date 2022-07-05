package com.openclassrooms.api.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.api.model.AddressByFirestationNumber;
import com.openclassrooms.api.model.Email;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.repository.FirestationRepository;
import com.openclassrooms.api.service.FirestationService;

@RestController
public class SafetyAlertController {
	
	  @Autowired
	    private FirestationService firestationService;

//		http://localhost:8080/phoneAlert?firestation=<firestation_number>
	    @GetMapping("/phoneAlert")
	    public List<String> listOfAddressByFirestationNumber(@RequestParam("firestation") final String firestation) {
	       
	    	return firestationService.getAddressByFirestation(firestation);
	    }
	 
	}







  