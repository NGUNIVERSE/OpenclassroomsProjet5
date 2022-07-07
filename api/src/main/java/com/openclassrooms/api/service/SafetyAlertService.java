package com.openclassrooms.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.model.Child;
import com.openclassrooms.api.model.Email;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.model.Medicalrecord;
import com.openclassrooms.api.model.PersonInfos;
//import com.openclassrooms.api.service.CalculateAgeService;
import com.openclassrooms.api.repository.PersonRepository;
import com.openclassrooms.api.repository.MedicalrecordRepository;
import com.openclassrooms.api.repository.FirestationRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.Data;

@Data
@Service
public class SafetyAlertService {
	
	@Autowired
	private FirestationService firestationService;
	@Autowired
	private PersonService personService;
	@Autowired
	private MedicalrecordService medicalrecordService;
	
	public List<String> getPhoneNumberOfPersonByFirestation(String firestation)
	{
		List<String> addressByFirestation = firestationService.getAddressByFirestation(firestation);
		List<Person> personByAddress = personService.getPersonPerAddress(addressByFirestation);
		List<String> phoneNumberOfPerson = personService.getPhoneNumberOfPerson(personByAddress);
		return phoneNumberOfPerson;
	}
	
	public List<Child> getChildListFromAnAddress(String address)
	{
		List<Child> listOfChild = new ArrayList<>();
		List<Person> listOfPersonAtAnAddress = new ArrayList<>();
		List<Person> homeMembers = new ArrayList<>();
		listOfPersonAtAnAddress = personService.getPersonPerAddress(address);
		Medicalrecord medicalrecord = new Medicalrecord();
		Person person = new Person();
		
		int i = 0;
		long age = 0;
		for(i=0;i<listOfPersonAtAnAddress.size();i++)
		{ 
			person = listOfPersonAtAnAddress.get(i);
			medicalrecord = medicalrecordService.findMedicalrecordByFirstnameAndLastname(person.getFirstname(), person.getLastname() );
			//calcul de l'age
			age = getAge(medicalrecord.getBirthdate());
		
			if(age <= 18)
			{	//si oui ajouter son nom à la liste child
				
				Child child = new Child(person.getFirstname(),person.getLastname(), age ,listOfPersonAtAnAddress);
				listOfChild.add(child);
			}
			else
			{	
				
			}			
				
				//si oui ajouter son nom à la liste child
				//si non ajouter son nom dans la liste des homemembers
	
		}
		
		return listOfChild;
	} 
	public long getAge(String birthDate) {
        long calculAge = 0;

        if (!birthDate.equals("TbD")) {
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
                    .withLocale(Locale.FRENCH);

            LocalDate date = LocalDate.parse(birthDate, formatter);
            calculAge = Period.between(date, localDate).getYears();
        } else {
            return 0;
        }

        //return String.valueOf(calculAge);
        return calculAge;
    }
}
