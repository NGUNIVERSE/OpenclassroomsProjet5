package com.openclassrooms.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openclassrooms.api.model.AddressByFirestationNumber;
import com.openclassrooms.api.model.Firestation;
import com.openclassrooms.api.repository.FirestationRepository;

import lombok.Data;

@Data
@Service
public class FirestationService {

    @Autowired
    private FirestationRepository firestationRepository;

    public Optional<Firestation> getFirestation(final Long id) {
        return firestationRepository.findById(id);
    }

    public Iterable<Firestation> getFirestations() {
        return firestationRepository.findAll();
    }

    public void deleteFirestation(final Long id) {
    	firestationRepository.deleteById(id);
    }

    public Firestation saveFirestation(Firestation firestation) {
    	Firestation savedFirestation = firestationRepository.save(firestation);
        return savedFirestation;
    }
    

	public Optional<Firestation> updateFirestation(final Long id, Firestation firestation) {
		Optional<Firestation> e = firestationRepository.findById(id);
		if(e.isPresent()) {
			Firestation currentFirestation = e.get();
			
			String address = firestation.getAddress();
			if(address != null) {
				currentFirestation.setAddress(address);
			}
			String station = firestation.getStation();
			if(station != null) {
				currentFirestation.setStation(station);
			}
			
			return Optional.of(firestationRepository.save(currentFirestation));
		} else {
			return (Optional.empty());
		}
	}
	
	public List<String> getAddressByFirestation(String station){
		
		List<String> listOfAddressByFirestationNumber = new ArrayList<>();
		//List<Firestation> listOfAddressByFirestationNumber = new ArrayList<>();
		
		Iterable<Firestation> firestation = firestationRepository.findByStation(station);
		for(Firestation Firestation : firestation)
		{
			String addressByFirestationNumber = new String();
			addressByFirestationNumber = Firestation.getAddress();
		//	addressByFirestationNumber.setStation(Firestation.getStation());
			listOfAddressByFirestationNumber.add(addressByFirestationNumber);
		}
		
		return listOfAddressByFirestationNumber;
	}
	
	public Firestation getFirestationByAddress(String address)
	{
		Firestation firestation = firestationRepository.findFirestationByAddress(address);

		return firestation;
	}
}
