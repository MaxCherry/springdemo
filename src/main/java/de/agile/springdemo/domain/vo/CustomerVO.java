package de.agile.springdemo.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerVO {

    @NotBlank
    private String customerNo;
    @NotBlank
    private String vbkz;



}
