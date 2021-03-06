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
import com.openclassrooms.api.model.Child;
import com.openclassrooms.api.model.Email;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.model.FloodList;
import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.model.PersonCovered;
import com.openclassrooms.api.model.PersonInfo;
import com.openclassrooms.api.model.PersonLivingAtAnAddress;
import com.openclassrooms.api.repository.FirestationRepository;
import com.openclassrooms.api.service.FirestationService;
import com.openclassrooms.api.service.SafetyAlertService;

@RestController
public class SafetyAlertController {
	
	  @Autowired
	    private FirestationService firestationService;
	  @Autowired
	    private SafetyAlertService safetyAlertService;

//		http://localhost:8080/phoneAlert?firestation=<firestation_number>
	    @GetMapping("/phoneAlert")
	    public List<String> listOfPhoneNumberOfPersonByFirestation(@RequestParam("firestation") final String firestation) {
	       
	    	//return firestationService.getAddressByFirestation(firestation);
	    	return safetyAlertService.getPhoneNumberOfPersonByFirestation(firestation);
	    }
	    
	    //http://localhost:8080/childAlert?address=<address>
	    @GetMapping("/childAlert")
	    public List<Child> listOfChildAtAnAddress(@RequestParam("address") final String address)
	    {
	    	return safetyAlertService.getChildListFromAnAddress(address);
	    	
	    } 
	    
	    //http://localhost:8080/firestation?stationNumber=<station_number>
	    @GetMapping("/firestation")
	    public List<PersonCovered> listOfPersonCoveredByFirestation(@RequestParam("station") final String station)
	    {
	    	return safetyAlertService.getPersonCoveredByFirestation(station);
	    	
	    }
	    //http://localhost:8080/fire?address=<address>
	    @GetMapping("/fire")
	    public List<PersonLivingAtAnAddress> listOfPersonLivingAtAnAddress(@RequestParam("address") final String address)
	    {
	    	return safetyAlertService.getPersonLivingAtAnAddress(address);
	    }
	    
	    /* http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
Cette url doit retourner le nom, l'adresse, l'??ge, l'adresse mail et les ant??c??dents m??dicaux (m??dicaments,
posologie, allergies) de chaque habitant. Si plusieurs personnes portent le m??me nom, elles doivent
toutes appara??tre. */
	    @GetMapping("/personInfo")
	    public PersonInfo informationAboutPerson(@RequestParam("firstname") final String firstname, @RequestParam("lastname") final String lastname)
	    {
	    	return safetyAlertService.getInformationAboutPerson(firstname,lastname);	  
	    }
	 /*http://localhost:8080/flood/stations?stations=<a list of station_numbers>
Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
personnes par adresse. Elle doit aussi inclure le nom, le num??ro de t??l??phone et l'??ge des habitants, et
faire figurer leurs ant??c??dents m??dicaux (m??dicaments, posologie et allergies) ?? c??t?? de chaque nom.*/
	    @GetMapping("/flood/stations")
	    public List<FloodList> listOfHomeDeservedByFirestation(@RequestParam("station") final String station)
	    {
	    	return safetyAlertService.getListOfHomeDeservedByFirestation(station);
	    }
}







  