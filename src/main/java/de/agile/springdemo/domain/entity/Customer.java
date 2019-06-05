package de.agile.springdemo.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class Customer {

    private String customerNo;
    private String vbkz;

    private List<Location> locations;
    private List<ContactPerson> contactPeople;

}
