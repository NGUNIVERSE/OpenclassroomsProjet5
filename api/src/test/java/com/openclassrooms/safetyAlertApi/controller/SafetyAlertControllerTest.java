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
package com.openclassrooms.safetyAlertApi.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.openclassrooms.safetyAlertApi.dto.ChildDto;
import com.openclassrooms.safetyAlertApi.dto.FloodListDto;
import com.openclassrooms.safetyAlertApi.dto.HomeMembresDto;
import com.openclassrooms.safetyAlertApi.dto.PersonCoveredDto;
import com.openclassrooms.safetyAlertApi.dto.PersonInfoDto;
import com.openclassrooms.safetyAlertApi.dto.PersonLivingAtAnAddressDto;
import com.openclassrooms.safetyAlertApi.model.Person;
import com.openclassrooms.safetyAlertApi.service.FirestationService;
import com.openclassrooms.safetyAlertApi.service.SafetyAlertService;

public class SafetyAlertControllerTest {

	private MockMvc mockMvc;

	@Mock
	private FirestationService firestationService;

	@Mock
	private SafetyAlertService safetyAlertServiceMock;

	@InjectMocks
	private SafetyAlertController safetyAlertController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(safetyAlertController).build();
	}

	private Person mockPerson() {
		Person personMock = new Person();

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
		Person personMock1 = new Person();
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

		mockMvc.perform(get("/phoneAlert?firestation=" + 1)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(2))).andExpect(jsonPath("$[0]").value("000-000-000"))
				.andExpect(jsonPath("$[1]").value("111-111-111"));

		verify(safetyAlertServiceMock).getPhoneNumberOfPersonByFirestation(station);

	}

	@Test
	public void listOfChildAtAnAddressTest() throws Exception {

		Person personMock = mockPerson();
		Person personMock1 = mockPerson1();
		String addressMock = "Culver";
		List<Person> listOfPerson = new ArrayList<>();
		listOfPerson.add(personMock);
		listOfPerson.add(personMock1);
		HomeMembresDto homeMembresDtoMock = new HomeMembresDto();
		HomeMembresDto homeMembresDtoMock1 = new HomeMembresDto();
		homeMembresDtoMock.setFirstname(personMock.getFirstname());
		homeMembresDtoMock1.setFirstname(personMock1.getFirstname());
		List<HomeMembresDto> listOfHomeMembres = new ArrayList<>();
		listOfHomeMembres.add(homeMembresDtoMock);
		listOfHomeMembres.add(homeMembresDtoMock1);

		ChildDto childMock = new ChildDto("roger", "rabbit", 20, listOfHomeMembres);
		ChildDto childMock1 = new ChildDto("babar", "fox", 30, listOfHomeMembres);

		List<ChildDto> listOfChild = new ArrayList<>();
		listOfChild.add(childMock);
		listOfChild.add(childMock1);

		when(safetyAlertServiceMock.getChildListFromAnAddress(addressMock)).thenReturn(listOfChild);

		mockMvc.perform(get("/childAlert?address=" + addressMock)).andExpect(status().isOk())
				.andExpect(jsonPath("[0].firstname", is("roger"))).andExpect(jsonPath("[0].lastname", is("rabbit")))
				.andExpect(jsonPath("[0].age", is(20)))

				.andExpect(jsonPath("[1].firstname", is("babar"))).andExpect(jsonPath("[1].lastname", is("fox")))
				.andExpect(jsonPath("[1].age", is(30)));

		verify(safetyAlertServiceMock).getChildListFromAnAddress(addressMock);
	}

	@Test
	public void listOfPersonCoveredByFirestationTest() throws Exception {

		PersonCoveredDto personCoveredMock = new PersonCoveredDto();
		personCoveredMock.setFirstname("Reginold");
		personCoveredMock.setLastname("Walker");
		personCoveredMock.setAddress("908 73rd St");
		personCoveredMock.setPhoneNumber("841-874-8547");
		personCoveredMock.setNombreEnfant(0);
		personCoveredMock.setNombreAdulte(0);
		PersonCoveredDto personCoveredMock1 = new PersonCoveredDto();
		personCoveredMock1.setFirstname("Ron");
		personCoveredMock1.setLastname("Peters");
		personCoveredMock1.setAddress("112 Steppes P1");
		personCoveredMock1.setPhoneNumber("841-874-8888");
		personCoveredMock1.setNombreEnfant(1);
		personCoveredMock1.setNombreAdulte(1);

		String stationMock = "1";
		List<PersonCoveredDto> listOfPersonCovered = new ArrayList<>();
		listOfPersonCovered.add(personCoveredMock);
		listOfPersonCovered.add(personCoveredMock1);

		when(safetyAlertServiceMock.getPersonCoveredByFirestation(stationMock)).thenReturn(listOfPersonCovered);

		mockMvc.perform(get("/firestation?stationNumber=" + stationMock)).andExpect(status().isOk())
				.andExpect(jsonPath("[0].firstname", is("Reginold"))).andExpect(jsonPath("[0].lastname", is("Walker")))
				.andExpect(jsonPath("[0].address", is("908 73rd St")))
				.andExpect(jsonPath("[0].phoneNumber", is("841-874-8547")))
				.andExpect(jsonPath("[0].nombreAdulte", is(0))).andExpect(jsonPath("[0].nombreEnfant", is(0)))
				.andExpect(jsonPath("[1].firstname", is("Ron"))).andExpect(jsonPath("[1].lastname", is("Peters")))
				.andExpect(jsonPath("[1].address", is("112 Steppes P1")))
				.andExpect(jsonPath("[1].phoneNumber", is("841-874-8888")))
				.andExpect(jsonPath("[1].nombreAdulte", is(1))).andExpect(jsonPath("[1].nombreEnfant", is(1)));

		verify(safetyAlertServiceMock).getPersonCoveredByFirestation(stationMock);
	}

	@Test
	public void listOfPersonLivingAtAnAddressTest() throws Exception {

		PersonLivingAtAnAddressDto personLivingAtAnAddressMock = new PersonLivingAtAnAddressDto();
		personLivingAtAnAddressMock.setFirstname("Alpha");
		personLivingAtAnAddressMock.setLastname("Bravo");
		personLivingAtAnAddressMock.setPhoneNumber("000-000-0001");
		personLivingAtAnAddressMock.setAge(20);
		personLivingAtAnAddressMock.setMedications(List.of("Charlie"));
		personLivingAtAnAddressMock.setAllergies(List.of("Delta"));
		personLivingAtAnAddressMock.setFirestationNumber("1");

		PersonLivingAtAnAddressDto personLivingAtAnAddressMock1 = new PersonLivingAtAnAddressDto();
		personLivingAtAnAddressMock1.setFirstname("Alpha1");
		personLivingAtAnAddressMock1.setLastname("Bravo1");
		personLivingAtAnAddressMock1.setPhoneNumber("000-000-0002");
		personLivingAtAnAddressMock1.setAge(30);
		personLivingAtAnAddressMock1.setMedications(List.of("Charlie1"));
		personLivingAtAnAddressMock1.setAllergies(List.of("Delta1"));
		personLivingAtAnAddressMock1.setFirestationNumber("1");

		String addressMock = "Roissy";
		List<PersonLivingAtAnAddressDto> listOfPersonLivingAtAnAddress = new ArrayList<>();
		listOfPersonLivingAtAnAddress.add(personLivingAtAnAddressMock);
		listOfPersonLivingAtAnAddress.add(personLivingAtAnAddressMock1);

		when(safetyAlertServiceMock.getPersonLivingAtAnAddress(addressMock)).thenReturn(listOfPersonLivingAtAnAddress);

		mockMvc.perform(get("/fire?address=" + addressMock)).andExpect(status().isOk())
				.andExpect(jsonPath("[0].firstname", is("Alpha"))).andExpect(jsonPath("[0].lastname", is("Bravo")))
				.andExpect(jsonPath("[0].phoneNumber", is("000-000-0001"))).andExpect(jsonPath("[0].age", is(20)))
				.andExpect(jsonPath("[0].medications[0]", is("Charlie")))
				.andExpect(jsonPath("[0].allergies[0]", is("Delta")))
				.andExpect(jsonPath("[0].firestationNumber", is("1")))
				.andExpect(jsonPath("[1].firstname", is("Alpha1"))).andExpect(jsonPath("[1].lastname", is("Bravo1")))
				.andExpect(jsonPath("[1].phoneNumber", is("000-000-0002"))).andExpect(jsonPath("[1].age", is(30)))
				.andExpect(jsonPath("[1].medications[0]", is("Charlie1")))
				.andExpect(jsonPath("[1].allergies[0]", is("Delta1")))
				.andExpect(jsonPath("[1].firestationNumber", is("1")));

		verify(safetyAlertServiceMock).getPersonLivingAtAnAddress(addressMock);

	}

	@Test
	public void informationAboutPersonTest() throws Exception {

		PersonInfoDto personInfoMock = new PersonInfoDto();
		personInfoMock.setFirstname("Alpha");
		personInfoMock.setLastname("Bravo");
		personInfoMock.setAddress("Roissy");
		personInfoMock.setAge(20);
		personInfoMock.setMedications(List.of("Charlie"));
		personInfoMock.setAllergies(List.of("Delta"));
		personInfoMock.setEmail("alpha@gmail.com");

		String firstnameMock = "Alpha";
		String lastnameMock = "Bravo";

		when(safetyAlertServiceMock.getInformationAboutPerson(firstnameMock, lastnameMock)).thenReturn(personInfoMock);

		mockMvc.perform(get("/personInfo?firstName=" + firstnameMock + "&lastName=" + lastnameMock))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstname").value("Alpha"))
				.andExpect(jsonPath("$.lastname", is("Bravo"))).andExpect(jsonPath("$.address", is("Roissy")))
				.andExpect(jsonPath("$.age", is(20))).andExpect(jsonPath("$.medications[0]", is("Charlie")))
				.andExpect(jsonPath("$.allergies[0]", is("Delta")))
				.andExpect(jsonPath("$.email", is("alpha@gmail.com")));

		verify(safetyAlertServiceMock).getInformationAboutPerson(firstnameMock, lastnameMock);

	}

	@Test
	public void listOfHomeDeservedByFirestationTest() throws Exception {

		FloodListDto floodListMock = new FloodListDto();
		floodListMock.setFirstname("Alpha");
		floodListMock.setLastname("Bravo");
		floodListMock.setPhoneNumber("000-000-0001");
		floodListMock.setAge(20);
		floodListMock.setMedications(List.of("Charlie"));
		floodListMock.setAllergies(List.of("Delta"));
		floodListMock.setAddress("1");

		FloodListDto floodListMock1 = new FloodListDto();
		floodListMock1.setFirstname("Alpha1");
		floodListMock1.setLastname("Bravo1");
		floodListMock1.setPhoneNumber("000-000-0002");
		floodListMock1.setAge(30);
		floodListMock1.setMedications(List.of("Charlie1"));
		floodListMock1.setAllergies(List.of("Delta1"));
		floodListMock1.setAddress("1");

		String stationNumerMock = "1";
		List<String> stationMock = new ArrayList<>();
		stationMock.add(stationNumerMock);
		List<FloodListDto> listOfFloodList = new ArrayList<>();
		listOfFloodList.add(floodListMock);
		listOfFloodList.add(floodListMock1);

		when(safetyAlertServiceMock.getListOfHomeDeservedByFirestation(stationMock)).thenReturn(listOfFloodList);

		mockMvc.perform(get("/flood/stations?stations=" + stationMock.get(0))).andExpect(status().isOk())
				.andExpect(jsonPath("[0].firstname", is("Alpha"))).andExpect(jsonPath("[0].lastname", is("Bravo")))
				.andExpect(jsonPath("[0].phoneNumber", is("000-000-0001"))).andExpect(jsonPath("[0].age", is(20)))
				.andExpect(jsonPath("[0].medications[0]", is("Charlie")))
				.andExpect(jsonPath("[0].allergies[0]", is("Delta"))).andExpect(jsonPath("[0].address", is("1")))
				.andExpect(jsonPath("[1].firstname", is("Alpha1"))).andExpect(jsonPath("[1].lastname", is("Bravo1")))
				.andExpect(jsonPath("[1].phoneNumber", is("000-000-0002"))).andExpect(jsonPath("[1].age", is(30)))
				.andExpect(jsonPath("[1].medications[0]", is("Charlie1")))
				.andExpect(jsonPath("[1].allergies[0]", is("Delta1"))).andExpect(jsonPath("[1].address", is("1")));

		verify(safetyAlertServiceMock).getListOfHomeDeservedByFirestation(stationMock);

	}

}
