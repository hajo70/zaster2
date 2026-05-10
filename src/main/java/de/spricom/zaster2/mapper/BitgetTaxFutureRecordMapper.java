package de.spricom.zaster2.mapper;

import de.spricom.zaster2.entities.BitgetTaxFutureRecordEntity;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper
public interface BitgetTaxFutureRecordMapper extends BitgetMapper {

    BitgetTaxFutureRecordEntity toEntity(Map<String, String> map);
}
