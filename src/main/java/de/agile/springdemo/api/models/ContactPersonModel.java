package de.agile.springdemo.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.agile.springdemo.domain.entity.Salutation;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ContactPersonModel extends RepresentationModel<ContactPersonModel> {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    private Salutation salutation;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(regexp = "^\\+[1-9]{1}[0-9]{3,14}$")
    private String phoneNumber;
}
