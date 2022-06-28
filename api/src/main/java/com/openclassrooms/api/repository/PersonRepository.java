package com.openclassrooms.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.api.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}