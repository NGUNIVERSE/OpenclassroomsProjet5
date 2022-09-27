
package com.openclassrooms.safetyAlertApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.safetyAlertApi.model.MedicalRecord;
import com.openclassrooms.safetyAlertApi.repository.MedicalRecordRepository;

import lombok.Data;

@Data
@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	public Optional<MedicalRecord> getMedicalRecord(final Long id) {
		return medicalRecordRepository.findById(id);
	}

	public Iterable<MedicalRecord> getMedicalRecords() {
		return medicalRecordRepository.findAll();
	}

	public void deleteMedicalRecord(final Long id) {
		medicalRecordRepository.deleteById(id);
	}

	public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord savedMedicalRecord = medicalRecordRepository.save(medicalRecord);
		return savedMedicalRecord;
	}

	public Optional<MedicalRecord> updateMedicalRecord(final Long id, MedicalRecord medicalRecord) {
		Optional<MedicalRecord> e = medicalRecordRepository.findById(id);
		if (e.isPresent()) {
			MedicalRecord currentMedicalRecord = e.get();

			String firstname = medicalRecord.getFirstname();
			if (firstname != null) {
				currentMedicalRecord.setFirstname(firstname);
			}
			String lastname = medicalRecord.getLastname();
			if (lastname != null) {
				currentMedicalRecord.setLastname(lastname);
			}
			String birthdate = medicalRecord.getBirthdate();
			if (birthdate != null) {
				currentMedicalRecord.setBirthdate(birthdate);
				;
			}
			List<String> medications = medicalRecord.getMedications();
			if (medications != null) {
				currentMedicalRecord.setMedications(medications);
				;
			}
			List<String> allergies = medicalRecord.getAllergies();
			if (allergies != null) {
				currentMedicalRecord.setAllergies(allergies);
				;
			}

			return Optional.of(medicalRecordRepository.save(currentMedicalRecord));

		} else {
			return (Optional.empty());
		}
	}

	public MedicalRecord findMedicalRecordByFirstnameAndLastname(String firstname, String lastname) {
		return medicalRecordRepository.findMedicalRecordByFirstnameAndLastname(firstname, lastname);
	}

}