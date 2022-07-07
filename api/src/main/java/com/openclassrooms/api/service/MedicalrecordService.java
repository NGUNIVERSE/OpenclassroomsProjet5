
package com.openclassrooms.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.openclassrooms.api.model.Medicalrecord;
import com.openclassrooms.api.repository.MedicalrecordRepository;

import lombok.Data;




@Data
@Service
public class MedicalrecordService {

    @Autowired
    private MedicalrecordRepository medicalrecordRepository;

    public Optional<Medicalrecord> getMedicalrecord(final Long id) {
        return medicalrecordRepository.findById(id);
    }

    public Iterable<Medicalrecord> getMedicalrecords() {
        return medicalrecordRepository.findAll();
    }

    public void deleteMedicalrecord(final Long id) {
    	medicalrecordRepository.deleteById(id);
    }

    public Medicalrecord saveMedicalrecord(Medicalrecord medicalrecord) {
    	Medicalrecord savedMedicalrecord = medicalrecordRepository.save(medicalrecord);
        return savedMedicalrecord;
    }
    
   
	public Optional<Medicalrecord> updateMedicalrecord(final Long id, Medicalrecord medicalrecord) {
		Optional<Medicalrecord> e = medicalrecordRepository.findById(id);
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
			
			return Optional.of(medicalrecordRepository.save(currentMedicalrecord));
		
		} else {
			return (Optional.empty());
		}
	}
	public Medicalrecord findMedicalrecordByFirstnameAndLastname(String firstname, String lastname)
	{
		return medicalrecordRepository.findMedicalrecordByFirstnameAndLastname(firstname, lastname);
	}
}