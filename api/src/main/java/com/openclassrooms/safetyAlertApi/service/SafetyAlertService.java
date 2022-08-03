package com.openclassrooms.safetyAlertApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyAlertApi.dto.ChildDto;
import com.openclassrooms.safetyAlertApi.dto.EmailDto;
import com.openclassrooms.safetyAlertApi.dto.FloodListDto;
import com.openclassrooms.safetyAlertApi.dto.HomeMembresDto;
import com.openclassrooms.safetyAlertApi.dto.PersonCoveredDto;
import com.openclassrooms.safetyAlertApi.dto.PersonInfoDto;
import com.openclassrooms.safetyAlertApi.dto.PersonInfosDto;
import com.openclassrooms.safetyAlertApi.dto.PersonLivingAtAnAddressDto;
import com.openclassrooms.safetyAlertApi.model.Firestation;
import com.openclassrooms.safetyAlertApi.model.Medicalrecord;
import com.openclassrooms.safetyAlertApi.model.Person;
import com.openclassrooms.safetyAlertApi.repository.FirestationRepository;
import com.openclassrooms.safetyAlertApi.repository.MedicalrecordRepository;
import com.openclassrooms.safetyAlertApi.repository.PersonRepository;
import com.sun.jmx.mbeanserver.Repository;

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
	
	public List<ChildDto> getChildListFromAnAddress(String address)
	{
		List<ChildDto> listOfChild = new ArrayList<>();
		List<Person> listOfPersonAtAnAddress = new ArrayList<>();
		List<HomeMembresDto> listOfHomeMembers = new ArrayList<>();
		listOfPersonAtAnAddress = personService.getPersonPerAddress(address);
		Medicalrecord medicalrecord = new Medicalrecord();
		Person person = new Person();
		HomeMembresDto homeMembresDto = new HomeMembresDto();
		
		int i = 0;
		long age = 0;

		for(i=0;i<listOfPersonAtAnAddress.size();i++)
		{ 
			person = listOfPersonAtAnAddress.get(i);
			homeMembresDto.setFirstname(person.getFirstname());
			homeMembresDto.setLastname(person.getLastname());
			listOfHomeMembers.add(homeMembresDto);

			medicalrecord = medicalrecordService.findMedicalrecordByFirstnameAndLastname(person.getFirstname(), person.getLastname() );
			//calcul de l'age
			age = getAge(medicalrecord.getBirthdate());
		
			if(age <= 18)
			{	//si oui ajouter son nom à la liste child
			//	homeMembresDto.setFirstname(person.getFirstname());
			//	homeMembresDto.setLastname(person.getLastname());
			//	listOfHomeMembers.add(homeMembresDto);

				
				ChildDto child = new ChildDto(person.getFirstname(),person.getLastname(), age, listOfHomeMembers);
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
	
	public List<PersonCoveredDto> getPersonCoveredByFirestation(String station)
	{
		List<PersonCoveredDto> personCovered = new ArrayList<>();
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
			PersonCoveredDto personCovereds = new PersonCoveredDto(personByAddress.get(i).getFirstname(),personByAddress.get(i).getLastname(),
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
	
	public List<PersonLivingAtAnAddressDto> getPersonLivingAtAnAddress(String address)
	{
		List<PersonLivingAtAnAddressDto> listOfPersonLivingAtAnAddress = new ArrayList<>();
		List<Person> listOfPerson = personService.getPersonPerAddress(address);
		Firestation firestation = firestationService.getFirestationByAddress(address);
		Medicalrecord medicalrecord = new Medicalrecord();
		int i = 0;
		for(i=0; i<listOfPerson.size();i++)
		{
			//PersonLivingAtAnAddress personAtAnAddress = new PersonLivingAtAnAddress();
			medicalrecord = medicalrecordService.findMedicalrecordByFirstnameAndLastname(listOfPerson.get(i).getFirstname(),listOfPerson.get(i).getLastname());
			PersonLivingAtAnAddressDto personAtAnAddress = new PersonLivingAtAnAddressDto(listOfPerson.get(i).getFirstname(),
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
	public PersonInfoDto getInformationAboutPerson(String firstname,String lastname)
	{ 
		PersonInfoDto personInfo = new PersonInfoDto();
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
	
	public List<FloodListDto> getListOfHomeDeservedByFirestation(String station)
	{
		 /*http://localhost:8080/flood/stations?stations=<a list of station_numbers>
		Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
		personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
		faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.*/
		//renvoyer les address par station
		//renvoyer les personne par address
		// trouver leur antecedant medicaux
		List<FloodListDto> floodList = new ArrayList<>();
		List<String> listOfFirestation = firestationService.getAddressByFirestation(station);
		List<Person> listOfPersonByAddress = personService.getPersonPerAddress(listOfFirestation);
		int i = 0 ;
		Medicalrecord medicalrecord = new Medicalrecord();
		for(i=0;i<listOfPersonByAddress.size();i++)
		{
			FloodListDto flood = new FloodListDto();
			medicalrecord = medicalrecordService.findMedicalrecordByFirstnameAndLastname(listOfPersonByAddress.get(i).getFirstname(), listOfPersonByAddress.get(i).getLastname());
			flood.setAddress(listOfPersonByAddress.get(i).getAddress());
			flood.setFirstname(listOfPersonByAddress.get(i).getFirstname());
			flood.setLastname(listOfPersonByAddress.get(i).getLastname());
			flood.setPhoneNumber(listOfPersonByAddress.get(i).getPhone());
			flood.setAge(getAge(medicalrecord.getBirthdate()));
			flood.setMedications(medicalrecord.getMedications());
			flood.setAllergies(medicalrecord.getAllergies());
			floodList.add(flood);
		}
		return floodList;
	}
}

