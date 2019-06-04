package de.agile.springdemo.api.models;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class CustomerModel extends RepresentationModel<CustomerModel> {

    private String customerNo;
    private String vbkz;

}
