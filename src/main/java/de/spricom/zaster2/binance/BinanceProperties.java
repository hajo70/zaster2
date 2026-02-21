package de.spricom.zaster2.binance;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zaster2.binance")
public record BinanceProperties(
        String url,
        String apiKey,
        String secretKey) {
}
