package com.openclassrooms.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.model.PersonCovered;
import com.openclassrooms.api.model.PersonInfo;
import com.openclassrooms.api.model.Child;
import com.openclassrooms.api.model.Email;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.model.Medicalrecord;
import com.openclassrooms.api.model.PersonInfos;
import com.openclassrooms.api.model.PersonLivingAtAnAddress;
//import com.openclassrooms.api.service.CalculateAgeService;
import com.openclassrooms.api.repository.PersonRepository;
import com.sun.jmx.mbeanserver.Repository;
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
	
	public List<PersonCovered> getPersonCoveredByFirestation(String station)
	{
		List<PersonCovered> personCovered = new ArrayList<>();
		List<String> addressByFirestation = firestationService.getAddressByFirestation(station);
		List<Person> personByAddress = personService.getPersonPerAddress(addressByFirestation);
		
		
		Medicalrecord medicalrecord = new Medicalrecord();
		//Person person = new Person();
		
		int i = 0;
		long age = 0;
		int nombreAdulte = 0 ;
		int nombreEnfant = 0 ;
		
		for(i=0;i<personByAddress.size();i++)
		{ 
			
			//PersonCovered personCovereds = new PersonCovered();
			//person = listOfPersonAtAnAddress.get(i);
			medicalrecord = medicalrecordService.findMedicalrecordByFirstnameAndLastname(personByAddress.get(i).getFirstname(), personByAddress.get(i).getLastname() );
			//calcul de l'age
			age = getAge(medicalrecord.getBirthdate());
			PersonCovered personCovereds = new PersonCovered(personByAddress.get(i).getFirstname(),personByAddress.get(i).getLastname(),
			personByAddress.get(i).getAddress(),personByAddress.get(i).getPhone() );

			
			if(age <= 18)
			{	//si oui ajouter son nom à la liste child
				nombreEnfant++;
			}
			else
			{	
				nombreAdulte++;
			}			
			
			personCovered.add(personCovereds);
		}
		for(i=0;i<personCovered.size();i++)
		{
			personCovered.get(i).setNombreEnfant(nombreEnfant);
			personCovered.get(i).setNombreAdulte(nombreAdulte);
		}
		//List des adress parfirestation
		//liste des person par adresse
		//nombre dadulte et d'enfant
		
		return personCovered;
	}
	
	public List<PersonLivingAtAnAddress> getPersonLivingAtAnAddress(String address)
	{
		List<PersonLivingAtAnAddress> listOfPersonLivingAtAnAddress = new ArrayList<>();
		List<Person> listOfPerson = personService.getPersonPerAddress(address);
		Firestation firestation = firestationService.getFirestationByAddress(address);
		Medicalrecord medicalrecord = new Medicalrecord();
		int i = 0;
		for(i=0; i<listOfPerson.size();i++)
		{
			//PersonLivingAtAnAddress personAtAnAddress = new PersonLivingAtAnAddress();
			medicalrecord = medicalrecordService.findMedicalrecordByFirstnameAndLastname(listOfPerson.get(i).getFirstname(),listOfPerson.get(i).getLastname());
			PersonLivingAtAnAddress personAtAnAddress = new PersonLivingAtAnAddress(listOfPerson.get(i).getFirstname(),
									   listOfPerson.get(i).getLastname(),
									   listOfPerson.get(i).getPhone(),
									   getAge(medicalrecord.getBirthdate()),
									   medicalrecord.getMedications(),
									   medicalrecord.getAllergies(),firestation.getStation());
			listOfPersonLivingAtAnAddress.add(personAtAnAddress);
		}
		//getpersonbyaddress
		//getfirestationnumberbyaddress
		//getmedicalrecord
		
		
		
		return listOfPersonLivingAtAnAddress;
	}
    /* http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
toutes apparaître. */
	public PersonInfo getInformationAboutPerson(String firstname,String lastname)
	{ 
		PersonInfo personInfo = new PersonInfo();
		Person person = new Person();
		Medicalrecord medicalrecord = new Medicalrecord();
		person = personService.getPersonByFirstnameAndLastname(firstname, lastname);
		medicalrecord = medicalrecordService.findMedicalrecordByFirstnameAndLastname(firstname, lastname);
		personInfo.setFirstname(person.getFirstname());
		personInfo.setLastname(person.getLastname());
		personInfo.setAddress(person.getAddress());
		personInfo.setAge(getAge(medicalrecord.getBirthdate()));
		personInfo.setEmail(person.getEmail());
		personInfo.setMedications(medicalrecord.getMedications());
		personInfo.setAllergies(medicalrecord.getAllergies());
		
		return personInfo;
	}
}

