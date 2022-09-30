package com.openclassrooms.safetyAlertApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.safetyAlertApi.model.MedicalRecord;
import com.openclassrooms.safetyAlertApi.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;

	/**
	 * Read - Get one medicalRecord
	 * 
	 * @param id The id of the person
	 * @return A MedicalRecord object full filled
	 */
	@GetMapping("/medicalRecord/{id}")
	public ResponseEntity<MedicalRecord> getMedicalRecord(@PathVariable("id") final Long id) {

		return ResponseEntity.of(medicalRecordService.getMedicalRecord(id));

	}

	/**
	 * Read - Get all medicalRecords
	 * 
	 * @return - An Iterable object of MedicalRecord full filled
	 */
	@GetMapping("/medicalRecords")
	public Iterable<MedicalRecord> getMedicalRecords() {
		return medicalRecordService.getMedicalRecords();
	}

	/**
	 * Create - Add a new medicalRecord
	 * 
	 * @param medicalRecord An object medicalRecord
	 * @return The medicalRecord object saved
	 */
	@PostMapping("/medicalRecord")
	public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		return ResponseEntity.status(HttpStatus.CREATED).body(medicalRecordService.saveMedicalRecord(medicalRecord));
	}

	/**
	 * Delete - Delete a medicalRecord
	 * 
	 * @param id - The id of the person to delete
	 */
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/medicalRecord/{id}")
	public void deleteMedicalRecord(@PathVariable("id") final Long id) {
		medicalRecordService.deleteMedicalRecord(id);
	}

	/**
	 * Update - Update an existing medicalRecord
	 * 
	 * @param id     - The id of the person to update
	 * @param person - The medicalRecord object updated
	 * @return
	 */
	@PutMapping("/medicalRecord/{id}")
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable("id") final Long id,
			@RequestBody MedicalRecord medicalRecord) {

		return ResponseEntity.of(medicalRecordService.updateMedicalRecord(id, medicalRecord));
	}

}