
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

}