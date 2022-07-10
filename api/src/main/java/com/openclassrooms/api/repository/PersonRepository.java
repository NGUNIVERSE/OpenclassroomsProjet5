package com.openclassrooms.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.api.model.Person;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

//	List<Person> findPersonByFirstnameAndLastname(String firstname, String lastname);
	
	List<Person> findPersonByCity(String city);
	List<Person> findPersonByAddress(String address);
	//List<Person> findPersonByAddress(List<String> address);
	Person findPersonByFirstnameAndLastname(String firstname, String lastname);
	
}