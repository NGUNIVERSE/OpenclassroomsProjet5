package com.openclassrooms.safetyAlertApi.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.openclassrooms.safetyAlertApi.model.Firestation;

@Service
public class FirestationRepository {

	private Map<Long, Firestation> firestationById = new HashMap<>();

	public void deleteById(Long id) {

		firestationById.remove(id);
	}

	public Firestation save(Firestation firestation) {

		if (firestation.getId() == null) {
			Long newId = (long) firestationById.size();
			while (firestationById.containsKey(newId)) {
				newId++;
			}

			firestation.setId(newId);
		}

		firestationById.put(firestation.getId(), firestation);

		return firestation;
	}

	public Iterable<Firestation> findAll() {

		return new ArrayList<>(firestationById.values());

	}

	public Optional<Firestation> findById(Long id) {

		return Optional.ofNullable(firestationById.get(id));
	}

	public List<Firestation> saveAll(List<Firestation> firestations) {

		if (!CollectionUtils.isEmpty(firestations)) {
			firestations.forEach(this::save);
		}

		return firestations;
	}

	public List<Firestation> findByStation(String station) {

		return firestationById.values().stream().filter(f -> f.getStation().equals(station))
				.collect(Collectors.toList());
	}

	public Firestation findFirestationByAddress(String address) {

		return firestationById.values().stream().filter(f -> f.getAddress().equals(address)).findFirst().orElse(null);
	}
}