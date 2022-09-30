package com.openclassrooms.safetyAlertApi.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.openclassrooms.safetyAlertApi.model.Person;

@Service
public class PersonRepository {

	private Map<Long, Person> personById = new HashMap<>();

	public void deleteById(Long id) {

		personById.remove(id);
	}

	public Person save(Person person) {

		if (person.getId() == null) {
			Long newId = (long) personById.size();
			while (personById.containsKey(newId)) {
				newId++;
			}

			person.setId(newId);
		}

		personById.put(person.getId(), person);

		return person;
	}

	public Iterable<Person> findAll() {

		return new ArrayList<>(personById.values());

	}

	public Optional<Person> findById(Long id) {

		return Optional.ofNullable(personById.get(id));
	}

	public List<Person> saveAll(List<Person> persons) {

		if (!CollectionUtils.isEmpty(persons)) {
			persons.forEach(this::save);
		}

		return persons;
	}

	public List<Person> findPersonByCity(String city) {

		return personById.values().stream().filter(f -> f.getCity().equals(city)).collect(Collectors.toList());
	}

	public List<Person> findPersonByAddress(String address) {

		return personById.values().stream().filter(f -> f.getAddress().equals(address)).collect(Collectors.toList());
	}

	public Person findPersonByFirstnameAndLastname(String firstname, String lastname) {

		return personById.values().stream().filter(f -> f.getFirstname().equals(firstname))
				.filter(f -> f.getLastname().equals(lastname)).findFirst().orElse(null);
	}

}