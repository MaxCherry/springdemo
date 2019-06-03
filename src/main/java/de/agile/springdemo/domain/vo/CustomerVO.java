package de.agile.springdemo.domain.vo;

import com.fasterxml.jackson.annotation.JsonView;
import de.agile.springdemo.api.mapper.Views;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@JsonView(Views.REDUCED.class)
public class CustomerVO {

    @JsonView(Views.REDUCED.class)
    private String customerNo;

    @NotBlank
    private String vbkz;

    private List<LocationVO> locations;
    private List<ContactPersonVO> contactPeople;


}
