package com.openclassrooms.safetyAlertApi.dto;

import lombok.Data;

@Data
public class EmailDto {
	private String email;
	private String city;

	public EmailDto(String email, String city) {
		this.email = email;
		this.city = city;
	}

	public EmailDto() {
	}
}
