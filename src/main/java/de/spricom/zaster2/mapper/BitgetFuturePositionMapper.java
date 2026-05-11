package de.spricom.zaster2.mapper;

import de.spricom.zaster2.entities.BitgetFuturePositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;

@Mapper
public interface BitgetFuturePositionMapper extends BitgetMapper {

    @Mapping(target = "createdAt", source = "ctime")
    @Mapping(target = "updatedAt", source = "utime")
    BitgetFuturePositionEntity toEntity(Map<String, String> map);
}
