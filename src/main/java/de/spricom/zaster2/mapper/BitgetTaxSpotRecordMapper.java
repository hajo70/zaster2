package de.spricom.zaster2.mapper;

import de.spricom.zaster2.entities.BitgetTaxSpotRecordEntity;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

@Mapper
public interface BitgetTaxSpotRecordMapper {

    BitgetTaxSpotRecordEntity toEntity(Map<String, String> map);

    // String (epoch millis) → LocalDateTime in UTC
    default LocalDateTime map(String value) {
        if (value == null) {
            return null;
        }
        long millis = Long.parseLong(value);
        return Instant.ofEpochMilli(millis)
                .atOffset(ZoneOffset.UTC)
                .toLocalDateTime();
    }
}
