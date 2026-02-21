package de.spricom.zaster2.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.EnvironmentPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@Log4j2
public class EncryptionPropertyEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("Adding EncryptionPropertySource ...");
        var encryptionPropertySource = new EncryptionPropertySource(environment);
        environment.getPropertySources().addFirst(encryptionPropertySource);
    }
}
