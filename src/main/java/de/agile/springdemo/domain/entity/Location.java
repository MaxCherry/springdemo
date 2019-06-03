package de.agile.springdemo.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Location {

    @Id
    @GeneratedValue
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String zipCode;
    private String city;
    private String countryCode;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

}
