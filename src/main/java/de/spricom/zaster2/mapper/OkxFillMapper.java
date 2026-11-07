package de.spricom.zaster2.mapper;

import de.spricom.zaster2.entities.OkxFillEntity;
import de.spricom.zaster2.okx.model.OkxFill;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OkxFillMapper extends OkxMapper {

    OkxFillEntity toEntity(OkxFill fill);

    List<OkxFillEntity> toEntities(List<OkxFill> fills);

    OkxFillEntity toEntity(Map<String, String> map);
}
