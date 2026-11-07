package de.spricom.zaster2.okx;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zaster2.okx")
public record OkxProperties(
        String baseUrl,
        ApiKey read
) {
}
