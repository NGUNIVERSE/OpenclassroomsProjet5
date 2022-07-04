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

import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.model.Email;
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
	public ResponseEntity<Person> getPerson(@PathVariable("id") final Long id) {
		
		return ResponseEntity.of(personService.getPerson(id));
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
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.savePerson(person));
	}
	
	/**
	 * Delete - Delete a person
	 * @param id - The id of the person to delete
	 */
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
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
	public ResponseEntity<Person> updatePerson(@PathVariable("id") final Long id, @RequestBody Person person) {
		
		return ResponseEntity.of(personService.updatePerson(id, person));
	}
	
	/************************************ URL ALERT *******************************/
	
    @GetMapping("/communityEmail")
    public List<Email> listOfEmailByCity(@RequestParam("city") String city) {
        return personService.getEmailPerCity(city);
    }
 

}