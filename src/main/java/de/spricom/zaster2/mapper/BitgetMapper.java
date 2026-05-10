package de.spricom.zaster2.mapper;

import java.math.BigDecimal;
import java.time.Instant;

public interface BitgetMapper {

    // String (epoch millis) → Instant
    default Instant map(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        long millis = Long.parseLong(value);
        return Instant.ofEpochMilli(millis);
    }

    default BigDecimal mapBigDecimal(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return new BigDecimal(value);
    }
}
