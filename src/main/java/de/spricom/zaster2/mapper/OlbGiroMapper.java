package de.spricom.zaster2.mapper;

import de.spricom.zaster2.dto.OlbGiroCsvDto;
import de.spricom.zaster2.entities.OlbGiroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface OlbGiroMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fileImportHistory", ignore = true)
    @Mapping(source = "paymentReference", target = "paymentReference", qualifiedByName = "normalizeWhitespace")
    OlbGiroEntity toEntity(OlbGiroCsvDto dto);

    @Named("normalizeWhitespace")
    default String normalizeWhitespace(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("\\s+", " ").trim();
    }
}
