
package com.openclassrooms.safetyAlertApi.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.openclassrooms.safetyAlertApi.model.Firestation;
import com.openclassrooms.safetyAlertApi.model.Medicalrecord;

@Service
public class MedicalrecordRepository {
	
	private Map<Long,Medicalrecord> medicalrecordById = new HashMap<>();

	public void deleteById(Long id) {
		
		medicalrecordById.remove(id);
	}

	public Medicalrecord save(Medicalrecord medicalrecord){
		
		if(medicalrecord.getId()==null)
		{
			Long newId = (long) medicalrecordById.size();
			while(medicalrecordById.containsKey(newId))
			{
				newId++;
			}
			
			medicalrecord.setId(newId);
		}
		
		medicalrecordById.put(medicalrecord.getId(),medicalrecord);
		
		return medicalrecord;
	}

	public Iterable<Medicalrecord> findAll(){
		
		return new ArrayList<>(medicalrecordById.values());
		
	}

	public Optional<Medicalrecord> findById(Long id){
		
		return Optional.ofNullable(medicalrecordById.get(id));
	}

	public List<Medicalrecord> saveAll(List<Medicalrecord> medicalrecords){
		
		if(!CollectionUtils.isEmpty(medicalrecords) )
		{
			medicalrecords.forEach(this::save);
		}
		
		return medicalrecords;
	}

	public Medicalrecord findMedicalrecordByFirstnameAndLastname(String firstname, String lastname){
		
		return medicalrecordById.values().stream()
				.filter(f->f.getFirstname().equals(firstname))
				.filter(f->f.getLastname().equals(lastname))
				.findFirst().orElse(null);
	}
}