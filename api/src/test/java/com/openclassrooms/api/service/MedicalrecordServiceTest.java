package com.openclassrooms.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.api.model.Medicalrecord;
import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.repository.MedicalrecordRepository;

@SpringBootTest
public class MedicalrecordServiceTest {

	
	@Mock
	private MedicalrecordRepository medicalrecordRepositoryMock;
	
	@InjectMocks
	private MedicalrecordService medicalrecordService;
	
private Medicalrecord mockMedicalrecord() {
    	
    	Medicalrecord medicalrecordMock = new Medicalrecord();
    	medicalrecordMock.setFirstname("Reginold");
    	medicalrecordMock.setLastname("Walker");
    	medicalrecordMock.setBirthdate("08/30/1979");
    	medicalrecordMock.setMedications("thradox:700mg");
    	medicalrecordMock.setAllergies("illisoxian");
    	return medicalrecordMock;
    }
    private Medicalrecord mockMedicalrecord1() {
    	
    	Medicalrecord medicalrecordMock1 = new Medicalrecord();
    	medicalrecordMock1.setFirstname("Ron");
    	medicalrecordMock1.setLastname("Peters");
    	medicalrecordMock1.setBirthdate("04/06/1965");
    	medicalrecordMock1.setMedications("");
    	medicalrecordMock1.setAllergies("");
    	return medicalrecordMock1;
    }
    
	private long id1=0;
	private long id2=1;	
	private long id3=50;
	
	
  @Test
    public void testGetMedicalrecord() throws Exception {
    	
    	Medicalrecord medicalrecordMock= mockMedicalrecord();
    	Medicalrecord medicalrecordMock1= mockMedicalrecord1();
	
    	Optional<Medicalrecord> medicalrecordOptionalMock = Optional.ofNullable(medicalrecordMock);
    	Optional<Medicalrecord> medicalrecordOptionalMock1 = Optional.ofNullable(medicalrecordMock1);
    	
    	when(medicalrecordRepositoryMock.findById(id1)).thenReturn(medicalrecordOptionalMock);
    	when(medicalrecordRepositoryMock.findById(id2)).thenReturn(medicalrecordOptionalMock1);
    	
    	Optional<Medicalrecord> result = medicalrecordService.getMedicalrecord(id1);
    	Optional<Medicalrecord> result1 = medicalrecordService.getMedicalrecord(id2);
    	
    	assertThat(result).isEqualTo(medicalrecordOptionalMock);
    	assertThat(result1).isEqualTo(medicalrecordOptionalMock1);
    	
    	verify(medicalrecordRepositoryMock).findById(id1);
        verify(medicalrecordRepositoryMock).findById(id2);
    	
    }
    
	  
    @Test
    public void testGetMedicalrecords() throws Exception {
    	
    	Medicalrecord medicalrecordMock= mockMedicalrecord();
    	Medicalrecord medicalrecordMock1= mockMedicalrecord1();
    	
    	List<Medicalrecord> listOfMedicalrecord = new ArrayList<>();
    	listOfMedicalrecord.add(medicalrecordMock);
    	listOfMedicalrecord.add(medicalrecordMock1); 
    	Iterable<Medicalrecord> listOfMedicalrecordTest = listOfMedicalrecord;
    	
    	when(medicalrecordRepositoryMock.findAll()).thenReturn(listOfMedicalrecordTest);

    	Iterable<Medicalrecord> result = medicalrecordService.getMedicalrecords();
    	
    	assertThat(result).isEqualTo(listOfMedicalrecordTest);
    	verify(medicalrecordRepositoryMock).findAll();
        
    }
  

    @Test
    public void testDeleteMedicalrecord() throws Exception{
         	
    	medicalrecordService.deleteMedicalrecord(id1);
     
     verify(medicalrecordRepositoryMock).deleteById(id1);

    }
    
    @Test
    public void testGetMedicalrecordUnknownId() throws Exception{
    	

    	medicalrecordService.deleteMedicalrecord(id3);
        
     verify(medicalrecordRepositoryMock).deleteById(id3);
	
	}
  
    @Test
    public void testSaveMedicalrecord() throws Exception{
    
    	Medicalrecord medicalrecordMock= mockMedicalrecord();
    
     	when(medicalrecordRepositoryMock.save(medicalrecordMock)).thenReturn(medicalrecordMock);
    	
     	Medicalrecord result = medicalrecordService.saveMedicalrecord(medicalrecordMock);
     	
     	verify(medicalrecordRepositoryMock).save(medicalrecordMock);
     	assertThat(result).isEqualTo(medicalrecordMock);
    }
	
    @Test
    public void updateMedicalrecordTest() throws Exception
    {

    	Medicalrecord medicalrecordMock= mockMedicalrecord();
    	
    	Optional<Medicalrecord> medicalrecordOptionalMock = Optional.ofNullable(medicalrecordMock);

    	  when(medicalrecordRepositoryMock.findById(id1)).thenReturn(medicalrecordOptionalMock);
    	  when(medicalrecordRepositoryMock.save(medicalrecordMock)).thenReturn(medicalrecordMock);

    	  Optional<Medicalrecord> result = medicalrecordService.updateMedicalrecord(id1, medicalrecordMock);
    	  
       	  verify(medicalrecordRepositoryMock).findById(id1);
       	  verify(medicalrecordRepositoryMock).save(medicalrecordMock);
       	  assertThat(result).isEqualTo(medicalrecordOptionalMock);
    }
  
    @Test
    public void updateMedicalrecordNotFoundTest() throws Exception
    {

    	Medicalrecord medicalrecordMock= mockMedicalrecord();
    	
    	Optional<Medicalrecord> medicalrecordOptionalMock = Optional.ofNullable(medicalrecordMock);
    	
    	when(medicalrecordRepositoryMock.findById(id3)).thenReturn(Optional.empty());

    	Optional<Medicalrecord> result = medicalrecordService.updateMedicalrecord(id3, medicalrecordMock);
    	
       	verify(medicalrecordRepositoryMock).findById(id3);
       	verify(medicalrecordRepositoryMock, times(0)).save(medicalrecordMock);
       	assertThat(result).isEqualTo(Optional.empty());
    }
    
    @Test
    public void findMedicalrecordByFirstnameAndLastnameTest()
	{
    	Medicalrecord medicalrecordMock= mockMedicalrecord();
		String firstname = "Reginold";
		String lastname  = "Walker";
		
		when(medicalrecordRepositoryMock.findMedicalrecordByFirstnameAndLastname(firstname, lastname)).thenReturn(medicalrecordMock);
		
		Medicalrecord result = medicalrecordService.findMedicalrecordByFirstnameAndLastname(firstname, lastname);
		
		verify(medicalrecordRepositoryMock).findMedicalrecordByFirstnameAndLastname(firstname, lastname);
		assertThat(result).isEqualTo(medicalrecordMock);
	}
	
}
