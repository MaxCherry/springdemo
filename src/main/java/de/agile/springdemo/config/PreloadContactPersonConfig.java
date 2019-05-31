package de.agile.springdemo.config;

import de.agile.springdemo.domain.entity.ContactPerson;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "preload-contactpersons")
@Data
public class PreloadContactPersonConfig {

    private List<ContactPerson> users;
}
