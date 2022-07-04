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
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    
 /*   @Autowired
    private MedicalrecordRepository medicalrecordRepository;
    
    @Autowired
    private FirestationRepository firestationRepository; */
    
    public Optional<Person> getPerson(final Long id) {
        return personRepository.findById(id);
    }

    public Iterable<Person> getPersons() {
        return personRepository.findAll();
    }

    public void deletePerson(final Long id) {
    	personRepository.deleteById(id);
    }

    public Person savePerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return savedPerson;
    }
    
	public Optional<Person> updatePerson(final Long id, Person person) {
		Optional<Person> e = personRepository.findById(id);
		if(e.isPresent()) {
			Person currentPerson = e.get();
			
			String firstname = person.getFirstname();
			if(firstname != null) {
				currentPerson.setFirstname(firstname);
			}
			String lastname = person.getLastname();
			if(lastname != null) {
				currentPerson.setLastname(lastname);
			}
			String address = person.getAddress();
			if(address != null) {
				currentPerson.setAddress(address);;
			}
			String city = person.getCity();
			if(city != null) {
				currentPerson.setCity(city);;
			}
			String zip = person.getZip();
			if(zip != null) {
				currentPerson.setZip(zip);;
			}
			String phone = person.getPhone();
			if(phone != null) {
				currentPerson.setPhone(phone);;
			}			
			String email = person.getEmail();
			if(email != null) {
				currentPerson.setEmail(email);
			}
			return Optional.of(personRepository.save(currentPerson));
		} else {
			return (Optional.empty());
		}
	}
	
	public List<Email> getEmailPerCity(String city)
	{
		List<Email> listOfEmail = new ArrayList<>();
        Iterable<Person> persons = personRepository.findPersonByCity(city);
        for (Person Person : persons) {
            Email email = new Email();
            email.setEmail(Person.getEmail());
            email.setCity(Person.getCity());
            listOfEmail.add(email);
        }
        return listOfEmail;	
	}
	
/*	  public List<PersonInfos> getlistPersonsByFirstnameAndLastname(String firstname, String lastname) {

	        List<PersonInfos> listPersonsInfo = new ArrayList<>();
	        List<String> listStations = new ArrayList<>();

	        List<Person> abstractPeople = personRepository.findPersonByFirstnameAndLastname(firstname, lastname);

	        for (Person Person : abstractPeople) {
	            Medicalrecord medicalRecord = medicalrecordRepository.findFirstMedicalrecordByPersonId(Person.getId());



	            List<Firestation> stations = firestationRepository.findFirestationByAddress(Person.getAddress());

	            if (medicalRecord == null) {
	              //  logger.error("Person " + Person + " don't exist");
	            }

	            int age = calculateAgeService.calculateAge(medicalRecord.getBirthdate());

	            PersonInfos personInfos = new PersonInfos();
	            personInfos.setFirstname(Person.getFirstname());
	            personInfos.setLastname(Person.getLastname());
	            personInfos.setAddress(Person.getAddress());
	            personInfos.setEmail(Person.getEmail());
	            personInfos.setPhone(Person.getPhone());
	            personInfos.setAge(age);

	            for (Firestation a : stations) {
	                if (!listStations.contains(String.valueOf(a.getStation()))) {
	                    listStations.add(String.valueOf(a.getStation()));
	                }
	            }

	            personInfos.setStation(listStations);
	            personInfos.setAllergies(medicalRecord.getAllergies());
	            personInfos.setMedications(medicalRecord.getMedications());
	            listPersonsInfo.add(personInfos);	           

	        }

	        return listPersonsInfo;
	    }*/

}