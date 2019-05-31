package de.agile.springdemo.domain.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * Created on 28.05.19
 * <p>
*/


@Data
@Entity
public class ContactPerson {

    @GeneratedValue
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private Salutation salutation;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
