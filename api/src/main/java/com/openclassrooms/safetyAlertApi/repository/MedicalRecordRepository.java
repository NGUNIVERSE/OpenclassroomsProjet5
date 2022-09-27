
package com.openclassrooms.safetyAlertApi.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.openclassrooms.safetyAlertApi.model.Firestation;
import com.openclassrooms.safetyAlertApi.model.MedicalRecord;

@Service
public class MedicalRecordRepository {
	
	private Map<Long,MedicalRecord> medicalRecordById = new HashMap<>();

	public void deleteById(Long id) {
		
		medicalRecordById.remove(id);
	}

	public MedicalRecord save(MedicalRecord medicalRecord){
		
		if(medicalRecord.getId()==null)
		{
			Long newId = (long) medicalRecordById.size();
			while(medicalRecordById.containsKey(newId))
			{
				newId++;
			}
			
			medicalRecord.setId(newId);
		}
		
		medicalRecordById.put(medicalRecord.getId(),medicalRecord);
		
		return medicalRecord;
	}

	public Iterable<MedicalRecord> findAll(){
		
		return new ArrayList<>(medicalRecordById.values());
		
	}

	public Optional<MedicalRecord> findById(Long id){
		
		return Optional.ofNullable(medicalRecordById.get(id));
	}

	public List<MedicalRecord> saveAll(List<MedicalRecord> medicalRecords){
		
		if(!CollectionUtils.isEmpty(medicalRecords) )
		{
			medicalRecords.forEach(this::save);
		}
		
		return medicalRecords;
	}

	public MedicalRecord findMedicalRecordByFirstnameAndLastname(String firstname, String lastname){
		
		return medicalRecordById.values().stream()
				.filter(f->f.getFirstname().equals(firstname))
				.filter(f->f.getLastname().equals(lastname))
				.findFirst().orElse(null);
	}
}