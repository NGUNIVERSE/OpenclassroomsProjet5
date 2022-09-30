package com.openclassrooms.safetyAlertApi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.openclassrooms.safetyAlertApi.dto.EmailDto;
import com.openclassrooms.safetyAlertApi.model.Person;
import com.openclassrooms.safetyAlertApi.repository.PersonRepository;

public class PersonServiceTest {

	@Mock
	private PersonRepository personRepositoryMock;

	@InjectMocks
	private PersonService personService;

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

	private long id1 = 0;
	private long id2 = 1;
	private long id3 = 50;

	@Test
	public void getPersonTest() {
		Person personMock = mockPerson();
		Person personMock1 = mockPerson1();

		Optional<Person> personOptionalMock = Optional.ofNullable(personMock);
		Optional<Person> personOptionalMock1 = Optional.ofNullable(personMock1);

		when(personRepositoryMock.findById(id1)).thenReturn(personOptionalMock);
		when(personRepositoryMock.findById(id2)).thenReturn(personOptionalMock1);

		Optional<Person> result = personService.getPerson(id1);
		Optional<Person> result1 = personService.getPerson(id2);

		assertThat(result).isEqualTo(personOptionalMock);
		assertThat(result1).isEqualTo(personOptionalMock1);

		verify(personRepositoryMock).findById(id1);
		verify(personRepositoryMock).findById(id2);

	}

	@Test
	public void testGetPersons() throws Exception {

		Person personMock = mockPerson();
		Person personMock1 = mockPerson1();

		List<Person> listOfPerson = new ArrayList<>();
		listOfPerson.add(personMock);
		listOfPerson.add(personMock1);
		Iterable<Person> listOfPersonTest = listOfPerson;

		when(personRepositoryMock.findAll()).thenReturn(listOfPersonTest);

		Iterable<Person> result = personService.getPersons();

		assertThat(result).isEqualTo(listOfPersonTest);
		verify(personRepositoryMock).findAll();

	}

	@Test
	public void testDeletePerson() throws Exception {

		personService.deletePerson(id1);

		verify(personRepositoryMock).deleteById(id1);

	}

	@Test
	public void testGetPersonUnknownId() throws Exception {

		personService.deletePerson(id3);

		verify(personRepositoryMock).deleteById(id3);
	}

	@Test
	public void testSavePerson() throws Exception {

		Person personMock = mockPerson();
		Person result = new Person();
		when(personRepositoryMock.save(personMock)).thenReturn(personMock);

		result = personService.savePerson(personMock);

		verify(personRepositoryMock).save(personMock);
		assertThat(result).isEqualTo(personMock);

	}

	@Test
	public void updatePersonTest() throws Exception {

		Person personMock = mockPerson();

		Optional<Person> personOptionalMock = Optional.ofNullable(personMock);

		when(personRepositoryMock.findById(id1)).thenReturn(personOptionalMock);
		when(personRepositoryMock.save(personMock)).thenReturn(personMock);

		Optional<Person> result = personService.updatePerson(id1, personMock);

		verify(personRepositoryMock).findById(id1);
		verify(personRepositoryMock).save(personMock);
		assertThat(result).isEqualTo(personOptionalMock);

	}

	@Test
	public void updatePersonNotFoundTest() throws Exception {

		Person personMock = mockPerson();

		when(personRepositoryMock.findById(id3)).thenReturn(Optional.empty());

		Optional<Person> result = personService.updatePerson(id3, personMock);

		verify(personRepositoryMock).findById(id3);
		verify(personRepositoryMock, times(0)).save(personMock);
		assertThat(result).isEqualTo(Optional.empty());

	}

	@Test
	public void getEmailPerCityTest() throws Exception {

		List<EmailDto> listOfEmail = new ArrayList<>();
		String cityMock = "Culver";
		EmailDto emailMock = new EmailDto();
		emailMock.setCity("Culver");
		emailMock.setEmail("reg@email.com");
		EmailDto emailMock1 = new EmailDto();
		emailMock1.setCity("Culver");
		emailMock1.setEmail("jpeter@email.com");
		listOfEmail.add(emailMock);
		listOfEmail.add(emailMock1);

		Person personMock = mockPerson();
		Person personMock1 = mockPerson1();

		List<Person> listOfPerson = new ArrayList<>();
		listOfPerson.add(personMock);
		listOfPerson.add(personMock1);
		List<Person> listOfPersonTest = listOfPerson;

		when(personRepositoryMock.findPersonByCity(cityMock)).thenReturn(listOfPersonTest);

		List<EmailDto> result = personService.getEmailPerCity(cityMock);

		verify(personRepositoryMock).findPersonByCity(cityMock);
		assertThat(result).isEqualTo(listOfEmail);
	}

	@Test
	public void getPersonPerListOfAddressTest() throws Exception {
		Person personMock = mockPerson();
		Person personMock1 = mockPerson1();

		String addressMock = "908 73rd St";
		String addressMock1 = "112 Steppes Pl";

		List<String> listAddress = new ArrayList<>();
		listAddress.add(addressMock);
		listAddress.add(addressMock1);

		List<Person> listOfPerson1 = new ArrayList<>();
		listOfPerson1.add(personMock);
		List<Person> listOfPerson2 = new ArrayList<>();
		listOfPerson2.add(personMock1);
		List<Person> listOfPerson3 = new ArrayList<>();
		listOfPerson3.add(personMock);
		listOfPerson3.add(personMock1);

		when(personRepositoryMock.findPersonByAddress(listAddress.get(0))).thenReturn(listOfPerson1);
		when(personRepositoryMock.findPersonByAddress(listAddress.get(1))).thenReturn(listOfPerson2);

		List<Person> result = personService.getPersonPerAddress(listAddress);

		verify(personRepositoryMock).findPersonByAddress(listAddress.get(0));
		verify(personRepositoryMock).findPersonByAddress(listAddress.get(1));
		assertThat(result).isEqualTo(listOfPerson3);

	}

	@Test
	public void getPersonPerAddressTest() throws Exception {
		Person personMock = mockPerson();

		String addressMock = "908 73rd St";

		List<Person> listOfPerson = new ArrayList<>();
		listOfPerson.add(personMock);

		when(personRepositoryMock.findPersonByAddress(addressMock)).thenReturn(listOfPerson);

		List<Person> result = personService.getPersonPerAddress(addressMock);

		verify(personRepositoryMock).findPersonByAddress(addressMock);
		assertThat(result).isEqualTo(listOfPerson);

	}

	@Test
	public void getPhoneNumberOfPersonTest() {
		List<String> listOfPhoneNumber = new ArrayList<>();

		Person personMock = mockPerson();
		Person personMock1 = mockPerson1();

		String phone1 = "841-874-8547";
		String phone2 = "841-874-8888";
		listOfPhoneNumber.add(phone1);
		listOfPhoneNumber.add(phone2);

		List<Person> listOfPerson = new ArrayList<>();
		listOfPerson.add(personMock);
		listOfPerson.add(personMock1);

		List<String> result = personService.getPhoneNumberOfPerson(listOfPerson);

		assertThat(result).isEqualTo(listOfPhoneNumber);

	}

	@Test
	public void getPersonByFirstnameAndLastnameTest() {
		Person personMock = mockPerson();
		String firstname = "Reginold";
		String lastname = "Walker";

		when(personRepositoryMock.findPersonByFirstnameAndLastname(firstname, lastname)).thenReturn(personMock);

		Person result = personService.getPersonByFirstnameAndLastname(firstname, lastname);

		verify(personRepositoryMock).findPersonByFirstnameAndLastname(firstname, lastname);
		assertThat(result).isEqualTo(personMock);
	}

}
