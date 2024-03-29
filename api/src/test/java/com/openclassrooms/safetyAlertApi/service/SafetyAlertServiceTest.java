package com.openclassrooms.safetyAlertApi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.openclassrooms.safetyAlertApi.dto.ChildDto;
import com.openclassrooms.safetyAlertApi.dto.FloodListDto;
import com.openclassrooms.safetyAlertApi.dto.HomeMembresDto;
import com.openclassrooms.safetyAlertApi.dto.PersonCoveredDto;
import com.openclassrooms.safetyAlertApi.dto.PersonInfoDto;
import com.openclassrooms.safetyAlertApi.dto.PersonLivingAtAnAddressDto;
import com.openclassrooms.safetyAlertApi.model.Firestation;
import com.openclassrooms.safetyAlertApi.model.MedicalRecord;
import com.openclassrooms.safetyAlertApi.model.Person;

public class SafetyAlertServiceTest {

	@Mock
	private FirestationService firestationServiceMock;

	@Mock
	private PersonService personServiceMock;

	@Mock
	private MedicalRecordService medicalRecordServiceMock;

	@InjectMocks
	private SafetyAlertService safetyAlertService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
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

	private Person mockPerson2() {
		Person personMock2 = new Person();
		personMock2.setFirstname("Junior");
		personMock2.setLastname("Junior");
		personMock2.setAddress("112 Steppes Pl");
		personMock2.setCity("Culver");
		personMock2.setZip("97451");
		personMock2.setPhone("841-874-8888");
		personMock2.setEmail("junior@email.com");
		return personMock2;
	}

	private MedicalRecord mockMedicalRecord() {

		long age = 42;
		LocalDate localDate = LocalDate.now().minusYears(age);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		String dateMock = localDate.format(formatter);

		MedicalRecord medicalRecordMock = new MedicalRecord();
		medicalRecordMock.setFirstname("Reginold");
		medicalRecordMock.setLastname("Walker");
		medicalRecordMock.setBirthdate(dateMock);
		medicalRecordMock.setMedications(List.of("thradox:700mg"));
		medicalRecordMock.setAllergies(List.of("illisoxian"));

		;
		return medicalRecordMock;
	}

	private MedicalRecord mockMedicalRecord1() {

		long age = 57;
		LocalDate localDate = LocalDate.now().minusYears(age);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		String dateMock = localDate.format(formatter);

		MedicalRecord medicalRecordMock1 = new MedicalRecord();
		medicalRecordMock1.setFirstname("Ron");
		medicalRecordMock1.setLastname("Peters");
		medicalRecordMock1.setBirthdate(dateMock);
		medicalRecordMock1.setMedications(List.of(""));
		medicalRecordMock1.setAllergies(List.of(""));
		return medicalRecordMock1;
	}

	private MedicalRecord mockMedicalRecord2() {

		long age = 2;
		LocalDate localDate = LocalDate.now().minusYears(age);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		String dateMock = localDate.format(formatter);

		MedicalRecord medicalRecordMock2 = new MedicalRecord();
		medicalRecordMock2.setFirstname("Junior");
		medicalRecordMock2.setLastname("Junior");
		medicalRecordMock2.setBirthdate(dateMock);
		medicalRecordMock2.setMedications(List.of("thradox:700mg"));
		medicalRecordMock2.setAllergies(List.of("illisoxian"));
		return medicalRecordMock2;
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
	public void getChildListFromAnAddressTest() throws Exception {

		Person personMock = mockPerson();
		Person personMock1 = mockPerson1();
		Person personMock2 = mockPerson2();

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

		MedicalRecord medicalRecordMock = mockMedicalRecord();
		MedicalRecord medicalRecordMock1 = mockMedicalRecord1();
		MedicalRecord medicalRecordMock2 = mockMedicalRecord2();

		ChildDto childMock = new ChildDto("Junior", "Junior", 2, listOfHomeMembres);

		List<ChildDto> listOfChild = new ArrayList<>();
		listOfChild.add(childMock);

		when(personServiceMock.getPersonPerAddress(addressMock)).thenReturn(listOfPerson);
		when(medicalRecordServiceMock.findMedicalRecordByFirstnameAndLastname(personMock.getFirstname(),
				personMock.getLastname())).thenReturn(medicalRecordMock);
		when(medicalRecordServiceMock.findMedicalRecordByFirstnameAndLastname(personMock1.getFirstname(),
				personMock1.getLastname())).thenReturn(medicalRecordMock1);
		when(medicalRecordServiceMock.findMedicalRecordByFirstnameAndLastname(personMock2.getFirstname(),
				personMock2.getLastname())).thenReturn(medicalRecordMock2);

		List<ChildDto> result = safetyAlertService.getChildListFromAnAddress(addressMock);

		verify(personServiceMock).getPersonPerAddress(addressMock);
		verify(medicalRecordServiceMock).findMedicalRecordByFirstnameAndLastname(personMock.getFirstname(),
				personMock.getLastname());
		verify(medicalRecordServiceMock).findMedicalRecordByFirstnameAndLastname(personMock1.getFirstname(),
				personMock1.getLastname());
		verify(medicalRecordServiceMock).findMedicalRecordByFirstnameAndLastname(personMock2.getFirstname(),
				personMock2.getLastname());

		assertThat(result).isEqualTo(listOfChild);
	}

	@Test
	public void getAgeTest() throws Exception {
		long age = 2;
		LocalDate localDate = LocalDate.now().minusYears(age);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		String dateMock = localDate.format(formatter);

		long result = safetyAlertService.getAge(dateMock);

		assertThat(result).isEqualTo(age);

	}

	@Test
	public void getPersonCoveredByFirestationTest() throws Exception {

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

		MedicalRecord medicalRecordMock = mockMedicalRecord();
		MedicalRecord medicalRecordMock1 = mockMedicalRecord1();

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
		when(medicalRecordServiceMock.findMedicalRecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(),
				listOfPerson.get(0).getLastname())).thenReturn(medicalRecordMock);
		when(medicalRecordServiceMock.findMedicalRecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(),
				listOfPerson.get(1).getLastname())).thenReturn(medicalRecordMock1);

		List<PersonCoveredDto> result = safetyAlertService.getPersonCoveredByFirestation(stationMock);

		verify(firestationServiceMock).getAddressByFirestation(stationMock);
		verify(personServiceMock).getPersonPerAddress(listAddress);
		verify(medicalRecordServiceMock).findMedicalRecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(),
				listOfPerson.get(0).getLastname());
		verify(medicalRecordServiceMock).findMedicalRecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(),
				listOfPerson.get(1).getLastname());
		assertThat(result).isEqualTo(listOfPersonCovered);
	}

	@Test
	public void getPersonLivingAtAnAddressTest() throws Exception {

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

		MedicalRecord medicalRecordMock = mockMedicalRecord();
		MedicalRecord medicalRecordMock1 = mockMedicalRecord1();

		String addressMock = "Roissy";
		List<PersonLivingAtAnAddressDto> listOfPersonLivingAtAnAddress = new ArrayList<>();
		listOfPersonLivingAtAnAddress.add(personLivingAtAnAddressMock);
		listOfPersonLivingAtAnAddress.add(personLivingAtAnAddressMock1);

		when(personServiceMock.getPersonPerAddress(addressMock)).thenReturn(listOfPerson);
		when(firestationServiceMock.getFirestationByAddress(addressMock)).thenReturn(firestationMock);
		when(medicalRecordServiceMock.findMedicalRecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(),
				listOfPerson.get(0).getLastname())).thenReturn(medicalRecordMock);
		when(medicalRecordServiceMock.findMedicalRecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(),
				listOfPerson.get(1).getLastname())).thenReturn(medicalRecordMock1);

		List<PersonLivingAtAnAddressDto> result = safetyAlertService.getPersonLivingAtAnAddress(addressMock);

		verify(personServiceMock).getPersonPerAddress(addressMock);
		verify(firestationServiceMock).getFirestationByAddress(addressMock);
		verify(medicalRecordServiceMock).findMedicalRecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(),
				listOfPerson.get(0).getLastname());
		verify(medicalRecordServiceMock).findMedicalRecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(),
				listOfPerson.get(1).getLastname());
		assertThat(result).isEqualTo(listOfPersonLivingAtAnAddress);

	}

	@Test
	public void getInformationAboutPersonTest() throws Exception {

		PersonInfoDto personInfoMock = new PersonInfoDto();
		personInfoMock.setFirstname("Reginold");
		personInfoMock.setLastname("Walker");
		personInfoMock.setAddress("908 73rd St");
		personInfoMock.setAge(42);
		personInfoMock.setMedications(List.of("thradox:700mg"));
		personInfoMock.setAllergies(List.of("illisoxian"));
		personInfoMock.setEmail("reg@email.com");

		Person personMock = mockPerson();
		MedicalRecord medicalRecordMock = mockMedicalRecord();

		String firstnameMock = "Reginold";
		String lastnameMock = "Walker";

		when(personServiceMock.getPersonByFirstnameAndLastname(firstnameMock, lastnameMock)).thenReturn(personMock);
		when(medicalRecordServiceMock.findMedicalRecordByFirstnameAndLastname(firstnameMock, lastnameMock))
				.thenReturn(medicalRecordMock);

		PersonInfoDto result = safetyAlertService.getInformationAboutPerson(firstnameMock, lastnameMock);

		verify(personServiceMock).getPersonByFirstnameAndLastname(firstnameMock, lastnameMock);
		verify(medicalRecordServiceMock).findMedicalRecordByFirstnameAndLastname(firstnameMock, lastnameMock);
		assertThat(result).isEqualTo(personInfoMock);
	}

	@Test
	public void getListOfHomeDeservedByFirestationTest() throws Exception {

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

		MedicalRecord medicalRecordMock = mockMedicalRecord();
		MedicalRecord medicalRecordMock1 = mockMedicalRecord1();

		when(firestationServiceMock.getAddressByFirestation(stationNumerMock)).thenReturn(listAddress);
		when(personServiceMock.getPersonPerAddress(listAddress)).thenReturn(listOfPerson);
		when(medicalRecordServiceMock.findMedicalRecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(),
				listOfPerson.get(0).getLastname())).thenReturn(medicalRecordMock);
		when(medicalRecordServiceMock.findMedicalRecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(),
				listOfPerson.get(1).getLastname())).thenReturn(medicalRecordMock1);

		List<FloodListDto> result = safetyAlertService.getListOfHomeDeservedByFirestation(stationMock);

		verify(firestationServiceMock).getAddressByFirestation(stationNumerMock);
		verify(personServiceMock).getPersonPerAddress(listAddress);
		verify(medicalRecordServiceMock).findMedicalRecordByFirstnameAndLastname(listOfPerson.get(0).getFirstname(),
				listOfPerson.get(0).getLastname());
		verify(medicalRecordServiceMock).findMedicalRecordByFirstnameAndLastname(listOfPerson.get(1).getFirstname(),
				listOfPerson.get(1).getLastname());
		assertThat(result).isEqualTo(listOfFloodList);
	}

}
