package de.spricom.zaster2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Zaster2Application {

    static void main(String[] args) {
        SpringApplication.run(Zaster2Application.class);
    }
}
