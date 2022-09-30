package com.openclassrooms.safetyAlertApi.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyAlertApi.dto.ChildDto;
import com.openclassrooms.safetyAlertApi.dto.FloodListDto;
import com.openclassrooms.safetyAlertApi.dto.HomeMembresDto;
import com.openclassrooms.safetyAlertApi.dto.PersonCoveredDto;
import com.openclassrooms.safetyAlertApi.dto.PersonInfoDto;
import com.openclassrooms.safetyAlertApi.dto.PersonLivingAtAnAddressDto;
import com.openclassrooms.safetyAlertApi.model.Firestation;
import com.openclassrooms.safetyAlertApi.model.MedicalRecord;
import com.openclassrooms.safetyAlertApi.model.Person;

import lombok.Data;

@Data
@Service
public class SafetyAlertService {

	@Autowired
	private FirestationService firestationService;
	@Autowired
	private PersonService personService;
	@Autowired
	private MedicalRecordService medicalRecordService;

	public List<String> getPhoneNumberOfPersonByFirestation(String firestation) {
		List<String> addressByFirestation = firestationService.getAddressByFirestation(firestation);
		List<Person> personByAddress = personService.getPersonPerAddress(addressByFirestation);
		List<String> phoneNumberOfPerson = personService.getPhoneNumberOfPerson(personByAddress);
		return phoneNumberOfPerson;
	}

	public List<ChildDto> getChildListFromAnAddress(String address) {
		List<ChildDto> listOfChild = new ArrayList<>();
		List<Person> listOfPersonAtAnAddress = new ArrayList<>();
		List<HomeMembresDto> listOfHomeMembers = new ArrayList<>();
		listOfPersonAtAnAddress = personService.getPersonPerAddress(address);

		for (int i = 0; i < listOfPersonAtAnAddress.size(); i++) {

			HomeMembresDto homeMembresDto = new HomeMembresDto();
			homeMembresDto.setFirstname(listOfPersonAtAnAddress.get(i).getFirstname());
			homeMembresDto.setLastname(listOfPersonAtAnAddress.get(i).getLastname());
			listOfHomeMembers.add(homeMembresDto);

			MedicalRecord medicalRecord = medicalRecordService.findMedicalRecordByFirstnameAndLastname(
					listOfPersonAtAnAddress.get(i).getFirstname(), listOfPersonAtAnAddress.get(i).getLastname());

			long age = getAge(medicalRecord.getBirthdate());

			if (age <= 18) {

				ChildDto child = new ChildDto(listOfPersonAtAnAddress.get(i).getFirstname(),
						listOfPersonAtAnAddress.get(i).getLastname(), age, listOfHomeMembers);
				listOfChild.add(child);
			}
		}

		return listOfChild;
	}

	public long getAge(String birthDate) {
		long calculAge = 0;

		if (!birthDate.equals("TbD")) {
			LocalDate localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy").withLocale(Locale.FRENCH);

			LocalDate date = LocalDate.parse(birthDate, formatter);
			calculAge = Period.between(date, localDate).getYears();
		} else {
			return 0;
		}

		return calculAge;
	}

	public List<PersonCoveredDto> getPersonCoveredByFirestation(String station) {
		List<PersonCoveredDto> personCovered = new ArrayList<>();
		List<String> addressByFirestation = firestationService.getAddressByFirestation(station);
		List<Person> personByAddress = personService.getPersonPerAddress(addressByFirestation);

		MedicalRecord medicalRecord = new MedicalRecord();

		int i = 0;
		long age = 0;
		int nombreAdulte = 0;
		int nombreEnfant = 0;

		for (i = 0; i < personByAddress.size(); i++) {

			medicalRecord = medicalRecordService.findMedicalRecordByFirstnameAndLastname(
					personByAddress.get(i).getFirstname(), personByAddress.get(i).getLastname());

			age = getAge(medicalRecord.getBirthdate());
			PersonCoveredDto personCovereds = new PersonCoveredDto(personByAddress.get(i).getFirstname(),
					personByAddress.get(i).getLastname(), personByAddress.get(i).getAddress(),
					personByAddress.get(i).getPhone());

			if (age <= 18) {
				nombreEnfant++;
			} else {
				nombreAdulte++;
			}

			personCovered.add(personCovereds);
		}
		for (i = 0; i < personCovered.size(); i++) {
			personCovered.get(i).setNombreEnfant(nombreEnfant);
			personCovered.get(i).setNombreAdulte(nombreAdulte);
		}

		return personCovered;
	}

	public List<PersonLivingAtAnAddressDto> getPersonLivingAtAnAddress(String address) {
		List<PersonLivingAtAnAddressDto> listOfPersonLivingAtAnAddress = new ArrayList<>();
		List<Person> listOfPerson = personService.getPersonPerAddress(address);
		Firestation firestation = firestationService.getFirestationByAddress(address);
		MedicalRecord medicalRecord = new MedicalRecord();
		int i = 0;
		for (i = 0; i < listOfPerson.size(); i++) {

			medicalRecord = medicalRecordService.findMedicalRecordByFirstnameAndLastname(
					listOfPerson.get(i).getFirstname(), listOfPerson.get(i).getLastname());

			PersonLivingAtAnAddressDto personAtAnAddress = new PersonLivingAtAnAddressDto(
					listOfPerson.get(i).getFirstname(), listOfPerson.get(i).getLastname(),
					listOfPerson.get(i).getPhone(), getAge(medicalRecord.getBirthdate()),
					medicalRecord.getMedications(), medicalRecord.getAllergies(), firestation.getStation());
			listOfPersonLivingAtAnAddress.add(personAtAnAddress);
		}

		return listOfPersonLivingAtAnAddress;
	}

	public PersonInfoDto getInformationAboutPerson(String firstname, String lastname) {
		PersonInfoDto personInfo = new PersonInfoDto();
		Person person = new Person();
		MedicalRecord medicalRecord = new MedicalRecord();
		person = personService.getPersonByFirstnameAndLastname(firstname, lastname);
		medicalRecord = medicalRecordService.findMedicalRecordByFirstnameAndLastname(firstname, lastname);
		personInfo.setFirstname(person.getFirstname());
		personInfo.setLastname(person.getLastname());
		personInfo.setAddress(person.getAddress());
		personInfo.setAge(getAge(medicalRecord.getBirthdate()));
		personInfo.setEmail(person.getEmail());
		personInfo.setMedications(medicalRecord.getMedications());
		personInfo.setAllergies(medicalRecord.getAllergies());

		return personInfo;
	}

	public List<FloodListDto> getListOfHomeDeservedByFirestation(List<String> station) {

		List<FloodListDto> floodList = new ArrayList<>();

		int i = 0;
		int j = 0;
		MedicalRecord medicalRecord = new MedicalRecord();
		for (j = 0; j < station.size(); j++) {
			List<String> listOfFirestation = firestationService.getAddressByFirestation(station.get(j));
			List<Person> listOfPersonByAddress = personService.getPersonPerAddress(listOfFirestation);

			for (i = 0; i < listOfPersonByAddress.size(); i++) {
				FloodListDto flood = new FloodListDto();
				medicalRecord = medicalRecordService.findMedicalRecordByFirstnameAndLastname(
						listOfPersonByAddress.get(i).getFirstname(), listOfPersonByAddress.get(i).getLastname());
				flood.setAddress(listOfPersonByAddress.get(i).getAddress());
				flood.setFirstname(listOfPersonByAddress.get(i).getFirstname());
				flood.setLastname(listOfPersonByAddress.get(i).getLastname());
				flood.setPhoneNumber(listOfPersonByAddress.get(i).getPhone());
				flood.setAge(getAge(medicalRecord.getBirthdate()));
				flood.setMedications(medicalRecord.getMedications());
				flood.setAllergies(medicalRecord.getAllergies());
				floodList.add(flood);
			}
		}
		return floodList;
	}

}
