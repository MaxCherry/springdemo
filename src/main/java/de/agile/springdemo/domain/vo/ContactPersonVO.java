package de.agile.springdemo.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import de.agile.springdemo.api.mapper.Views;
import de.agile.springdemo.domain.entity.Salutation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ContactPersonVO {

    @JsonView(Views.REDUCED.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonView(Views.ALL.class)
    @NotNull
    private Salutation salutation;

    @JsonView(Views.ALL.class)
    @NotBlank
    private String firstName;

    @JsonView(Views.ALL.class)
    @NotBlank
    private String lastName;

    @JsonView(Views.ALL.class)
    @Pattern(regexp = "^\\+[1-9]{1}[0-9]{3,14}$")
    private String phoneNumber;

}
