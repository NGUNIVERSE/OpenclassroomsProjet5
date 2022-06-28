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
	import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.model.Person;
import com.openclassrooms.api.repository.FirestationRepository;
	import com.openclassrooms.api.service.FirestationService;

	@RestController
	public class FirestationController {


	    @Autowired
	    private FirestationService firestationService;

	    
	    
		/**
		 * Read - Get one firestation 
		 * @param id The id of the firestation
		 * @return A Firestation object full filled
		 */
	/*	@GetMapping("/firestation")
		public ResponseEntity<Firestation> getFirestationStationNumber(@RequestParam("stationNumber") final Long stationNumber) {
	
				return ResponseEntity.of(firestationService.getFirestation(id));
	
		} */
	    
	    

		/**
		 * Read - Get one firestation 
		 * @param id The id of the firestation
		 * @return A Firestation object full filled
		 */
		@GetMapping("/firestation/{id}")
		public ResponseEntity<Firestation> getFirestation(@PathVariable("id") final Long id) {
	
				return ResponseEntity.of(firestationService.getFirestation(id));
	
		}
		
	    /**
	    * Read - Get all firestations
	    * @return - An Iterable object of firestation full filled
	    */
	    @GetMapping("/firestations")
	    public Iterable<Firestation> getFirestations() {
	        return firestationService.getFirestations();
	       
	    }
	    
	    
		/**
		 * Create - Add a new firestation
		 * @param firestation An object firestation
		 * @return The firestation object saved
		 */
		@PostMapping("/firestation")
		public ResponseEntity<Firestation> createFirestation(@RequestBody Firestation firestation) {
			return ResponseEntity.status(HttpStatus.CREATED).body(firestationService.saveFirestation(firestation));

		}
		
		/**
		 * Delete - Delete a firestation
		 * @param id - The id of the firestation to delete
		 */
		@ResponseStatus(value = HttpStatus.NO_CONTENT)
		@DeleteMapping("/firestation/{id}")
		public void deleteFirestation(@PathVariable("id") final Long id) {
			firestationService.deleteFirestation(id);
		}
		
		/**
		 * Update - Update an existing firestation
		 * @param id - The id of the pfirestation to update
		 * @param firestation - The firestation object updated
		 * @return
		 */
		@PutMapping("/firestation/{id}")
		public ResponseEntity<Firestation> updateFirestation(@PathVariable("id") final Long id, @RequestBody Firestation firestation) {
			
			return ResponseEntity.of(firestationService.updateFirestation(id , firestation));
		}

}


