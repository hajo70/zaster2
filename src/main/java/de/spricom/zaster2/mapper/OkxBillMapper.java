package de.spricom.zaster2.mapper;

import de.spricom.zaster2.entities.OkxBillEntity;
import de.spricom.zaster2.okx.model.BillDetailsData;
import de.spricom.zaster2.okx.model.OkxBillData;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OkxBillMapper extends OkxMapper {

    OkxBillEntity toEntity(OkxBillData bill);

    List<OkxBillEntity> toEntities(List<OkxBillData> bills);

    OkxBillEntity toEntity(BillDetailsData bill);

    List<OkxBillEntity> toEntitiesFromDetails(List<BillDetailsData> bills);

    OkxBillEntity toEntity(Map<String, String> map);
}
