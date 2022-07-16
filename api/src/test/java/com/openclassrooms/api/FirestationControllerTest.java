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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.openclassrooms.api.controller.FirestationController;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.service.FirestationService;

@WebMvcTest(controllers = FirestationController.class)
public class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationService firestationServiceMock;

  /*  @MockBean
    private Firestation firestationMock; */
    
    @Test
    public void testGetFirestations() throws Exception {
    	Firestation firestationMock= new Firestation();
    	Firestation firestationMock1= new Firestation();
    	firestationMock.setAddress("1509 Culver St");
    	firestationMock.setStation("3");
    	firestationMock1.setAddress("salut");
    	firestationMock1.setStation("77");
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
     //assertThat
       //verify(firestationService).getFirestations();      
    //   mockMvc.perform(get("/firestations")).andExpect(status().isOk());         
    }

}