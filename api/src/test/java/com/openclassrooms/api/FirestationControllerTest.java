/*package com.openclassrooms.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc; 

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc; */
   /* @Autowired
    private MockBean */
 /*   @Test
    public void testGetFirestations() throws Exception {
        mockMvc.perform(get("/firestations"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
            .andExpect(jsonPath("$[0].station",is("3")));
    }

} */
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
import com.openclassrooms.api.controller.FirestationController;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.service.FirestationService;

@WebMvcTest(controllers = FirestationController.class)
public class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationService firestationServiceMock;
    
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
    
    	
    	when(firestationServiceMock.getFirestation(id1)).thenReturn(firestationOptionalMock);
    	when(firestationServiceMock.getFirestation(id2)).thenReturn(firestationOptionalMock1);
    	
    	mockMvc.perform(get("/firestation/{id}",id1)).andExpect(status().isOk())
          .andExpect(jsonPath("$.address").value("1509 Culver St"))
          .andExpect(jsonPath("$.station").value("3"));
    	
    	mockMvc.perform(get("/firestation/{id}",id2)).andExpect(status().isOk())
       		   .andExpect(jsonPath("$.address").value("salut"))
       	       .andExpect(jsonPath("$.station").value("77"));
    	
    	verify(firestationServiceMock).getFirestation(id1);
        verify(firestationServiceMock).getFirestation(id2);
    	
    }
    
    
    @Test
    public void testGetFirestations() throws Exception {

    	
    	Firestation firestationMock = mockFirestation();
    	Firestation firestationMock1 = mockFirestation1();
    	
    	List<Firestation> listOfFirestation = new ArrayList<>();
    	listOfFirestation.add(firestationMock);
    	listOfFirestation.add(firestationMock1); 
    	Iterable<Firestation> listOfFirestationTest = listOfFirestation;
    	
    	when(firestationServiceMock.getFirestations()).thenReturn(listOfFirestationTest);
    	
     mockMvc.perform(get("/firestations")).andExpect(status().isOk())
       .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
       .andExpect(jsonPath("$[0].station", is("3")))
       .andExpect(jsonPath("$[1].address", is("salut")))
       .andExpect(jsonPath("$[1].station", is("77")));
     
     	verify(firestationServiceMock).getFirestations();
        
    }
    
    @Test
    public void testDeleteFirestation() throws Exception{
        
     mockMvc.perform(delete("/firestation/{id}",id2)).andExpect(status().isNoContent());
     verify(firestationServiceMock).deleteFirestation(id2);

    }
    
    @Test
    public void testGetFirestationUnknownId() throws Exception{
    	
    mockMvc.perform(get("/firestation/{id}",id3)).andExpect(status().isNotFound());
    verify(firestationServiceMock).getFirestation(id3);
    
    }
    
    @Test
    public void testCreateFirestation() throws Exception{

    	
    	Firestation firestationMock = mockFirestation();
    	Firestation firestationMock1 = mockFirestation1();
    	
    	List<Firestation> listOfFirestation = new ArrayList<>();
     	listOfFirestation.add(firestationMock);

    	ObjectMapper mapper = new ObjectMapper();
     	String json =  mapper.writeValueAsString(firestationMock1);
     	
 
    	when(firestationServiceMock.saveFirestation(firestationMock1)).thenReturn(firestationMock1);
     
     mockMvc.perform(post("/firestation")
    		 .contentType(MediaType.APPLICATION_JSON)
    		 .accept(MediaType.APPLICATION_JSON)
    		 .content(json))
     		 .andExpect(status().isCreated())
     		 .andExpect(jsonPath("$.address").value("salut"))
       	     .andExpect(jsonPath("$.station").value("77"));;
     
     verify(firestationServiceMock).saveFirestation(firestationMock1);
     
    }

    @Test
    public void updateFirestationTest() throws Exception
    {

    	Firestation firestationMock1 = mockFirestation1();
  	
     	Optional<Firestation> firestationOptionalMock1 = Optional.ofNullable(firestationMock1);
     	
    	ObjectMapper mapper = new ObjectMapper();
     	String json =  mapper.writeValueAsString(firestationMock1);
   
    	when(firestationServiceMock.updateFirestation(id1,firestationMock1)).thenReturn(firestationOptionalMock1);
   
     mockMvc.perform(put("/firestation/{id}",id1)
    		 .content(json)
    		 .contentType(MediaType.APPLICATION_JSON)
    		 .accept(MediaType.APPLICATION_JSON))
     		 .andExpect(status().isOk())
     		 .andExpect(jsonPath("$.address").value("salut"))
       	     .andExpect(jsonPath("$.station").value("77"));
       	
       	  verify(firestationServiceMock).updateFirestation(id1,firestationMock1);
    
    }
    
    @Test
    public void updateFirestationNotFoundTest() throws Exception
    {

    	Firestation firestationMock = mockFirestation();
     	
    	ObjectMapper mapper = new ObjectMapper();
     	String json =  mapper.writeValueAsString(firestationMock);
   
     mockMvc.perform(put("/firestation/{id}",id1)
    		 .content(json)
    		 .contentType(MediaType.APPLICATION_JSON)
    		 .accept(MediaType.APPLICATION_JSON))
     		 .andExpect(status().isNotFound());
       	
       	  verify(firestationServiceMock).updateFirestation(id1,firestationMock);
    
    }
}
