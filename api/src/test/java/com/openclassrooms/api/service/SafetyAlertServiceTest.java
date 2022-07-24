package com.openclassrooms.api.service;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.api.model.Child;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.model.FloodList;
import com.openclassrooms.api.model.Medicalrecord;
import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.model.PersonCovered;
import com.openclassrooms.api.model.PersonInfo;
import com.openclassrooms.api.model.PersonLivingAtAnAddress;

@SpringBootTest
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

private Medicalrecord mockMedicalrecord2() {   	
	Medicalrecord medicalrecordMock2 = new Medicalrecord();
	medicalrecordMock2.setFirstname("Junior");
	medicalrecordMock2.setLastname("Junior");
	medicalrecordMock2.setBirthdate("01/01/2020");
	medicalrecordMock2.setMedications("thradox:700mg");
	medicalrecordMock2.setAllergies("illisoxian");
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
    	
    	Medicalrecord medicalrecordMock = mockMedicalrecord();
    	Medicalrecord medicalrecordMock1 = mockMedicalrecord1();
    	Medicalrecord medicalrecordMock2 = mockMedicalrecord2();
    	
    	Child childMock = new Child("Junior","Junior",2,listOfPerson);

    	List<Child> listOfChild = new ArrayList<>();
    	listOfChild.add(childMock);

    	when(personServiceMock.getPersonPerAddress(addressMock)).thenReturn(listOfPerson);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(personMock.getFirstname(), personMock.getLastname())).thenReturn(medicalrecordMock);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(personMock1.getFirstname(), personMock1.getLastname())).thenReturn(medicalrecordMock1);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(personMock2.getFirstname(), personMock2.getLastname())).thenReturn(medicalrecordMock2);
   // 	when(safetyAlertServiceMock.getAge("01/01/2020")).thenReturn(age);
    	
    	List<Child> result = safetyAlertService.getChildListFromAnAddress(addressMock);	
    	
    	verify(personServiceMock).getPersonPerAddress(addressMock);
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(personMock.getFirstname(), personMock.getLastname());
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(personMock1.getFirstname(), personMock1.getLastname());
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(personMock2.getFirstname(), personMock2.getLastname());
  //  	verify(safetyAlertServiceMock).getAge("01/01/2020"); // n'est pas invoqué probleme pour le calcul de l'age (répétabilité du test unitaire)
    	
    	assertThat(result).isEqualTo(listOfChild);
    }
@Test
public void getAgeTest() throws Exception
{
	String dateMock ="01/01/2020";
	long age = 2;
	long result = safetyAlertService.getAge(dateMock);
	
	assertThat(result).isEqualTo(age);    // revoir ce test même probleme que précédemment sur la répétabilité du test dans le temps
		
}
	  
    @Test
    public void getPersonCoveredByFirestationTest() throws Exception{
    	
      	PersonCovered personCoveredMock = new PersonCovered();
      	personCoveredMock.setFirstname("Reginold");
    	personCoveredMock.setLastname("Walker");
    	personCoveredMock.setAddress("908 73rd St");
    	personCoveredMock.setPhoneNumber("841-874-8547");
    	personCoveredMock.setNombreEnfant(0);
    	personCoveredMock.setNombreAdulte(2);
      	PersonCovered personCoveredMock1 = new PersonCovered();
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
    	List<PersonCovered> listOfPersonCovered = new ArrayList<>();
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
    	
    	List<PersonCovered> result = safetyAlertService.getPersonCoveredByFirestation(stationMock);
    	
    	verify(firestationServiceMock).getAddressByFirestation(stationMock);
    	verify(personServiceMock).getPersonPerAddress(listAddress);
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(), listOfPerson.get(0).getLastname());
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(), listOfPerson.get(1).getLastname());
    	assertThat(result).isEqualTo(listOfPersonCovered);
    }
  
  
    @Test
    public void getPersonLivingAtAnAddressTest() throws Exception{
    	
    	PersonLivingAtAnAddress personLivingAtAnAddressMock = new PersonLivingAtAnAddress();
    	personLivingAtAnAddressMock.setFirstname("Reginold");
    	personLivingAtAnAddressMock.setLastname("Walker");
    	personLivingAtAnAddressMock.setPhoneNumber("841-874-8547");
    	personLivingAtAnAddressMock.setAge(42);
    	personLivingAtAnAddressMock.setMedications("thradox:700mg");
    	personLivingAtAnAddressMock.setAllergies("illisoxian");
    	personLivingAtAnAddressMock.setFirestationNumber("3");
    	
    	PersonLivingAtAnAddress personLivingAtAnAddressMock1 = new PersonLivingAtAnAddress();
    	personLivingAtAnAddressMock1.setFirstname("Ron");
    	personLivingAtAnAddressMock1.setLastname("Peters");
    	personLivingAtAnAddressMock1.setPhoneNumber("841-874-8888");
    	personLivingAtAnAddressMock1.setAge(57);
    	personLivingAtAnAddressMock1.setMedications("");
    	personLivingAtAnAddressMock1.setAllergies("");
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
    	List<PersonLivingAtAnAddress> listOfPersonLivingAtAnAddress = new ArrayList<>();
    	listOfPersonLivingAtAnAddress.add(personLivingAtAnAddressMock);
    	listOfPersonLivingAtAnAddress.add(personLivingAtAnAddressMock1);
    		 
    	
       	when(personServiceMock.getPersonPerAddress(addressMock)).thenReturn(listOfPerson);
       	when(firestationServiceMock.getFirestationByAddress(addressMock)).thenReturn(firestationMock);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(), listOfPerson.get(0).getLastname())).thenReturn(medicalrecordMock);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(), listOfPerson.get(1).getLastname())).thenReturn(medicalrecordMock1);

    	List<PersonLivingAtAnAddress> result = safetyAlertService.getPersonLivingAtAnAddress(addressMock);

    	verify(personServiceMock).getPersonPerAddress(addressMock);
      	verify(firestationServiceMock).getFirestationByAddress(addressMock);
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(), listOfPerson.get(0).getLastname());
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(), listOfPerson.get(1).getLastname());
    	assertThat(result).isEqualTo(listOfPersonLivingAtAnAddress);
    	
    }
    
  
    @Test
    public void getInformationAboutPersonTest() throws Exception{
    	
    	PersonInfo personInfoMock = new PersonInfo();
    	personInfoMock.setFirstname("Reginold");
    	personInfoMock.setLastname("Walker");
    	personInfoMock.setAddress("908 73rd St");
    	personInfoMock.setAge(42);
    	personInfoMock.setMedications("thradox:700mg");
    	personInfoMock.setAllergies("illisoxian");
    	personInfoMock.setEmail("reg@email.com");
    	    	
    	Person personMock = mockPerson();
    	Medicalrecord medicalrecordMock = mockMedicalrecord();
    	
    	String firstnameMock = "Reginold";
    	String lastnameMock = "Walker";
    		
    	when(personServiceMock.getPersonByFirstnameAndLastname(firstnameMock, lastnameMock)).thenReturn(personMock);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(firstnameMock, lastnameMock)).thenReturn(medicalrecordMock);
    	
    	PersonInfo result = safetyAlertService.getInformationAboutPerson(firstnameMock, lastnameMock);
    	
    	verify(personServiceMock).getPersonByFirstnameAndLastname(firstnameMock, lastnameMock);
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(firstnameMock, lastnameMock);
    	assertThat(result).isEqualTo(personInfoMock);
    }
    
    
    @Test
    public void getListOfHomeDeservedByFirestationTest() throws Exception{
    	
    	
    	FloodList floodListMock = new FloodList();
    	floodListMock.setFirstname("Reginold");
    	floodListMock.setLastname("Walker");
    	floodListMock.setPhoneNumber("841-874-8547");
    	floodListMock.setAge(42);
    	floodListMock.setMedications("thradox:700mg");
    	floodListMock.setAllergies("illisoxian");
    	floodListMock.setAddress("908 73rd St");
    	
    	FloodList floodListMock1 = new FloodList();
    	floodListMock1.setFirstname("Ron");
    	floodListMock1.setLastname("Peters");
    	floodListMock1.setPhoneNumber("841-874-8888");
    	floodListMock1.setAge(57);
    	floodListMock1.setMedications("");
    	floodListMock1.setAllergies("");
    	floodListMock1.setAddress("112 Steppes Pl");
    	    	
    	String stationMock = "1";
    	List<FloodList> listOfFloodList = new ArrayList<>();
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
    		
    	when(firestationServiceMock.getAddressByFirestation(stationMock)).thenReturn(listAddress);
    	when(personServiceMock.getPersonPerAddress(listAddress)).thenReturn(listOfPerson);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(), listOfPerson.get(0).getLastname())).thenReturn(medicalrecordMock);
    	when(medicalrecordServiceMock.findMedicalrecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(), listOfPerson.get(1).getLastname())).thenReturn(medicalrecordMock1);
    	
    	List<FloodList> result = safetyAlertService.getListOfHomeDeservedByFirestation(stationMock);
    	
    	verify(firestationServiceMock).getAddressByFirestation(stationMock);
    	verify(personServiceMock).getPersonPerAddress(listAddress);
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(), listOfPerson.get(0).getLastname());
    	verify(medicalrecordServiceMock).findMedicalrecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(), listOfPerson.get(1).getLastname());
    	assertThat(result).isEqualTo(listOfFloodList);
    }
    
}
