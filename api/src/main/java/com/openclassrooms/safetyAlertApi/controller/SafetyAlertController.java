package com.openclassrooms.safetyAlertApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetyAlertApi.dto.ChildDto;
import com.openclassrooms.safetyAlertApi.dto.FloodListDto;
import com.openclassrooms.safetyAlertApi.dto.PersonCoveredDto;
import com.openclassrooms.safetyAlertApi.dto.PersonInfoDto;
import com.openclassrooms.safetyAlertApi.dto.PersonLivingAtAnAddressDto;
import com.openclassrooms.safetyAlertApi.service.SafetyAlertService;

@RestController
public class SafetyAlertController {

	@Autowired
	private SafetyAlertService safetyAlertService;

//		http://localhost:8080/phoneAlert?firestation=<firestation_number>
	@GetMapping("/phoneAlert")
	public List<String> listOfPhoneNumberOfPersonByFirestation(@RequestParam("firestation") final String firestation) {

		return safetyAlertService.getPhoneNumberOfPersonByFirestation(firestation);
	}

	// http://localhost:8080/childAlert?address=<address>
	@GetMapping("/childAlert")
	public List<ChildDto> listOfChildAtAnAddress(@RequestParam("address") final String address) {
		return safetyAlertService.getChildListFromAnAddress(address);

	}

	// http://localhost:8080/firestation?stationNumber=<station_number>
	@GetMapping("/firestation")
	public List<PersonCoveredDto> listOfPersonCoveredByFirestation(
			@RequestParam("stationNumber") final String station) {
		return safetyAlertService.getPersonCoveredByFirestation(station);

	}

	// http://localhost:8080/fire?address=<address>
	@GetMapping("/fire")
	public List<PersonLivingAtAnAddressDto> listOfPersonLivingAtAnAddress(
			@RequestParam("address") final String address) {
		return safetyAlertService.getPersonLivingAtAnAddress(address);
	}

	/*
	 * http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	 * Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les
	 * antécédents médicaux (médicaments, posologie, allergies) de chaque habitant.
	 * Si plusieurs personnes portent le même nom, elles doivent toutes apparaître.
	 */
	@GetMapping("/personInfo")
	public PersonInfoDto informationAboutPerson(@RequestParam("firstName") final String firstname,
			@RequestParam("lastName") final String lastname) {
		return safetyAlertService.getInformationAboutPerson(firstname, lastname);
	}

	/*
	 * http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	 * Cette url doit retourner une liste de tous les foyers desservis par la
	 * caserne. Cette liste doit regrouper les personnes par adresse. Elle doit
	 * aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et faire
	 * figurer leurs antécédents médicaux (médicaments, posologie et allergies) à
	 * côté de chaque nom.
	 */
	@GetMapping("/flood/stations")
	public List<FloodListDto> listOfHomeDeservedByFirestation(@RequestParam("stations") final List<String> stations) {
		return safetyAlertService.getListOfHomeDeservedByFirestation(stations); // mettre List String comme paramètre
	}
}
