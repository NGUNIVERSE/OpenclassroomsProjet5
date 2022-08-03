package com.openclassrooms.safetyAlertApi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.safetyAlertApi.dto.ChildDto;
import com.openclassrooms.safetyAlertApi.dto.FloodListDto;
import com.openclassrooms.safetyAlertApi.dto.HomeMembresDto;
import com.openclassrooms.safetyAlertApi.dto.PersonCoveredDto;
import com.openclassrooms.safetyAlertApi.dto.PersonInfoDto;
import com.openclassrooms.safetyAlertApi.dto.PersonLivingAtAnAddressDto;
import com.openclassrooms.safetyAlertApi.model.Firestation;
import com.openclassrooms.safetyAlertApi.model.Medicalrecord;
import com.openclassrooms.safetyAlertApi.model.Person;
import com.openclassrooms.safetyAlertApi.service.FirestationService;
import com.openclassrooms.safetyAlertApi.service.MedicalrecordService;
import com.openclassrooms.safetyAlertApi.service.PersonService;
import com.openclassrooms.safetyAlertApi.service.SafetyAlertService;


public class SafetyAlertServiceTest {
	
	@Mock
	private FirestationService firestationServiceMock;
	
	@Mock
	private PersonService personServiceMock;
	
	@Mock
	private MedicalrecordService medicalrecordServiceMock;
	
	@Mock
	private SafetyAlertService safetyAlertServiceMock;
	
	@InjectMocks
	private SafetyAlertService safetyAlertService;
	
	 @BeforeEach
	    public void setup()
	    {
	    	MockitoAnnotations.openMocks(this);
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
	private Person mockPerson2() {
		Person personMock2= new Person();
    	personMock2.setFirstname("Junior");
      	personMock2.setLastname("Junior");
      	personMock2.setAddress("112 Steppes Pl");
      	personMock2.setCity("Culver");
      	personMock2.setZip("97451");
      	personMock2.setPhone("841-874-8888");
      	personMock2.setEmail("junior@email.com");
		return personMock2;
	}
	
private Medicalrecord mockMedicalrecord() {   	
	
	long age = 42;
    LocalDate localDate = LocalDate.now().minusYears(age);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
	String dateMock =localDate.format(formatter);
			
    	Medicalrecord medicalrecordMock = new Medicalrecord();
    	medicalrecordMock.setFirstname("Reginold");
    	medicalrecordMock.setLastname("Walker");
    	medicalrecordMock.setBirthdate(dateMock);
    	medicalrecordMock.setMedications(List.of("thradox:700mg"));
    	medicalrecordMock.setAllergies(List.of("illisoxian"));
    	
;
    	return medicalrecordMock;
    }

private Medicalrecord mockMedicalrecord1() {
	
	long age = 57;
    LocalDate localDate = LocalDate.now().minusYears(age);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
	String dateMock =localDate.format(formatter);
	
	Medicalrecord medicalrecordMock1 = new Medicalrecord();
	medicalrecordMock1.setFirstname("Ron");
	medicalrecordMock1.setLastname("Peters");
	medicalrecordMock1.setBirthdate(dateMock);
	medicalrecordMock1.setMedications(List.of(""));
	medicalrecordMock1.setAllergies(List.of(""));
	return medicalrecordMock1;
}

private Medicalrecord mockMedicalrecord2() {   	
	
	long age = 2;
    LocalDate localDate = LocalDate.now().minusYears(age);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
	String dateMock =localDate.format(formatter);
	
	Medicalrecord medicalrecordMock2 = new Medicalrecord();
	medicalrecordMock2.setFirstname("Junior");
	medicalrecordMock2.setLastname("Junior");
	medicalrecordMock2.setBirthdate(dateMock);
	medicalrecordMock2.setMedications(List.of("thradox:700mg"));
	medicalrecordMock2.setAllergies(List.of("illisoxian"));
	return medicalrecordMock2;
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

		
	 @Test
    public void getPhoneNumberOfPersonByFirestationTest() throws Exception {
    	
		    Person personMock = mockPerson();
	    	Person personMock1 = mockPerson1();
	    	List<Person> listOfPerson = new ArrayList<>();
	    	listOfPerson.add(personMock);
	    	listOfPerson.add(personMock1);
		 
	    	String station = "1";
	    	String addressMock = "908 73rd St";
			String addressMock1 = "112 Steppes Pl";
			
			List<String> listAddress = new ArrayList<>();
			listAddress.add(addressMock);
			listAddress.add(addressMock1);
	    
			List<String> phone = new ArrayList<>();
			String phone1 = "841-874-8547";
			String phone2 = "841-874-8888";
			phone.add(phone1);
			phone.add(phone2);
    	
			when(firestationServiceMock.getAddressByFirestation(station)).thenReturn(listAddress);
    	    when(personServiceMock.getPersonPerAddress(listAddress)).thenReturn(listOfPerson);
    	    when(personServiceMock.getPhoneNumberOfPerson(listOfPerson)).thenReturn(phone);

    	    List<String> result = safetyAlertService.getPhoneNumberOfPersonByFirestation(station);
    	    
    	verify(firestationServiceMock).getAddressByFirestation(station);
    	verify(personServiceMock).getPersonPerAddress(listAddress);
    	verify(personServiceMock).getPhoneNumberOfPerson(listOfPerson);
    	assertThat(result).isEqualTo(phone);
      }
	 
 
    @Test
    public void getChildListFromAnAddressTest() throws Exception{
    	

    	
    	Person personMock = mockPerson();
    	Person personMock1 = mockPerson1();
    	Person personMock2 = mockPerson2();
    	long age = 2;
    	String addressMock = "Culver";
    	List<Person> listOfPerson = new ArrayList<>();
    	listOfPerson.add(personMock);
    	listOfPerson.add(personMock1);
    	listOfPerson.add(personMock2);
    	
    	HomeMembresDto homeMembresDtoMock = new HomeMembresDto();
    	HomeMembresDto homeMembresDtoMock1 = new HomeMembresDto();
    	HomeMembresDto homeMembresDtoMock2 = new HomeMembresDto();
    	homeMembresDtoMock.setFirstname(personMock.getFirstname());
    	homeMembresDtoMock1.setFirstname(personMock1.getFirstname());
    	homeMembresDtoMock2.setFirstname(personMock2.getFirstname());
    	homeMembresDtoMock.setLastname(personMock.getLastname());
    	homeMembresDtoMock1.setLastname(personMock1.getLastname());
    	homeMembresDtoMock2.setLastname(personMock2.getLastname());
    	List<HomeMembresDto> listOfHomeMembres = new ArrayList<>();
    	listOfHomeMembres.add(homeMembresDtoMock);
    	listOfHomeMembres.add(homeMembresDtoMock1);
    	listOfHomeMembres.add(homeMembresDtoMock2);
    	
    	Medicalrecord medicalrecordMock = mockMedicalrecord();
    	Medicalrecord medicalrecordMock1 = mockMedicalrecord1();
    	Medicalrecord medicalrecordMock2 = mockMedicalrecord2();
    	
    	ChildDto childMock = new ChildDto("Junior","Junior",2,listOfHomeMembres);

    	List<ChildDto> listOfChild = new ArrayList<>();
    	listOfChild.add(childMock);

    	when(personServiceMock.getPersonPerAddress(addressMock)).thenReturn(listOfPerson);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(personMock.getFirstname(), personMock.getLastname())).thenReturn(medicalrecordMock);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(personMock1.getFirstname(), personMock1.getLastname())).thenReturn(medicalrecordMock1);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(personMock2.getFirstname(), personMock2.getLastname())).thenReturn(medicalrecordMock2);
       	
    	List<ChildDto> result = safetyAlertService.getChildListFromAnAddress(addressMock);	
    	
    	verify(personServiceMock).getPersonPerAddress(addressMock);
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(personMock.getFirstname(), personMock.getLastname());
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(personMock1.getFirstname(), personMock1.getLastname());
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(personMock2.getFirstname(), personMock2.getLastname());
      	
    	assertThat(result).isEqualTo(listOfChild);
    }
@Test
public void getAgeTest() throws Exception
{
	long age = 2;
    LocalDate localDate = LocalDate.now().minusYears(age);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
	String dateMock =localDate.format(formatter);

	long result = safetyAlertService.getAge(dateMock);
	
	assertThat(result).isEqualTo(age);   
		
}
	  
    @Test
    public void getPersonCoveredByFirestationTest() throws Exception{
    	
      	PersonCoveredDto personCoveredMock = new PersonCoveredDto();
      	personCoveredMock.setFirstname("Reginold");
    	personCoveredMock.setLastname("Walker");
    	personCoveredMock.setAddress("908 73rd St");
    	personCoveredMock.setPhoneNumber("841-874-8547");
    	personCoveredMock.setNombreEnfant(0);
    	personCoveredMock.setNombreAdulte(2);
      	PersonCoveredDto personCoveredMock1 = new PersonCoveredDto();
      	personCoveredMock1.setFirstname("Ron");
    	personCoveredMock1.setLastname("Peters");
    	personCoveredMock1.setAddress("112 Steppes Pl");
    	personCoveredMock1.setPhoneNumber("841-874-8888");
    	personCoveredMock1.setNombreEnfant(0);
    	personCoveredMock1.setNombreAdulte(2);
    	
    	 Person personMock = mockPerson();
	    	Person personMock1 = mockPerson1();
	    	List<Person> listOfPerson = new ArrayList<>();
	    	listOfPerson.add(personMock);
	    	listOfPerson.add(personMock1);
	    	
	    	Medicalrecord medicalrecordMock = mockMedicalrecord();
	    	Medicalrecord medicalrecordMock1 = mockMedicalrecord1();
	    	
    	String stationMock = "1";
    	List<PersonCoveredDto> listOfPersonCovered = new ArrayList<>();
    	listOfPersonCovered.add(personCoveredMock);
    	listOfPersonCovered.add(personCoveredMock1);

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
   	
    	when(firestationServiceMock.getAddressByFirestation(stationMock)).thenReturn(listAddress);
    	when(personServiceMock.getPersonPerAddress(listAddress)).thenReturn(listOfPerson);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(), listOfPerson.get(0).getLastname())).thenReturn(medicalrecordMock);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(), listOfPerson.get(1).getLastname())).thenReturn(medicalrecordMock1);
    	
    	List<PersonCoveredDto> result = safetyAlertService.getPersonCoveredByFirestation(stationMock);
    	
    	verify(firestationServiceMock).getAddressByFirestation(stationMock);
    	verify(personServiceMock).getPersonPerAddress(listAddress);
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(), listOfPerson.get(0).getLastname());
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(), listOfPerson.get(1).getLastname());
    	assertThat(result).isEqualTo(listOfPersonCovered);
    }
  
  
    @Test
    public void getPersonLivingAtAnAddressTest() throws Exception{
    	
    	PersonLivingAtAnAddressDto personLivingAtAnAddressMock = new PersonLivingAtAnAddressDto();
    	personLivingAtAnAddressMock.setFirstname("Reginold");
    	personLivingAtAnAddressMock.setLastname("Walker");
    	personLivingAtAnAddressMock.setPhoneNumber("841-874-8547");
    	personLivingAtAnAddressMock.setAge(42);
    	personLivingAtAnAddressMock.setMedications(List.of("thradox:700mg"));
    	personLivingAtAnAddressMock.setAllergies(List.of("illisoxian"));
    	personLivingAtAnAddressMock.setFirestationNumber("3");
    	
    	PersonLivingAtAnAddressDto personLivingAtAnAddressMock1 = new PersonLivingAtAnAddressDto();
    	personLivingAtAnAddressMock1.setFirstname("Ron");
    	personLivingAtAnAddressMock1.setLastname("Peters");
    	personLivingAtAnAddressMock1.setPhoneNumber("841-874-8888");
    	personLivingAtAnAddressMock1.setAge(57);
    	personLivingAtAnAddressMock1.setMedications(List.of(""));
    	personLivingAtAnAddressMock1.setAllergies(List.of(""));
    	personLivingAtAnAddressMock1.setFirestationNumber("3");
    	
    	 Person personMock = mockPerson();
	    	Person personMock1 = mockPerson1();
	    	List<Person> listOfPerson = new ArrayList<>();
	    	listOfPerson.add(personMock);
	    	listOfPerson.add(personMock1);
	    	
	    	Firestation firestationMock = mockFirestation();
	    	
	    	Medicalrecord medicalrecordMock = mockMedicalrecord();
	    	Medicalrecord medicalrecordMock1 = mockMedicalrecord1();
    	
    	String addressMock = "Roissy";
    	List<PersonLivingAtAnAddressDto> listOfPersonLivingAtAnAddress = new ArrayList<>();
    	listOfPersonLivingAtAnAddress.add(personLivingAtAnAddressMock);
    	listOfPersonLivingAtAnAddress.add(personLivingAtAnAddressMock1);
    		 
    	
       	when(personServiceMock.getPersonPerAddress(addressMock)).thenReturn(listOfPerson);
       	when(firestationServiceMock.getFirestationByAddress(addressMock)).thenReturn(firestationMock);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(), listOfPerson.get(0).getLastname())).thenReturn(medicalrecordMock);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(), listOfPerson.get(1).getLastname())).thenReturn(medicalrecordMock1);

    	List<PersonLivingAtAnAddressDto> result = safetyAlertService.getPersonLivingAtAnAddress(addressMock);

    	verify(personServiceMock).getPersonPerAddress(addressMock);
      	verify(firestationServiceMock).getFirestationByAddress(addressMock);
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(), listOfPerson.get(0).getLastname());
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(), listOfPerson.get(1).getLastname());
    	assertThat(result).isEqualTo(listOfPersonLivingAtAnAddress);
    	
    }
    
  
    @Test
    public void getInformationAboutPersonTest() throws Exception{
    	
    	PersonInfoDto personInfoMock = new PersonInfoDto();
    	personInfoMock.setFirstname("Reginold");
    	personInfoMock.setLastname("Walker");
    	personInfoMock.setAddress("908 73rd St");
    	personInfoMock.setAge(42);
    	personInfoMock.setMedications(List.of("thradox:700mg"));
    	personInfoMock.setAllergies(List.of("illisoxian"));
    	personInfoMock.setEmail("reg@email.com");
    	    	
    	Person personMock = mockPerson();
    	Medicalrecord medicalrecordMock = mockMedicalrecord();
    	
    	String firstnameMock = "Reginold";
    	String lastnameMock = "Walker";
    		
    	when(personServiceMock.getPersonByFirstnameAndLastname(firstnameMock, lastnameMock)).thenReturn(personMock);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(firstnameMock, lastnameMock)).thenReturn(medicalrecordMock);
    	
    	PersonInfoDto result = safetyAlertService.getInformationAboutPerson(firstnameMock, lastnameMock);
    	
    	verify(personServiceMock).getPersonByFirstnameAndLastname(firstnameMock, lastnameMock);
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(firstnameMock, lastnameMock);
    	assertThat(result).isEqualTo(personInfoMock);
    }
    
    
    @Test
    public void getListOfHomeDeservedByFirestationTest() throws Exception{
    	
    	
    	FloodListDto floodListMock = new FloodListDto();
    	floodListMock.setFirstname("Reginold");
    	floodListMock.setLastname("Walker");
    	floodListMock.setPhoneNumber("841-874-8547");
    	floodListMock.setAge(42);
    	floodListMock.setMedications(List.of("thradox:700mg"));
    	floodListMock.setAllergies(List.of("illisoxian"));
    	floodListMock.setAddress("908 73rd St");
    	
    	FloodListDto floodListMock1 = new FloodListDto();
    	floodListMock1.setFirstname("Ron");
    	floodListMock1.setLastname("Peters");
    	floodListMock1.setPhoneNumber("841-874-8888");
    	floodListMock1.setAge(57);
    	floodListMock1.setMedications(List.of(""));
    	floodListMock1.setAllergies(List.of(""));
    	floodListMock1.setAddress("112 Steppes Pl");
    	    	
    	String stationNumerMock = "1";
    	List<String> stationMock = new ArrayList<>();
    	stationMock.add(stationNumerMock);
    	List<FloodListDto> listOfFloodList = new ArrayList<>();
    	listOfFloodList.add(floodListMock);
    	listOfFloodList.add(floodListMock1);
    	
    	String addressMock = "1509 Culver St";
		String addressMock1 = "salut";
		
		List<String> listAddress = new ArrayList<>();
		listAddress.add(addressMock);
		listAddress.add(addressMock1);
		
		 Person personMock = mockPerson();
	    	Person personMock1 = mockPerson1();
	    	List<Person> listOfPerson = new ArrayList<>();
	    	listOfPerson.add(personMock);
	    	listOfPerson.add(personMock1);
	    	
	    	Medicalrecord medicalrecordMock = mockMedicalrecord();
	    	Medicalrecord medicalrecordMock1 = mockMedicalrecord1();
    		
    	when(firestationServiceMock.getAddressByFirestation(stationNumerMock)).thenReturn(listAddress);
    	when(personServiceMock.getPersonPerAddress(listAddress)).thenReturn(listOfPerson);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(), listOfPerson.get(0).getLastname())).thenReturn(medicalrecordMock);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(), listOfPerson.get(1).getLastname())).thenReturn(medicalrecordMock1);
    	
    	List<FloodListDto> result = safetyAlertService.getListOfHomeDeservedByFirestation(stationMock);
    	
    	verify(firestationServiceMock).getAddressByFirestation(stationNumerMock);
    	verify(personServiceMock).getPersonPerAddress(listAddress);
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(), listOfPerson.get(0).getLastname());
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(), listOfPerson.get(1).getLastname());
    	assertThat(result).isEqualTo(listOfFloodList);
    }
    
}
