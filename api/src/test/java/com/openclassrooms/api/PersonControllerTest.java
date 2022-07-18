
package com.openclassrooms.api;

import static org.hamcrest.CoreMatchers.is;
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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.api.controller.MedicalrecordController;
import com.openclassrooms.api.controller.PersonController;
import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.service.PersonService;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personServiceMock;

  /*  @MockBean
    private Medicalrecord medicalrecordMock; */
    
    
    
    @Test
    public void testGetPerson() throws Exception {
    	
    	Person personMock = mockPerson();
    	
    	Person personMock1 = mockPerson1();
    	
    	Optional<Person> personOptionalMock = Optional.ofNullable(personMock);
    	Optional<Person> personOptionalMock1 = Optional.ofNullable(personMock1);
    	long id1=0;
    	long id2=1;	
    	
    	when(personServiceMock.getPerson(id1)).thenReturn(personOptionalMock);
    	when(personServiceMock.getPerson(id2)).thenReturn(personOptionalMock1);
    	
    	mockMvc.perform(get("/person/{id}",0)).andExpect(status().isOk())
          .andExpect(jsonPath("$.firstname").value("Reginold"))
          .andExpect(jsonPath("$.lastname").value("Walker"))
          .andExpect(jsonPath("$.address").value("908 73rd St"))
          .andExpect(jsonPath("$.city").value("Culver"))
    	  .andExpect(jsonPath("$.zip").value("97451"))
          .andExpect(jsonPath("$.phone").value("841-874-8547"))
    	  .andExpect(jsonPath("$.email").value("reg@email.com"));
    	  
    	mockMvc.perform(get("/person/{id}",1)).andExpect(status().isOk())
        .andExpect(jsonPath("$.firstname").value("Ron"))
        .andExpect(jsonPath("$.lastname").value("Peters"))
        .andExpect(jsonPath("$.address").value("112 Steppes Pl"))
        .andExpect(jsonPath("$.city").value("Culver"))
  	  .andExpect(jsonPath("$.zip").value("97451"))
        .andExpect(jsonPath("$.phone").value("841-874-8888"))
  	  .andExpect(jsonPath("$.email").value("jpeter@email.com"));
    	
    	verify(personServiceMock).getPerson(id1);
        verify(personServiceMock).getPerson(id2);
    	
    }
    
    
    @Test
    public void testPersons() throws Exception {
    	Person personMock = mockPerson();
    	
    	Person personMock1 = mockPerson1();
    	
    	List<Person> listOfPerson = new ArrayList<>();
    	listOfPerson.add(personMock);
    	listOfPerson.add(personMock1); 
    	Iterable<Person> listOfPersonTest = listOfPerson;
    	
    	when(personServiceMock.getPersons()).thenReturn(listOfPersonTest);
     
 	mockMvc.perform(get("/persons")).andExpect(status().isOk())
    .andExpect(jsonPath("$[0].firstname", is("Reginold")))
    .andExpect(jsonPath("$[0].lastname", is("Walker")))
    .andExpect(jsonPath("$[0].address", is("908 73rd St")))
    .andExpect(jsonPath("$[0].city", is("Culver")))
	  .andExpect(jsonPath("$[0].zip", is("97451")))
    .andExpect(jsonPath("$[0].phone", is("841-874-8547")))
	  .andExpect(jsonPath("$[0].email", is("reg@email.com")))
	.andExpect(jsonPath("$[1].firstname", is("Ron")))
  .andExpect(jsonPath("$[1].lastname", is("Peters")))
  .andExpect(jsonPath("$[1].address", is("112 Steppes Pl")))
  .andExpect(jsonPath("$[1].city", is("Culver")))
  .andExpect(jsonPath("$[1].zip", is("97451")))
  .andExpect(jsonPath("$[1].phone", is("841-874-8888")))
  .andExpect(jsonPath("$[1].email", is("jpeter@email.com")));
     
     verify(personServiceMock).getPersons();
     //assertThat
       //verify(firestationService).getFirestations();      
    //   mockMvc.perform(get("/firestations")).andExpect(status().isOk());         
    }
    
    @Test
    public void testDeletePerson() throws Exception{

    	long id2=1;	
    	

     mockMvc.perform(delete("/person/{id}",1)).andExpect(status().isNoContent());
     verify(personServiceMock).deletePerson(id2);


    }
    
    @Test 
    public void testGetPersonUnknownId() throws Exception{
    	
    	long id3=50;	
    	

     mockMvc.perform(get("/person/{id}",id3)).andExpect(status().isNotFound());
     verify(personServiceMock).getPerson(id3);
    }
    
    
    @Test
    public void testCreatePerson() throws Exception{
    
    	Person personMock = mockPerson();
    	
    	Person personMock1 = mockPerson1();
    	
    	List<Person> listOfPerson = new ArrayList<>();
    	listOfPerson.add(personMock);
    //	listOfFirestation.add(firestationMock1); 
    	//Iterable<Firestation> listOfFirestationTest = listOfFirestation;
    	ObjectMapper mapper = new ObjectMapper();
     	String json =  mapper.writeValueAsString(personMock1);
     	
    	when(personServiceMock.savePerson(personMock1)).thenReturn(personMock1);
     
     mockMvc.perform(post("/person")
    		 .contentType(MediaType.APPLICATION_JSON)
    		 .accept(MediaType.APPLICATION_JSON)
    		 .content(json))
     		 .andExpect(status().isCreated())
             .andExpect(jsonPath("$.firstname").value("Ron"))
             .andExpect(jsonPath("$.lastname").value("Peters"))
             .andExpect(jsonPath("$.address").value("112 Steppes Pl"))
             .andExpect(jsonPath("$.city").value("Culver"))
       	  .andExpect(jsonPath("$.zip").value("97451"))
             .andExpect(jsonPath("$.phone").value("841-874-8888"))
       	  .andExpect(jsonPath("$.email").value("jpeter@email.com"));
     
     verify(personServiceMock).savePerson(personMock1);
     
    }


	private Person mockPerson1() {
		Person personMock1= new Person();
    	personMock1.setFirstname("Ron");
      	personMock1.setLastname("Peters");
      	personMock1.setAddress("112 Steppes Pl");
      	personMock1.setCity("Culver");
      	personMock1.setZip("97451");
      	personMock1.setPhone("841-874-8888");
      	personMock1.setEmail("jpeter@email.com");
		return personMock1;
	}

    @Test
    public void updatePersonTest() throws Exception
    {
    	
    	Person personMock1 = mockPerson1();
    
      	
    	Optional<Person> personOptionalMock = Optional.ofNullable(personMock1);
    	
    	long id1=0;
    	long id2=1;	
     	
    //	listOfFirestation.add(firestationMock1); 
    	//Iterable<Firestation> listOfFirestationTest = listOfFirestation;
    	ObjectMapper mapper = new ObjectMapper();
     	String json =  mapper.writeValueAsString(personMock1);

    
    	//when(firestationServiceMock.saveFirestation(firestationMock1)).thenReturn(firestationMock1);
    	  when(personServiceMock.updatePerson(id1,personMock1)).thenReturn(personOptionalMock);
   
     
    		 mockMvc.perform(put("/person/{id}",0)
    	    		 .contentType(MediaType.APPLICATION_JSON)
    	    		 .accept(MediaType.APPLICATION_JSON)
    	    		 .content(json))
    	     		 .andExpect(status().isOk())
    	     		 .andExpect(jsonPath("$.firstname").value("Ron"))
    	             .andExpect(jsonPath("$.lastname").value("Peters"))
    	             .andExpect(jsonPath("$.address").value("112 Steppes Pl"))
    	             .andExpect(jsonPath("$.city").value("Culver"))
    	       	  .andExpect(jsonPath("$.zip").value("97451"))
    	             .andExpect(jsonPath("$.phone").value("841-874-8888"))
    	       	  .andExpect(jsonPath("$.email").value("jpeter@email.com"));
       	
       	  verify(personServiceMock).updatePerson(id1,personMock1);
       	  

    }
    
    @Test
    public void updatePersonNotFoundTest() throws Exception
    {
    	
    	Person personMock = mockPerson();
    
      	long id1 = 0 ;
    
    //	listOfFirestation.add(firestationMock1); 
    	//Iterable<Firestation> listOfFirestationTest = listOfFirestation;
    	ObjectMapper mapper = new ObjectMapper();
     	String json =  mapper.writeValueAsString(personMock);

    
    	//when(firestationServiceMock.saveFirestation(firestationMock1)).thenReturn(firestationMock1);
     
    		 mockMvc.perform(put("/person/{id}",0)
    	    		 .contentType(MediaType.APPLICATION_JSON)
    	    		 .accept(MediaType.APPLICATION_JSON)
    	    		 .content(json))
    	     		 .andExpect(status().isNotFound());

       	
       	  verify(personServiceMock).updatePerson(0L,personMock);  // caster un entier en Long 0L
       	  

    }


	private Person mockPerson() {
		Person personMock= new Person();

		personMock.setFirstname("Reginold");
    	personMock.setLastname("Walker");
    	personMock.setAddress("908 73rd St");
    	personMock.setCity("Culver");
    	personMock.setZip("97451");
    	personMock.setPhone("841-874-8547");
    	personMock.setEmail("reg@email.com");
		return personMock;
	}
}