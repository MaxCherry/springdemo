package de.agile.springdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@ConfigurationProperties(prefix = "springdemo.security")
@Data
public class UserConfiguration {

    private List<ApiUser> users;

    @Bean
    List<ApiUser> users() {
        return users;
    }

}
