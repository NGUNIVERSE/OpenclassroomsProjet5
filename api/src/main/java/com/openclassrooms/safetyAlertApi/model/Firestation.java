package com.openclassrooms.safetyAlertApi.model;

import lombok.Data;

@Data
public class Firestation {

	private Long id;

	private String address;

	private String station;

	public Firestation(Long id, String address, String station) {

		this.id = id;
		this.address = address;
		this.station = station;
	}

	public Firestation() {

	}

}
