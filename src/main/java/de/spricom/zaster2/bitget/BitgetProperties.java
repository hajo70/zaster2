package de.spricom.zaster2.bitget;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zaster2.bitget")
public record BitgetProperties(
        String url,
        String apiKey,
        String secretKey,
        String passPhrase
) {
}

