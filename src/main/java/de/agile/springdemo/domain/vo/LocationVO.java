package de.agile.springdemo.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import de.agile.springdemo.api.mapper.Views;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LocationVO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(Views.REDUCED.class)
    private Long id;

    @NotBlank
    @JsonView(Views.ALL.class)
    private String addressLine1;

    @JsonView(Views.ALL.class)
    private String addressLine2;

    @NotBlank
    @JsonView(Views.ALL.class)
    private String zipCode;

    @NotBlank
    @JsonView(Views.ALL.class)
    private String city;

    @JsonView(Views.ALL.class)
    private String countryCode;
}
