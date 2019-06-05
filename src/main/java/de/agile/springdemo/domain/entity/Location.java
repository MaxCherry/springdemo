package de.agile.springdemo.domain.entity;

import lombok.Data;

@Data
public class Location {

    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String zipCode;
    private String city;
    private String countryCode;

}
