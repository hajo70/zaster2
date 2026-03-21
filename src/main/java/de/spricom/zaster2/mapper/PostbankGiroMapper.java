package de.spricom.zaster2.mapper;

import de.spricom.zaster2.dto.PostbankGiroCsvDto;
import de.spricom.zaster2.entities.PostbankGiroEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PostbankGiroMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fileImportHistory", ignore = true)
    PostbankGiroEntity toEntity(PostbankGiroCsvDto dto);
}
