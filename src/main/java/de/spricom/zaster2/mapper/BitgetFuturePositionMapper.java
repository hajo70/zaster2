package de.spricom.zaster2.mapper;

import de.spricom.zaster2.entities.BitgetFuturePositionEntity;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface BitgetFuturePositionMapper extends BitgetMapper {

    BitgetFuturePositionEntity toEntity(Map<String, String> map);
}
