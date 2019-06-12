package de.agile.springdemo.config;

import lombok.Data;

@Data
public class ApiUser {

    private String userName;
    private String password;
    private String roles;

}
