package com.openclassrooms.safetyAlertApi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetyAlertApi.model.Firestation;
import com.openclassrooms.safetyAlertApi.model.Person;
import com.openclassrooms.safetyAlertApi.repository.FirestationRepository;
import com.openclassrooms.safetyAlertApi.service.FirestationService;



public class FirestationServiceTest {
	
	@Mock
	private FirestationRepository firestationRepositoryMock;
	
	@InjectMocks
	private FirestationService firestationService;
	
	 @BeforeEach
	    public void setup()
	    {
	    	MockitoAnnotations.openMocks(this);
	    }

	 private Firestation mockFirestation() {
	    	Firestation firestationMock = new Firestation();
	    	firestationMock.setAddress("1509 Culver St");
	    	firestationMock.setStation("3");
	    	return firestationMock;
	    }
	    private Firestation mockFirestation1() {
	    	Firestation firestationMock1 = new Firestation();
	    	firestationMock1.setAddress("salut");
	    	firestationMock1.setStation("77");
	    	return firestationMock1;
	    }
	    
	    private long id1=0;
		private long id2=1;
		private long id3=50;
	    	
	
	
	@Test
	public void testGetFirestation() throws Exception {
	
		Firestation firestationMock = mockFirestation();
    	Firestation firestationMock1 = mockFirestation1();
    	
    	Optional<Firestation> firestationOptionalMock = Optional.ofNullable(firestationMock);
    	Optional<Firestation> firestationOptionalMock1 = Optional.ofNullable(firestationMock1);
    
    	
    	when(firestationRepositoryMock.findById(id1)).thenReturn(firestationOptionalMock);
    	when(firestationRepositoryMock.findById(id2)).thenReturn(firestationOptionalMock1);
    	

    	Optional<Firestation> result = firestationService.getFirestation(id1);
    	Optional<Firestation> result1 = firestationService.getFirestation(id2);
    	
    	
    	assertThat(result).isEqualTo(firestationOptionalMock);
    	assertThat(result1).isEqualTo(firestationOptionalMock1);
    	
    	verify(firestationRepositoryMock).findById(id1);
        verify(firestationRepositoryMock).findById(id2);
    	
	}
	
	 @Test
	    public void testGetFirestations() throws Exception {
	   
			Firestation firestationMock = mockFirestation();
	    	Firestation firestationMock1 = mockFirestation1();
	    	
	    	List<Firestation> listOfFirestation = new ArrayList<>();
	    	listOfFirestation.add(firestationMock);
	    	listOfFirestation.add(firestationMock1); 
	    	Iterable<Firestation> listOfFirestationTest = listOfFirestation;
	    
	    	when(firestationRepositoryMock.findAll()).thenReturn(listOfFirestationTest);
	    	
	    	Iterable<Firestation> result = firestationService.getFirestations();
	     
	    	assertThat(result).isEqualTo(listOfFirestationTest);
	    	verify(firestationRepositoryMock).findAll();
	        
	    }
	  
	  @Test
    public void testDeleteFirestation() throws Exception{
    
		  firestationService.deleteFirestation(id1);
		  
		  verify(firestationRepositoryMock).deleteById(id1);

    }
	 	  
	       @Test 
    public void testGetFirestationUnknownId() throws Exception{
    	
	    	   firestationService.deleteFirestation(id3);
			  
			  verify(firestationRepositoryMock).deleteById(id3);
    }
    
	       
	@Test
    public void testSaveFirestation() throws Exception{
    
		Firestation firestationMock = mockFirestation();
     	
    	when(firestationRepositoryMock.save(firestationMock)).thenReturn(firestationMock);
    
    	Firestation result = firestationService.saveFirestation(firestationMock);
     
    	verify(firestationRepositoryMock).save(firestationMock);
    	assertThat(result).isEqualTo(firestationMock);
     
    }
  
	@Test
    public void updateFirestationTest() throws Exception
    {
    	
		Firestation firestationMock = mockFirestation();
      	
		Optional<Firestation> firestationOptionalMock = Optional.ofNullable(firestationMock);
    	
  	  when(firestationRepositoryMock.findById(id1)).thenReturn(firestationOptionalMock);
  	  when(firestationRepositoryMock.save(firestationMock)).thenReturn(firestationMock);
    	
  	  Optional<Firestation> result = firestationService.updateFirestation(id1, firestationMock);

    	verify(firestationRepositoryMock).findById(id1);
    	verify(firestationRepositoryMock).save(firestationMock);
    	assertThat(result).isEqualTo(firestationOptionalMock);

    }
	
	@Test
    public void updateFirestationNotFoundTest() throws Exception
    {
    	
		Firestation firestationMock = mockFirestation();
      	
		Optional<Firestation> firestationOptionalMock = Optional.ofNullable(firestationMock);
    	
  	  when(firestationRepositoryMock.findById(id3)).thenReturn(Optional.empty());
    	
  	Optional<Firestation> result = firestationService.updateFirestation(id3, firestationMock);

    	verify(firestationRepositoryMock).findById(id3);
    	verify(firestationRepositoryMock, times(0)).save(firestationMock);
    	assertThat(result).isEqualTo(Optional.empty());

    }
	
	@Test
  public void getAddressByFirestationTest() throws Exception{
		
		
		String stationMock = "1";
		
		String addressMock = "1509 Culver St";
		String addressMock1 = "salut";
		
		List<String> listAddress = new ArrayList<>();
		listAddress.add(addressMock);
		listAddress.add(addressMock1);
		
		Firestation firestationMock = mockFirestation();
    	Firestation firestationMock1 = mockFirestation1();
    	
    	List<Firestation> listOfFirestation = new ArrayList<>();
    	listOfFirestation.add(firestationMock);
    	listOfFirestation.add(firestationMock1); 
    	
    	when(firestationRepositoryMock.findByStation(stationMock)).thenReturn(listOfFirestation);
    	
		List<String> listOfAddressByFirestationNumber = firestationService.getAddressByFirestation(stationMock);

		
		verify(firestationRepositoryMock).findByStation(stationMock);
		assertThat(listOfAddressByFirestationNumber).isEqualTo(listAddress);


	}

	@Test
	public void getFirestationByAddressTest()
	{		
		Firestation firestationMock = mockFirestation();
		String addressMock = "1509 Culver St";
		
		when(firestationRepositoryMock.findFirestationByAddress(addressMock)).thenReturn(firestationMock);
		
		Firestation result = firestationService.getFirestationByAddress(addressMock);
		
		verify(firestationRepositoryMock).findFirestationByAddress(addressMock);
		assertThat(result).isEqualTo(firestationMock);

	}

}
