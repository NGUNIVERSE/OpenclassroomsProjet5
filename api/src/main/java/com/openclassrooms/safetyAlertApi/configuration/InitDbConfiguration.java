package com.openclassrooms.safetyAlertApi.configuration;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyAlertApi.model.Firestation;
import com.openclassrooms.safetyAlertApi.model.MedicalRecord;
import com.openclassrooms.safetyAlertApi.model.Person;
import com.openclassrooms.safetyAlertApi.repository.FirestationRepository;
import com.openclassrooms.safetyAlertApi.repository.MedicalRecordRepository;
import com.openclassrooms.safetyAlertApi.repository.PersonRepository;

import lombok.Data;

@Configuration
public class InitDbConfiguration {

	@Data
	private static class JSON {

		private List<Person> persons;
		private List<Firestation> firestations;
		@JsonProperty("medicalrecords")
		private List<MedicalRecord> medicalRecords;

	}

	@Autowired
	private FirestationRepository firestationRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void initDb() throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		JSON json = mapper.readValue(getClass().getClassLoader().getResourceAsStream("data.json"), JSON.class);

		personRepository.saveAll(json.getPersons());
		medicalRecordRepository.saveAll(json.getMedicalRecords());
		firestationRepository.saveAll(json.getFirestations());

	}

}
