package com.openclassrooms.safetyAlertApi.dto;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
public class AddressByFirestationNumberDto {

    private String address;
    private String station;

    public AddressByFirestationNumberDto(String address, String station) {
        this.address = address;
        this.station = station;
    }

    public AddressByFirestationNumberDto() {
    }
}

