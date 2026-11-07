package de.spricom.zaster2.okx;

public record ApiKey(
        String apiKey,
        String secretKey,
        String passphrase
) {
}
