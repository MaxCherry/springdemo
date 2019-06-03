package de.agile.springdemo.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    private String customerNo;
    private String vbkz;

    @OneToMany(targetEntity = Location.class, mappedBy = "customer")
    private List<Location> locations;
    @OneToMany(targetEntity = ContactPerson.class)
    private List<ContactPerson> contactPeople;

}
