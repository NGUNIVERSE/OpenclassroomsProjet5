package com.openclassrooms.safetyAlertApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyAlertApi.dto.EmailDto;
import com.openclassrooms.safetyAlertApi.model.Person;
import com.openclassrooms.safetyAlertApi.repository.PersonRepository;

import lombok.Data;

@Data
@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

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
		if (e.isPresent()) {
			Person currentPerson = e.get();

			String firstname = person.getFirstname();
			if (firstname != null) {
				currentPerson.setFirstname(firstname);
			}
			String lastname = person.getLastname();
			if (lastname != null) {
				currentPerson.setLastname(lastname);
			}
			String address = person.getAddress();
			if (address != null) {
				currentPerson.setAddress(address);
				;
			}
			String city = person.getCity();
			if (city != null) {
				currentPerson.setCity(city);
				;
			}
			String zip = person.getZip();
			if (zip != null) {
				currentPerson.setZip(zip);
				;
			}
			String phone = person.getPhone();
			if (phone != null) {
				currentPerson.setPhone(phone);
				;
			}
			String email = person.getEmail();
			if (email != null) {
				currentPerson.setEmail(email);
			}
			return Optional.of(personRepository.save(currentPerson));
		} else {
			return (Optional.empty());
		}
	}

	public List<EmailDto> getEmailPerCity(String city) {
		List<EmailDto> listOfEmail = new ArrayList<>();
		Iterable<Person> persons = personRepository.findPersonByCity(city);
		for (Person Person : persons) {
			EmailDto email = new EmailDto();
			email.setEmail(Person.getEmail());
			email.setCity(Person.getCity());
			listOfEmail.add(email);
		}
		return listOfEmail;
	}

	public List<Person> getPersonPerAddress(String address) {
		List<Person> listOfPerson = new ArrayList<>();
		Iterable<Person> persons = personRepository.findPersonByAddress(address);
		for (Person Person : persons) {
			Person person = new Person();
			person.setId(Person.getId());
			person.setFirstname(Person.getFirstname());
			person.setLastname(Person.getLastname());
			person.setAddress(Person.getAddress());
			person.setCity(Person.getCity());
			person.setZip(Person.getZip());
			person.setPhone(Person.getPhone());
			person.setEmail(Person.getEmail());
			listOfPerson.add(person);
		}
		return listOfPerson;
	}

	public List<Person> getPersonPerAddress(List<String> address) {
		List<Person> listOfPerson = new ArrayList<>();
		List<String> addresses = address;
		int i = 0;
		for (i = 0; i < addresses.size(); i++) {

			Iterable<Person> persons = personRepository.findPersonByAddress(addresses.get(i));
			for (Person Person : persons) {
				Person person = new Person();
				person.setId(Person.getId());
				person.setFirstname(Person.getFirstname());
				person.setLastname(Person.getLastname());
				person.setAddress(Person.getAddress());
				person.setCity(Person.getCity());
				person.setZip(Person.getZip());
				person.setPhone(Person.getPhone());
				person.setEmail(Person.getEmail());
				listOfPerson.add(person);
			}
		}
		return listOfPerson;
	}

	public List<String> getPhoneNumberOfPerson(List<Person> listOfPerson) {
		List<String> listOfPhoneNumber = new ArrayList<>();
		Iterable<Person> listOfPersons = listOfPerson;
		for (Person Person : listOfPersons) {

			listOfPhoneNumber.add(Person.getPhone());
		}

		return listOfPhoneNumber;
	}

	public Person getPersonByFirstnameAndLastname(String firstname, String lastname) {
		return personRepository.findPersonByFirstnameAndLastname(firstname, lastname);
	}

}