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
import org.hamcrest.collection.IsArray;
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
import com.openclassrooms.api.model.Child;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.model.FloodList;
import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.model.PersonCovered;
import com.openclassrooms.api.model.PersonInfo;
import com.openclassrooms.api.model.PersonLivingAtAnAddress;
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
    public void listOfPhoneNumberOfPersonByFirestationTest() throws Exception {
    	
    	String station = "1";
    	List<String> phone = new ArrayList<>();
    	String phone1 = "000-000-000";
    	String phone2 = "111-111-111";
    	phone.add(phone1);
    	phone.add(phone2);
    	
    	when(safetyAlertServiceMock.getPhoneNumberOfPersonByFirestation(station)).thenReturn(phone);
    	    	
    	mockMvc.perform(get("/phoneAlert?firestation=" + 1))
    		.andDo(print())
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$",Matchers.hasSize(2)))  //.andExpect(jsonPath("$.size()").value(2))
    		.andExpect(jsonPath("$[0]").value("000-000-000"))
    		.andExpect(jsonPath("$[1]").value("111-111-111"));

    	verify(safetyAlertServiceMock).getPhoneNumberOfPersonByFirestation(station);

      }
    
    @Test
    public void listOfChildAtAnAddressTest() throws Exception{
    	
    	Person personMock = mockPerson();
    	Person personMock1 = mockPerson1();
    	String addressMock = "Culver";
    	List<Person> listOfPerson = new ArrayList<>();
    	listOfPerson.add(personMock);
    	listOfPerson.add(personMock1);
    	
    	Child childMock = new Child("roger","rabbit",20,listOfPerson);
    	Child childMock1 = new Child("babar","fox",30,listOfPerson);
    	List<Child> listOfChild = new ArrayList<>();
    	listOfChild.add(childMock);
    	listOfChild.add(childMock1);
    	
    	when(safetyAlertServiceMock.getChildListFromAnAddress(addressMock)).thenReturn(listOfChild);
    	
    	mockMvc.perform(get("/childAlert?address=" + addressMock))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("[0].firstname", is("roger")))
    		.andExpect(jsonPath("[0].lastname", is("rabbit")))
    		.andExpect(jsonPath("[0].age", is(20)))
    	//	.andExpect(jsonPath("[0].homeMembres", is(listOfPerson)))   // Problem avec les tableaux
    		.andExpect(jsonPath("[1].firstname", is("babar")))
    		.andExpect(jsonPath("[1].lastname", is("fox")))
    		.andExpect(jsonPath("[1].age", is(30)));
    	//	.andExpect(jsonPath("[1].homeMembres", is(listOfPerson)));   // Problem avec les tableaux
    		
    	verify(safetyAlertServiceMock).getChildListFromAnAddress(addressMock);
    }
    
    @Test
    public void listOfPersonCoveredByFirestationTest() throws Exception{
    	
      	PersonCovered personCoveredMock = new PersonCovered();
      	personCoveredMock.setFirstname("Reginold");
    	personCoveredMock.setLastname("Walker");
    	personCoveredMock.setAddress("908 73rd St");
    	personCoveredMock.setPhoneNumber("841-874-8547");
    	personCoveredMock.setNombreEnfant(0);
    	personCoveredMock.setNombreAdulte(0);
      	PersonCovered personCoveredMock1 = new PersonCovered();
      	personCoveredMock1.setFirstname("Ron");
    	personCoveredMock1.setLastname("Peters");
    	personCoveredMock1.setAddress("112 Steppes P1");
    	personCoveredMock1.setPhoneNumber("841-874-8888");
    	personCoveredMock1.setNombreEnfant(1);
    	personCoveredMock1.setNombreAdulte(1);
    	
    	String stationMock = "1";
    	List<PersonCovered> listOfPersonCovered = new ArrayList<>();
    	listOfPersonCovered.add(personCoveredMock);
    	listOfPersonCovered.add(personCoveredMock1);

    	
    	when(safetyAlertServiceMock.getPersonCoveredByFirestation(stationMock)).thenReturn(listOfPersonCovered);
    	
    	mockMvc.perform(get("/firestation?station=" + stationMock))   //revoir URL car ici "station" au lieu de "stationNumber"
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("[0].firstname", is("Reginold")))
    		.andExpect(jsonPath("[0].lastname", is("Walker")))
    		.andExpect(jsonPath("[0].address", is("908 73rd St")))
    		.andExpect(jsonPath("[0].phoneNumber", is("841-874-8547")))
    		.andExpect(jsonPath("[0].nombreAdulte", is(0)))
    		.andExpect(jsonPath("[0].nombreEnfant", is(0)))
    		.andExpect(jsonPath("[1].firstname", is("Ron")))
    		.andExpect(jsonPath("[1].lastname", is("Peters")))
    		.andExpect(jsonPath("[1].address", is("112 Steppes P1")))
    		.andExpect(jsonPath("[1].phoneNumber", is("841-874-8888")))
    		.andExpect(jsonPath("[1].nombreAdulte", is(1)))
    		.andExpect(jsonPath("[1].nombreEnfant", is(1)));
   
    		
    	verify(safetyAlertServiceMock).getPersonCoveredByFirestation(stationMock);
    }
    @Test
    public void listOfPersonLivingAtAnAddressTest() throws Exception{
    	
    	PersonLivingAtAnAddress personLivingAtAnAddressMock = new PersonLivingAtAnAddress();
    	personLivingAtAnAddressMock.setFirstname("Alpha");
    	personLivingAtAnAddressMock.setLastname("Bravo");
    	personLivingAtAnAddressMock.setPhoneNumber("000-000-0001");
    	personLivingAtAnAddressMock.setAge(20);
    	personLivingAtAnAddressMock.setMedications("Charlie");
    	personLivingAtAnAddressMock.setAllergies("Delta");
    	personLivingAtAnAddressMock.setFirestationNumber("1");
    	
    	PersonLivingAtAnAddress personLivingAtAnAddressMock1 = new PersonLivingAtAnAddress();
    	personLivingAtAnAddressMock1.setFirstname("Alpha1");
    	personLivingAtAnAddressMock1.setLastname("Bravo1");
    	personLivingAtAnAddressMock1.setPhoneNumber("000-000-0002");
    	personLivingAtAnAddressMock1.setAge(30);
    	personLivingAtAnAddressMock1.setMedications("Charlie1");
    	personLivingAtAnAddressMock1.setAllergies("Delta1");
    	personLivingAtAnAddressMock1.setFirestationNumber("1");
    	    	
    	String addressMock = "Roissy";
    	List<PersonLivingAtAnAddress> listOfPersonLivingAtAnAddress = new ArrayList<>();
    	listOfPersonLivingAtAnAddress.add(personLivingAtAnAddressMock);
    	listOfPersonLivingAtAnAddress.add(personLivingAtAnAddressMock1);
    		
    	when(safetyAlertServiceMock.getPersonLivingAtAnAddress(addressMock)).thenReturn(listOfPersonLivingAtAnAddress);
    	
    	mockMvc.perform(get("/fire?address=" + addressMock))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("[0].firstname", is("Alpha")))
    		.andExpect(jsonPath("[0].lastname", is("Bravo")))
    		.andExpect(jsonPath("[0].phoneNumber", is("000-000-0001")))
    		.andExpect(jsonPath("[0].age", is(20)))
    		.andExpect(jsonPath("[0].medications", is("Charlie")))
    		.andExpect(jsonPath("[0].allergies", is("Delta")))
    		.andExpect(jsonPath("[0].firestationNumber", is("1")))
    		.andExpect(jsonPath("[1].firstname", is("Alpha1")))
    		.andExpect(jsonPath("[1].lastname", is("Bravo1")))
    		.andExpect(jsonPath("[1].phoneNumber", is("000-000-0002")))
    		.andExpect(jsonPath("[1].age", is(30)))
    		.andExpect(jsonPath("[1].medications", is("Charlie1")))
    		.andExpect(jsonPath("[1].allergies", is("Delta1")))
    		.andExpect(jsonPath("[1].firestationNumber", is("1")));
    		
    	verify(safetyAlertServiceMock).getPersonLivingAtAnAddress(addressMock);
    	
    }
    @Test
    public void informationAboutPersonTest() throws Exception{
    	
    	PersonInfo personInfoMock = new PersonInfo();
    	personInfoMock.setFirstname("Alpha");
    	personInfoMock.setLastname("Bravo");
    	personInfoMock.setAddress("Roissy");
    	personInfoMock.setAge(20);
    	personInfoMock.setMedications("Charlie");
    	personInfoMock.setAllergies("Delta");
    	personInfoMock.setEmail("alpha@gmail.com");
    	    	
    	String firstnameMock = "Alpha";
    	String lastnameMock = "Bravo";
    		
    	when(safetyAlertServiceMock.getInformationAboutPerson(firstnameMock,lastnameMock)).thenReturn(personInfoMock);
    	
    	mockMvc.perform(get("/personInfo?firstname=" + firstnameMock + "&lastname=" + lastnameMock))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.firstname").value("Alpha"))
    		.andExpect(jsonPath("$.lastname", is("Bravo")))
    		.andExpect(jsonPath("$.address", is("Roissy")))
    		.andExpect(jsonPath("$.age", is(20)))
    		.andExpect(jsonPath("$.medications", is("Charlie")))
    		.andExpect(jsonPath("$.allergies", is("Delta")))
    		.andExpect(jsonPath("$.email", is("alpha@gmail.com")));

    		
    	verify(safetyAlertServiceMock).getInformationAboutPerson(firstnameMock, lastnameMock);
    	
    }
    @Test
    public void listOfHomeDeservedByFirestationTest() throws Exception{
    	
    	
    	FloodList floodListMock = new FloodList();
    	floodListMock.setFirstname("Alpha");
    	floodListMock.setLastname("Bravo");
    	floodListMock.setPhoneNumber("000-000-0001");
    	floodListMock.setAge(20);
    	floodListMock.setMedications("Charlie");
    	floodListMock.setAllergies("Delta");
    	floodListMock.setAddress("1");
    	
    	FloodList floodListMock1 = new FloodList();
    	floodListMock1.setFirstname("Alpha1");
    	floodListMock1.setLastname("Bravo1");
    	floodListMock1.setPhoneNumber("000-000-0002");
    	floodListMock1.setAge(30);
    	floodListMock1.setMedications("Charlie1");
    	floodListMock1.setAllergies("Delta1");
    	floodListMock1.setAddress("1");
    	    	
    	String stationMock = "1";
    	List<FloodList> listOfFloodList = new ArrayList<>();
    	listOfFloodList.add(floodListMock);
    	listOfFloodList.add(floodListMock1);
    		
    	when(safetyAlertServiceMock.getListOfHomeDeservedByFirestation(stationMock)).thenReturn(listOfFloodList);
    	
    	mockMvc.perform(get("/flood/stations?station=" + stationMock))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("[0].firstname", is("Alpha")))
    		.andExpect(jsonPath("[0].lastname", is("Bravo")))
    		.andExpect(jsonPath("[0].phoneNumber", is("000-000-0001")))
    		.andExpect(jsonPath("[0].age", is(20)))
    		.andExpect(jsonPath("[0].medications", is("Charlie")))
    		.andExpect(jsonPath("[0].allergies", is("Delta")))
    		.andExpect(jsonPath("[0].address", is("1")))
    		.andExpect(jsonPath("[1].firstname", is("Alpha1")))
    		.andExpect(jsonPath("[1].lastname", is("Bravo1")))
    		.andExpect(jsonPath("[1].phoneNumber", is("000-000-0002")))
    		.andExpect(jsonPath("[1].age", is(30)))
    		.andExpect(jsonPath("[1].medications", is("Charlie1")))
    		.andExpect(jsonPath("[1].allergies", is("Delta1")))
    		.andExpect(jsonPath("[1].address", is("1")));
    		
    	verify(safetyAlertServiceMock).getListOfHomeDeservedByFirestation(stationMock);
    	
    }
    

}
