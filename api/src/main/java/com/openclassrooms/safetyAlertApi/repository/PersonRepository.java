package com.openclassrooms.safetyAlertApi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetyAlertApi.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findPersonByCity(String city);

	List<Person> findPersonByAddress(String address);

	Person findPersonByFirstnameAndLastname(String firstname, String lastname);

}