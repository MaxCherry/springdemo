package de.agile.springdemo.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CustomerVO {

    @NotBlank
    private String customerNo;
    @NotBlank
    private String vbkz;

    private List<ContactPersonVO> contactPeople;


}
