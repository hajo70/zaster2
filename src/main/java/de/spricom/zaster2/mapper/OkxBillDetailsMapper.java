package de.spricom.zaster2.mapper;

import de.spricom.zaster2.entities.OkxBillDetailsEntity;
import de.spricom.zaster2.okx.model.BillDetailsData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OkxBillDetailsMapper extends OkxMapper {

    OkxBillDetailsEntity toEntity(BillDetailsData bill);

    List<OkxBillDetailsEntity> toEntities(List<BillDetailsData> bills);
}
