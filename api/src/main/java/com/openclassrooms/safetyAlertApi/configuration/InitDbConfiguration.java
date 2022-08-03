package com.openclassrooms.safetyAlertApi.configuration;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetyAlertApi.model.Firestation;
import com.openclassrooms.safetyAlertApi.model.Medicalrecord;
import com.openclassrooms.safetyAlertApi.model.Person;

import lombok.Data;


@Configuration
public class InitDbConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitDbConfiguration.class) ; 
	
	@Data
	private static class JSON{
		
		private List<Person> persons;
		private List<Firestation> firestations;
		private List<Medicalrecord> medicalrecords;
		
		
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void initDb() throws IOException
	{
		
	//	URL lien = new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json");
		
		File file = new File("src/main/resources/data.json");
		
		LOGGER.info("testInitDb");
		
		ObjectMapper mapper = new ObjectMapper();
		
		// JSON json = mapper.readValue(lien, JSON.class);
		JSON json = mapper.readValue(file , JSON.class);
		LOGGER.info(json.toString());
		
		
		//	TypeReference<List<Person>> typeReference = new TypeReference<List<User>>(){};		
		
		//InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
		
	}
	


}
