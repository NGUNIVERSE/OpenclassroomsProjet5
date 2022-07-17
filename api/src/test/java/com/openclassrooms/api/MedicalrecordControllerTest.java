
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
import com.openclassrooms.api.model.Medicalrecord;
import com.openclassrooms.api.service.MedicalrecordService;

@WebMvcTest(controllers = MedicalrecordController.class)
public class MedicalrecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalrecordService medicalrecordServiceMock;

  /*  @MockBean
    private Medicalrecord medicalrecordMock; */
    
    @Test
    public void testGetMedicalrecord() throws Exception {
    	
    	Medicalrecord medicalrecordMock= new Medicalrecord();
    	Medicalrecord medicalrecordMock1= new Medicalrecord();
    	medicalrecordMock.setFirstname("Reginold");
    	medicalrecordMock.setLastname("Walker");
    	medicalrecordMock.setBirthdate("08/30/1979");
    	medicalrecordMock.setMedications("thradox:700mg");
    	medicalrecordMock.setAllergies("illisoxian");
    	
    	medicalrecordMock1.setFirstname("Ron");
    	medicalrecordMock1.setLastname("Peters");
    	medicalrecordMock1.setBirthdate("04/06/1965");
    	medicalrecordMock1.setMedications("");
    	medicalrecordMock1.setAllergies("");
    	
    	Optional<Medicalrecord> medicalrecordOptionalMock = Optional.ofNullable(medicalrecordMock);
    	Optional<Medicalrecord> medicalrecordOptionalMock1 = Optional.ofNullable(medicalrecordMock1);
    	long id1=0;
    	long id2=1;	
    	
    	when(medicalrecordServiceMock.getMedicalrecord(id1)).thenReturn(medicalrecordOptionalMock);
    	when(medicalrecordServiceMock.getMedicalrecord(id2)).thenReturn(medicalrecordOptionalMock1);
    	
    	mockMvc.perform(get("/medicalrecord/{id}",0)).andExpect(status().isOk())
          .andExpect(jsonPath("$..firstname").value("Reginold"))
          .andExpect(jsonPath("$..lastname").value("Walker"))
          .andExpect(jsonPath("$..birthdate").value("08/30/1979"))
          .andExpect(jsonPath("$..medications").value("thradox:700mg"))
    	  .andExpect(jsonPath("$..allergies").value("illisoxian"));
    	  
    	mockMvc.perform(get("/medicalrecord/{id}",1)).andExpect(status().isOk())
    	.andExpect(jsonPath("$..firstname").value("Ron"))
        .andExpect(jsonPath("$..lastname").value("Peters"))
        .andExpect(jsonPath("$..birthdate").value("04/06/1965"))
        .andExpect(jsonPath("$..medications").value(""))
  	  .andExpect(jsonPath("$..allergies").value(""));
    	
    	verify(medicalrecordServiceMock).getMedicalrecord(id1);
        verify(medicalrecordServiceMock).getMedicalrecord(id2);
    	
    }
    
    
    @Test
    public void testGetMedicalrecords() throws Exception {
    	Medicalrecord medicalrecordMock= new Medicalrecord();
    	Medicalrecord medicalrecordMock1= new Medicalrecord();
    	medicalrecordMock.setFirstname("Reginold");
    	medicalrecordMock.setLastname("Walker");
    	medicalrecordMock.setBirthdate("08/30/1979");
    	medicalrecordMock.setMedications("thradox:700mg");
    	medicalrecordMock.setAllergies("illisoxian");
    	
    	medicalrecordMock1.setFirstname("Ron");
    	medicalrecordMock1.setLastname("Peters");
    	medicalrecordMock1.setBirthdate("04/06/1965");
    	medicalrecordMock1.setMedications("");
    	medicalrecordMock1.setAllergies("");
    	
    	List<Medicalrecord> listOfMedicalrecord = new ArrayList<>();
    	listOfMedicalrecord.add(medicalrecordMock);
    	listOfMedicalrecord.add(medicalrecordMock1); 
    	Iterable<Medicalrecord> listOfMedicalrecordTest = listOfMedicalrecord;
    	
    	when(medicalrecordServiceMock.getMedicalrecords()).thenReturn(listOfMedicalrecordTest);
     
 	mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk())
    .andExpect(jsonPath("$[0].firstname", is("Reginold")))
    .andExpect(jsonPath("$[0].lastname", is("Walker")))
    .andExpect(jsonPath("$[0].birthdate", is("08/30/1979")))
    .andExpect(jsonPath("$[0].medications", is("thradox:700mg")))
	  .andExpect(jsonPath("$[0].allergies", is("illisoxian")))
	.andExpect(jsonPath("$[1].firstname", is("Ron")))
  .andExpect(jsonPath("$[1].lastname", is("Peters")))
  .andExpect(jsonPath("$[1].birthdate", is("04/06/1965")))
  .andExpect(jsonPath("$[1].medications", is("")))
  .andExpect(jsonPath("$[1].allergies", is("")));
     
     verify(medicalrecordServiceMock).getMedicalrecords();
     //assertThat
       //verify(firestationService).getFirestations();      
    //   mockMvc.perform(get("/firestations")).andExpect(status().isOk());         
    }
    
    @Test
    public void testDeleteMedicalrecord() throws Exception{
    
    	Medicalrecord medicalrecordMock= new Medicalrecord();
    	Medicalrecord medicalrecordMock1= new Medicalrecord();
    	medicalrecordMock.setFirstname("Reginold");
    	medicalrecordMock.setLastname("Walker");
    	medicalrecordMock.setBirthdate("08/30/1979");
    	medicalrecordMock.setMedications("thradox:700mg");
    	medicalrecordMock.setAllergies("illisoxian");
    	
    	medicalrecordMock1.setFirstname("Ron");
    	medicalrecordMock1.setLastname("Peters");
    	medicalrecordMock1.setBirthdate("04/06/1965");
    	medicalrecordMock1.setMedications("");
    	medicalrecordMock1.setAllergies("");
    	
    	List<Medicalrecord> listOfMedicalrecord = new ArrayList<>();
    	listOfMedicalrecord.add(medicalrecordMock);
    	listOfMedicalrecord.add(medicalrecordMock1); 
    	Iterable<Medicalrecord> listOfMedicalrecordTest = listOfMedicalrecord;
    	
    	long id1=0;
    	long id2=1;	
    	
    	when(medicalrecordServiceMock.getMedicalrecords()).thenReturn(listOfMedicalrecordTest);
    	
     	mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].firstname", is("Reginold")))
        .andExpect(jsonPath("$[0].lastname", is("Walker")))
        .andExpect(jsonPath("$[0].birthdate", is("08/30/1979")))
        .andExpect(jsonPath("$[0].medications", is("thradox:700mg")))
    	  .andExpect(jsonPath("$[0].allergies", is("illisoxian")))
    	.andExpect(jsonPath("$[1].firstname", is("Ron")))
      .andExpect(jsonPath("$[1].lastname", is("Peters")))
      .andExpect(jsonPath("$[1].birthdate", is("04/06/1965")))
      .andExpect(jsonPath("$[1].medications", is("")))
      .andExpect(jsonPath("$[1].allergies", is("")));
     	
     mockMvc.perform(delete("/medicalrecord/{id}",1)).andExpect(status().isNoContent());
     verify(medicalrecordServiceMock).deleteMedicalrecord(id2);
     mockMvc.perform(get("/medicalrecord/{id}",1)).andExpect(status().isNotFound());

    }
    
    @Test
    public void testCreateMedicalrecord() throws Exception{
    
    	Medicalrecord medicalrecordMock= new Medicalrecord();
    	Medicalrecord medicalrecordMock1= new Medicalrecord();
    	medicalrecordMock.setFirstname("Reginold");
    	medicalrecordMock.setLastname("Walker");
    	medicalrecordMock.setBirthdate("08/30/1979");
    	medicalrecordMock.setMedications("thradox:700mg");
    	medicalrecordMock.setAllergies("illisoxian");
    	
    	medicalrecordMock1.setFirstname("Ron");
    	medicalrecordMock1.setLastname("Peters");
    	medicalrecordMock1.setBirthdate("04/06/1965");
    	medicalrecordMock1.setMedications("");
    	medicalrecordMock1.setAllergies("");
    	
    	List<Medicalrecord> listOfMedicalrecord = new ArrayList<>();
    	listOfMedicalrecord.add(medicalrecordMock);
    //	listOfFirestation.add(firestationMock1); 
    	//Iterable<Firestation> listOfFirestationTest = listOfFirestation;
    	ObjectMapper mapper = new ObjectMapper();
     	String json =  mapper.writeValueAsString(medicalrecordMock1);
     	
    	when(medicalrecordServiceMock.getMedicalrecords()).thenReturn(listOfMedicalrecord);
    	when(medicalrecordServiceMock.saveMedicalrecord(medicalrecordMock1)).thenReturn(medicalrecordMock1);
    	
     	mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].firstname", is("Reginold")))
        .andExpect(jsonPath("$[0].lastname", is("Walker")))
        .andExpect(jsonPath("$[0].birthdate", is("08/30/1979")))
        .andExpect(jsonPath("$[0].medications", is("thradox:700mg")))
    	  .andExpect(jsonPath("$[0].allergies", is("illisoxian")));
     
     mockMvc.perform(post("/medicalrecord")
    		 .contentType(MediaType.APPLICATION_JSON)
    		 .accept(MediaType.APPLICATION_JSON)
    		 .content(json))
     		 .andExpect(status().isCreated())
         	.andExpect(jsonPath("$..firstname").value("Ron"))
            .andExpect(jsonPath("$..lastname").value("Peters"))
            .andExpect(jsonPath("$..birthdate").value("04/06/1965"))
            .andExpect(jsonPath("$..medications").value(""))
       	  .andExpect(jsonPath("$..allergies").value(""));
     
     verify(medicalrecordServiceMock).saveMedicalrecord(medicalrecordMock1);
     
 /*    mockMvc.perform(get("/firestations")).andExpect(status().isOk())
       .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
       .andExpect(jsonPath("$[0].station", is("3")))
    		   .andExpect(jsonPath("$[1].address", is("salut"))) // No result for path $[1][adress]
    	       .andExpect(jsonPath("$[1].station", is("77"))); */
     
   //  verify(firestationServiceMock).saveFirestation(firestationMock1);
    }

    @Test
    public void updateMedicalrecordTest() throws Exception
    {
    	Medicalrecord medicalrecordMock= new Medicalrecord();
    	Medicalrecord medicalrecordMock1= new Medicalrecord();
    	medicalrecordMock.setFirstname("Reginold");
    	medicalrecordMock.setLastname("Walker");
    	medicalrecordMock.setBirthdate("08/30/1979");
    	medicalrecordMock.setMedications("thradox:700mg");
    	medicalrecordMock.setAllergies("illisoxian");
    	
    	medicalrecordMock1.setFirstname("Ron");
    	medicalrecordMock1.setLastname("Peters");
    	medicalrecordMock1.setBirthdate("04/06/1965");
    	medicalrecordMock1.setMedications("");
    	medicalrecordMock1.setAllergies("");
    	
    	List<Medicalrecord> listOfMedicalrecord = new ArrayList<>();
    	listOfMedicalrecord.add(medicalrecordMock);
    	
    	Optional<Medicalrecord> medicalrecordOptionalMock1 = Optional.ofNullable(medicalrecordMock1);

    	long id1=0;
    	long id2=1;	

     	
    //	listOfFirestation.add(firestationMock1); 
    	//Iterable<Firestation> listOfFirestationTest = listOfFirestation;
    	ObjectMapper mapper = new ObjectMapper();
     	String json =  mapper.writeValueAsString(medicalrecordMock1);

    	when(medicalrecordServiceMock.getMedicalrecords()).thenReturn(listOfMedicalrecord);
    	//when(firestationServiceMock.saveFirestation(firestationMock1)).thenReturn(firestationMock1);
    	  when(medicalrecordServiceMock.updateMedicalrecord(id1,medicalrecordMock1)).thenReturn(medicalrecordOptionalMock1);
   
    		mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk())
            .andExpect(jsonPath("$[0].firstname", is("Reginold")))
            .andExpect(jsonPath("$[0].lastname", is("Walker")))
            .andExpect(jsonPath("$[0].birthdate", is("08/30/1979")))
            .andExpect(jsonPath("$[0].medications", is("thradox:700mg")))
        	  .andExpect(jsonPath("$[0].allergies", is("illisoxian")));
     
    		 mockMvc.perform(put("/medicalrecord/{id}",0)
    	    		 .contentType(MediaType.APPLICATION_JSON)
    	    		 .accept(MediaType.APPLICATION_JSON)
    	    		 .content(json))
    	     		 .andExpect(status().isOk())
    	         	.andExpect(jsonPath("$..firstname").value("Ron"))
    	            .andExpect(jsonPath("$..lastname").value("Peters"))
    	            .andExpect(jsonPath("$..birthdate").value("04/06/1965"))
    	            .andExpect(jsonPath("$..medications").value(""))
    	       	  .andExpect(jsonPath("$..allergies").value(""));
       	
       	  verify(medicalrecordServiceMock).updateMedicalrecord(id1,medicalrecordMock1);
       	  
   /*    	     mockMvc.perform(get("/firestations")).andExpect(status().isOk())
       	     .andExpect(jsonPath("$[0].address", is("salut")))                 // POURQUOI CA MARCHE PAS ????
       	     .andExpect(jsonPath("$[0].station", is("77"))); */
   
       	     
 /*    mockMvc.perform(get("/firestations")).andExpect(status().isOk())
       .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
       .andExpect(jsonPath("$[0].station", is("3")))
    		   .andExpect(jsonPath("$[1].address", is("salut"))) // No result for path $[1][adress]
    	       .andExpect(jsonPath("$[1].station", is("77"))); */
     
  //   verify(firestationServiceMock).saveFirestation(firestationMock1);

    }
}
