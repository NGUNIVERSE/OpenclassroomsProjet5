	package com.openclassrooms.safetyAlertApi.dto;
	//import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;
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
