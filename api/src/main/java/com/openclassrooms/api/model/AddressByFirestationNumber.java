package com.openclassrooms.api.model;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
public class AddressByFirestationNumber {

    private String address;
    private String station;

    public AddressByFirestationNumber(String address, String station) {
        this.address = address;
        this.station = station;
    }

    public AddressByFirestationNumber() {
    }
}

