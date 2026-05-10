package de.spricom.zaster2.mapper;

import de.spricom.zaster2.entities.BitgetFutureOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;

@Mapper
public interface BitgetFutureOrderMapper extends BitgetMapper {

    @Mapping(target = "createdAt", source = "cTime")
    @Mapping(target = "updatedAt", source = "uTime")
    BitgetFutureOrderEntity toEntity(Map<String, String> map);
}
