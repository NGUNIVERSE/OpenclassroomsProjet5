package com.openclassrooms.api.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;


@Configuration
public class InitDbConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitDbConfiguration.class) ; 
	
	@EventListener(ApplicationReadyEvent.class)
	public void initDb()
	{
		LOGGER.info("testInitDb");
	}

}
