package de.agile.springdemo.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.agile.springdemo.domain.entity.Salutation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ContactPersonVO {

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
