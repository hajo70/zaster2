package de.spricom.zaster2.bitget;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@SpringBootTest
class BitgetApiSample {

    @Autowired
    private BitgetApi api;

    @Test
    void taxSpotRecord() throws IOException {
        api.taxSpotRecord(Instant.now().minus(30, ChronoUnit.DAYS), Instant.now());
    }

    @Test
    void taxFutureRecord() throws IOException {
        api.taxFutureRecord(Instant.now().minus(30, ChronoUnit.DAYS), Instant.now());
    }
}