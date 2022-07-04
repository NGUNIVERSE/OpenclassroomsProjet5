package com.openclassrooms.api.service;

import com.openclassrooms.api.model.Medicalrecord;
import com.openclassrooms.api.repository.MedicalrecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class CalculateAgeService {

    private final MedicalrecordRepository medicalrecordRepository;


    public CalculateAgeService(MedicalrecordRepository medicalrecordRepository) {
        this.medicalrecordRepository = medicalrecordRepository;
    }

    @Autowired
    MedicalrecordService medicalrecordService;

   
    public int calculateAge(LocalDate date) {
        LocalDate now = LocalDate.now();
        return Period.between(date, now).getYears();
    }

    
    public boolean isChild(int age) {
        return age <= 18;
    }

    //@Override
        public boolean isChild(Medicalrecord medicalrecord) {
        if (medicalrecord == null) {
            return false;
        }
        int age = calculateAge(medicalrecord.getBirthdate());
        return isChild(age);
    }

    //@Override
    public boolean isAdult(Medicalrecord medicalrecord) {
        return !isChild(medicalrecord);
    }

   
    public boolean isAdult(int age) {
        return !isChild(age);
    }

}
