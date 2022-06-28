package com.openclassrooms.api.controller;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.api.model.Person;
//import com.openclassrooms.api.repository.PersonRepository;
import com.openclassrooms.api.service.PersonService;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

	/**
	 * Read - Get one person 
	 * @param id The id of the person
	 * @return A Person object full filled
	 */
	@GetMapping("/person/{id}")
	public Person getPerson(@PathVariable("id") final Long id) {
		Optional<Person> person = personService.getPerson(id);
		if(person.isPresent()) {
			return person.get();
		} else {
			return null;
		}
	}
    /**
    * Read - Get all persons
    * @return - An Iterable object of Person full filled
    */
    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return personService.getPersons();
    }
    
	/**
	 * Create - Add a new person
	 * @param person An object person
	 * @return The person object saved
	 */
	@PostMapping("/person")
	public Person createPerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}
	
	/**
	 * Delete - Delete a person
	 * @param id - The id of the person to delete
	 */
	@DeleteMapping("/person/{id}")
	public void deletePerson(@PathVariable("id") final Long id) {
		personService.deletePerson(id);
	}
	
	/**
	 * Update - Update an existing person
	 * @param id - The id of the person to update
	 * @param person - The person object updated
	 * @return
	 */
	@PutMapping("/person/{id}")
	public Person updatePerson(@PathVariable("id") final Long id, @RequestBody Person person) {
		Optional<Person> e = personService.getPerson(id);
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
			personService.savePerson(currentPerson);
			return currentPerson;
		} else {
			return null;
		}
	}
	
 

}