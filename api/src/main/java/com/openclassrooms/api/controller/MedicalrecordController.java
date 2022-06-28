	package com.openclassrooms.api.controller;

	import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.ResponseBody;
	import org.springframework.web.bind.annotation.RestController;

	import com.openclassrooms.api.model.Medicalrecord;
import com.openclassrooms.api.model.Person;
//import com.openclassrooms.api.repository.PersonRepository;
	import com.openclassrooms.api.service.MedicalrecordService;



	@RestController
	public class MedicalrecordController {

	    @Autowired
	    private MedicalrecordService medicalrecordService;

		/**
		 * Read - Get one medicalrecord
		 * @param id The id of the person
		 * @return A Medicalrecord object full filled
		 */
		@GetMapping("/medicalrecord/{id}")
		public Medicalrecord getMedicalrecord(@PathVariable("id") final Long id) {
			Optional<Medicalrecord> medicalrecord = medicalrecordService.getMedicalrecord(id);
			if(medicalrecord.isPresent()) {
				return medicalrecord.get();
			} else {
				return null;
			}
		}
	    /**
	    * Read - Get all medicalrecords
	    * @return - An Iterable object of Medicalrecord full filled
	    */
	    @GetMapping("/medicalrecords")
	    public Iterable<Medicalrecord> getMedicalrecords() {
	        return medicalrecordService.getMedicalrecords();
	    }
	    
		/**
		 * Create - Add a new medicalrecord
		 * @param medicalrecord An object medicalrecord
		 * @return The medicalrecord object saved
		 */
		@PostMapping("/medicalrecord")
		public Medicalrecord createMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
			return medicalrecordService.saveMedicalrecord(medicalrecord);
		}
		
		
		/**
		 * Delete - Delete a medicalrecord
		 * @param id - The id of the person to delete
		 */
		@DeleteMapping("/medicalrecord/{id}")
		public void deleteMedicalrecord(@PathVariable("id") final Long id) {
			medicalrecordService.deleteMedicalrecord(id);
		}
		
		/**
		 * Update - Update an existing medicarecord
		 * @param id - The id of the person to update
		 * @param person - The medicalrecord object updated
		 * @return
		 */
		@PutMapping("/medicalrecord/{id}")
		public Medicalrecord updateMedicalrecord(@PathVariable("id") final Long id, @RequestBody Medicalrecord medicalrecord) {
			Optional<Medicalrecord> e = medicalrecordService.getMedicalrecord(id);
			if(e.isPresent()) {
				Medicalrecord currentMedicalrecord = e.get();
				
				String firstname = medicalrecord.getFirstname();
				if(firstname != null) {
					currentMedicalrecord.setFirstname(firstname);
				}
				String lastname = medicalrecord.getLastname();
				if(lastname != null) {
					currentMedicalrecord.setLastname(lastname);
				}
				String birthdate = medicalrecord.getBirthdate();
				if(birthdate != null) {
					currentMedicalrecord.setBirthdate(birthdate);;
				}
				String medications = medicalrecord.getMedications();
				if(medications != null) {
					currentMedicalrecord.setMedications(medications);;
				}
				String allergies = medicalrecord.getAllergies();
				if(allergies != null) {
					currentMedicalrecord.setAllergies(allergies);;
				}	
				medicalrecordService.saveMedicalrecord(currentMedicalrecord);
				return currentMedicalrecord;
			} else {
				return null;
			}
		}

}