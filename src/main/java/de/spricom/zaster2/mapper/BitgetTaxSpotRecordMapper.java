package de.spricom.zaster2.mapper;

import de.spricom.zaster2.entities.BitgetTaxSpotRecordEntity;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface BitgetTaxSpotRecordMapper extends BitgetMapper {

    BitgetTaxSpotRecordEntity toEntity(Map<String, String> map);

}
