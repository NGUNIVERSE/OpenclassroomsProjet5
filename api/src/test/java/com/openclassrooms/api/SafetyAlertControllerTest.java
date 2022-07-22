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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.api.controller.SafetyAlertController;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.service.FirestationService;
import com.openclassrooms.api.service.SafetyAlertService;

//@WebMvcTest(controllers = SafetyAlertController.class)
public class SafetyAlertControllerTest {

  //  @Autowired
    private MockMvc mockMvc;
    
    @Mock
    private FirestationService firestationService;

    @Mock
    private SafetyAlertService safetyAlertServiceMock;

    @InjectMocks
    private SafetyAlertController safetyAlertController;
    
    
    @BeforeEach
    public void setup()
    {
    	MockitoAnnotations.openMocks(this);
    	this.mockMvc = MockMvcBuilders.standaloneSetup(safetyAlertController).build(); // Remplace @WebMvcTest(controllers = SafetyAlertController.class) en évitant de charger tout le contexte gain de temps 
    }
    
    
    
    
  /*  @MockBean
    private Firestation firestationMock; */
    
    
    
  //  listOfPhoneNumberOfPersonByFirestationTest
 /*   listOfChildAtAnAddressTest
    listOfPersonCoveredByFirestationTest
    listOfPersonLivingAtAnAddressTest
    informationAboutPersonTest
    listOfHomeDeservedByFirestationTest */
    
    @Test
    public void listOfPhoneNumberOfPersonByFirestationTest() throws Exception {
    	
    	String station = "1";
    	List<String> phone = new ArrayList<>();
    	String phone1 = "000-000-000";
    	String phone2 = "111-111-111";
    	phone.add(phone1);
    	phone.add(phone2);
    	    	
    /*	ObjectMapper mapper = new ObjectMapper();
     	String json =  mapper.writeValueAsString(phone); */
    	
    	when(safetyAlertServiceMock.getPhoneNumberOfPersonByFirestation(station)).thenReturn(phone);
    	    	
    	mockMvc.perform(get("/phoneAlert?firestation=" + 1))
    	.andDo(print())
    	.andExpect(status().isOk())
          .andExpect(jsonPath("$",Matchers.hasSize(2)))  //.andExpect(jsonPath("$.size()").value(2))
          .andExpect(jsonPath("$[0]").value("000-000-000"))
          .andExpect(jsonPath("$[1]").value("111-111-111"));

    	verify(safetyAlertServiceMock).getPhoneNumberOfPersonByFirestation(station);

      }
    
    
//    @Test
//    public void testGetFirestations() throws Exception {
//    	Firestation firestationMock= new Firestation();
//    	Firestation firestationMock1= new Firestation();
//    	firestationMock.setAddress("1509 Culver St");
//    	firestationMock.setStation("3");
//    	firestationMock1.setAddress("salut");
//    	firestationMock1.setStation("77");
//    	List<Firestation> listOfFirestation = new ArrayList<>();
//    	listOfFirestation.add(firestationMock);
//    	listOfFirestation.add(firestationMock1); 
//    	Iterable<Firestation> listOfFirestationTest = listOfFirestation;
//    	
//    	when(firestationServiceMock.getFirestations()).thenReturn(listOfFirestationTest);
//    	
//     mockMvc.perform(get("/firestations")).andExpect(status().isOk())
//       .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
//       .andExpect(jsonPath("$[0].station", is("3")))
//    		   .andExpect(jsonPath("$[1].address", is("salut")))
//    	       .andExpect(jsonPath("$[1].station", is("77")));
//     verify(firestationServiceMock).getFirestations();
//     //assertThat
//       //verify(firestationService).getFirestations();      
//    //   mockMvc.perform(get("/firestations")).andExpect(status().isOk());         
//    }
//    
//    @Test
//    public void testDeleteFirestation() throws Exception{
//    
//    	Firestation firestationMock= new Firestation();
//    	Firestation firestationMock1= new Firestation();
//    	firestationMock.setAddress("1509 Culver St");
//    	firestationMock.setStation("3");
//    	firestationMock1.setAddress("salut");
//    	firestationMock1.setStation("77");
//    	List<Firestation> listOfFirestation = new ArrayList<>();
//    	listOfFirestation.add(firestationMock);
//    	listOfFirestation.add(firestationMock1); 
//    	Iterable<Firestation> listOfFirestationTest = listOfFirestation;
//    	long id2  = 1;
//    	when(firestationServiceMock.getFirestations()).thenReturn(listOfFirestationTest);
//    	
//        mockMvc.perform(get("/firestations")).andExpect(status().isOk())
//        .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
//        .andExpect(jsonPath("$[0].station", is("3")))
//     		   .andExpect(jsonPath("$[1].address", is("salut")))
//     	       .andExpect(jsonPath("$[1].station", is("77")));
//        
//     mockMvc.perform(delete("/firestation/{id}",1)).andExpect(status().isNoContent());
//     verify(firestationServiceMock).deleteFirestation(id2);
//     mockMvc.perform(get("/firestation/{id}",1)).andExpect(status().isNotFound());
//
//    }
//    
//    @Test
//    public void testCreateFirestation() throws Exception{
//    
//    	Firestation firestationMock= new Firestation();
//    	Firestation firestationMock1= new Firestation();
//    	firestationMock.setAddress("1509 Culver St");
//    	firestationMock.setStation("3");
//    	firestationMock1.setAddress("salut");
//    	firestationMock1.setStation("77");
//    	List<Firestation> listOfFirestation = new ArrayList<>();
//     	listOfFirestation.add(firestationMock);
//    //	listOfFirestation.add(firestationMock1); 
//    	//Iterable<Firestation> listOfFirestationTest = listOfFirestation;
//    	ObjectMapper mapper = new ObjectMapper();
//     	String json =  mapper.writeValueAsString(firestationMock1);
//     	
//    	when(firestationServiceMock.getFirestations()).thenReturn(listOfFirestation);
//    	when(firestationServiceMock.saveFirestation(firestationMock1)).thenReturn(firestationMock1);
//    	
//     mockMvc.perform(get("/firestations")).andExpect(status().isOk())
//     .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
//     .andExpect(jsonPath("$[0].station", is("3")));
//     
//     mockMvc.perform(post("/firestation")
//    		 .contentType(MediaType.APPLICATION_JSON)
//    		 .accept(MediaType.APPLICATION_JSON)
//    		 .content(json))
//     		 .andExpect(status().isCreated())
//     		 .andExpect(jsonPath("$..address").value("salut"))
//       	     .andExpect(jsonPath("$..station").value("77"));;
//     
//     verify(firestationServiceMock).saveFirestation(firestationMock1);
//     
// /*    mockMvc.perform(get("/firestations")).andExpect(status().isOk())
//       .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
//       .andExpect(jsonPath("$[0].station", is("3")))
//    		   .andExpect(jsonPath("$[1].address", is("salut"))) // No result for path $[1][adress]
//    	       .andExpect(jsonPath("$[1].station", is("77"))); */
//     
//     verify(firestationServiceMock).saveFirestation(firestationMock1);
//    }
//
//    @Test
//    public void updateFirestationTest() throws Exception
//    {
//      	Firestation firestationMock= new Firestation();
//    	Firestation firestationMock1= new Firestation();
//    	firestationMock.setAddress("1509 Culver St");
//    	firestationMock.setStation("3");
//    	firestationMock1.setAddress("salut");
//    	firestationMock1.setStation("77");
//    	List<Firestation> listOfFirestation = new ArrayList<>();
//     	listOfFirestation.add(firestationMock);
//     	
//     	Optional<Firestation> firestationOptionalMock1 = Optional.ofNullable(firestationMock1);
//     	
//    //	listOfFirestation.add(firestationMock1); 
//    	//Iterable<Firestation> listOfFirestationTest = listOfFirestation;
//    	ObjectMapper mapper = new ObjectMapper();
//     	String json =  mapper.writeValueAsString(firestationMock1);
//     	long id1 = 0;
//    	when(firestationServiceMock.getFirestations()).thenReturn(listOfFirestation);
//    	//when(firestationServiceMock.saveFirestation(firestationMock1)).thenReturn(firestationMock1);
//    	  when(firestationServiceMock.updateFirestation(id1,firestationMock1)).thenReturn(firestationOptionalMock1);
//   
//    	  mockMvc.perform(get("/firestations")).andExpect(status().isOk())
//     .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
//     .andExpect(jsonPath("$[0].station", is("3")));
//     
//     mockMvc.perform(put("/firestation/{id}",0)
//    		 .content(json)
//    		 .contentType(MediaType.APPLICATION_JSON)
//    		 .accept(MediaType.APPLICATION_JSON))
//     		 .andExpect(status().isOk())
//     		 .andExpect(jsonPath("$..address").value("salut"))
//       	     .andExpect(jsonPath("$..station").value("77"));
//       	
//       	  verify(firestationServiceMock).updateFirestation(id1,firestationMock1);
//       	  
//   /*    	     mockMvc.perform(get("/firestations")).andExpect(status().isOk())
//       	     .andExpect(jsonPath("$[0].address", is("salut")))                 // POURQUOI CA MARCHE PAS ????
//       	     .andExpect(jsonPath("$[0].station", is("77"))); */
//   
//       	     
// /*    mockMvc.perform(get("/firestations")).andExpect(status().isOk())
//       .andExpect(jsonPath("$[0].address", is("1509 Culver St")))
//       .andExpect(jsonPath("$[0].station", is("3")))
//    		   .andExpect(jsonPath("$[1].address", is("salut"))) // No result for path $[1][adress]
//    	       .andExpect(jsonPath("$[1].station", is("77"))); */
//     
//  //   verify(firestationServiceMock).saveFirestation(firestationMock1);
//
//    }
}
