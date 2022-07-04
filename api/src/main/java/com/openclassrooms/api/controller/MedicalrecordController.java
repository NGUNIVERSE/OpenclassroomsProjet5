	package com.openclassrooms.api.controller;
	import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
	import com.openclassrooms.api.model.Medicalrecord;
import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.repository.PersonRepository;
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
		public ResponseEntity<Medicalrecord> getMedicalrecord(@PathVariable("id") final Long id) {
	
			return ResponseEntity.of(medicalrecordService.getMedicalrecord(id));

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
		public ResponseEntity<Medicalrecord> createMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
			return ResponseEntity.status(HttpStatus.CREATED).body(medicalrecordService.saveMedicalrecord(medicalrecord));
		}
		
		/**
		 * Delete - Delete a medicalrecord
		 * @param id - The id of the person to delete
		 */
		@ResponseStatus(value = HttpStatus.NO_CONTENT)
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
		public ResponseEntity<Medicalrecord> updateMedicalrecord(@PathVariable("id") final Long id, @RequestBody Medicalrecord medicalrecord) {
		
			return ResponseEntity.of(medicalrecordService.updateMedicalrecord(id , medicalrecord));
		}
		
}