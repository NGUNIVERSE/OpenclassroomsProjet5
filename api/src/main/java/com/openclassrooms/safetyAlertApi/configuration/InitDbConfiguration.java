package com.openclassrooms.safetyAlertApi.configuration;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyAlertApi.model.Firestation;
import com.openclassrooms.safetyAlertApi.model.Medicalrecord;
import com.openclassrooms.safetyAlertApi.model.Person;
import com.openclassrooms.safetyAlertApi.repository.FirestationRepository;
import com.openclassrooms.safetyAlertApi.repository.MedicalrecordRepository;
import com.openclassrooms.safetyAlertApi.repository.PersonRepository;

import lombok.Data;

@Configuration
public class InitDbConfiguration {

	//private static final Logger LOGGER = LoggerFactory.getLogger(InitDbConfiguration.class);

	@Data
	private static class JSON {

		private List<Person> persons;
		private List<Firestation> firestations;
		private List<Medicalrecord> medicalrecords;

	}

	@Autowired
	private FirestationRepository firestationRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private MedicalrecordRepository medicalrecordRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void initDb() throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		JSON json = mapper.readValue(getClass().getClassLoader().getResourceAsStream("data.json"), JSON.class);

		personRepository.saveAll(json.getPersons());
		medicalrecordRepository.saveAll(json.getMedicalrecords());
		firestationRepository.saveAll(json.getFirestations());

	}

}
