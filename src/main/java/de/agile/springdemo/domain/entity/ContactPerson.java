package de.agile.springdemo.domain.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Created on 28.05.19
 * <p>
*/


@Data
@Builder
public class ContactPerson {

    private Long id;
    private Salutation salutation;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
