package de.spricom.zaster2.okx.service;

import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Component
public class OkxSignatureGenerator {

    private static final String HMAC_SHA256 = "HmacSHA256";

    /**
     * Generates standard ISO-8601 UTC timestamp required by OKX API (e.g. 2026-10-10T12:00:00.123Z).
     */
    public String generateTimestamp() {
        return generateTimestamp(Instant.now());
    }

    /**
     * Generates standard ISO-8601 UTC timestamp from given Instant.
     */
    public String generateTimestamp(Instant instant) {
        return DateTimeFormatter.ISO_INSTANT.format(instant);
    }

    /**
     * Formats server timestamp (Unix timestamp millis or ISO string) to ISO-8601 UTC timestamp string.
     */
    public String formatTimestamp(String serverTs) {
        if (serverTs == null || serverTs.isBlank()) {
            throw new IllegalArgumentException("Server timestamp must not be null or blank");
        }
        try {
            long epochMilli = Long.parseLong(serverTs);
            return generateTimestamp(Instant.ofEpochMilli(epochMilli));
        } catch (NumberFormatException e) {
            return serverTs;
        }
    }

    /**
     * Generates OKX API sign string (Base64 encoded HMAC-SHA256 of timestamp + METHOD + requestPath + body).
     *
     * @param timestamp   ISO-8601 UTC timestamp string
     * @param method      HTTP method (GET, POST, etc.)
     * @param requestPath Path and query params (e.g. /api/v5/account/balance or /api/v5/asset/balances?ccy=BTC)
     * @param body        Request body string (empty string for GET or empty body)
     * @param secretKey   Secret key for HMAC-SHA256
     * @return Base64-encoded signature
     */
    public String generateSignature(String timestamp, String method, String requestPath, String body, String secretKey) {
        String prehash = timestamp + method.toUpperCase() + requestPath + (body == null ? "" : body);
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            mac.init(secretKeySpec);
            byte[] hash = mac.doFinal(prehash.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalStateException("Failed to calculate HMAC-SHA256 signature", e);
        }
    }
}
