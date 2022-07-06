package com.openclassrooms.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.model.Email;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.model.Medicalrecord;
import com.openclassrooms.api.model.PersonInfos;
//import com.openclassrooms.api.service.CalculateAgeService;
import com.openclassrooms.api.repository.PersonRepository;
import com.openclassrooms.api.repository.MedicalrecordRepository;
import com.openclassrooms.api.repository.FirestationRepository;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
@Service
public class SafetyAlertService {
	
	@Autowired
	private FirestationService firestationService;
	@Autowired
	private PersonService personService;
	
	public List<String> getPhoneNumberOfPersonByFirestation(String firestation)
	{
		List<String> addressByFirestation = firestationService.getAddressByFirestation(firestation);
		List<Person> personByAddress = personService.getPersonPerAddress(addressByFirestation);
		List<String> phoneNumberOfPerson = personService.getPhoneNumberOfPerson(personByAddress);
		return phoneNumberOfPerson;
	}

}
