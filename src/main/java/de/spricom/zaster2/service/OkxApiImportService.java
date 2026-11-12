package de.spricom.zaster2.service;

import de.spricom.zaster2.entities.OkxBillDetailsEntity;
import de.spricom.zaster2.mapper.OkxBillDetailsMapper;
import de.spricom.zaster2.okx.model.BillDetailsData;
import de.spricom.zaster2.okx.model.BillDetailsRequest;
import de.spricom.zaster2.okx.service.OkxApiClient;
import de.spricom.zaster2.repository.OkxBillDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class OkxApiImportService {
    private final OkxApiClient apiClient;
    private final OkxBillDetailsRepository billDetailsRepository;
    private final OkxBillDetailsMapper billDetailsMapper;

    public void importLastBillDetails() {
        BillDetailsRequest req = new BillDetailsRequest();
        Long maxBillId = billDetailsRepository.findMaxBillId();
        req.setBefore(maxBillId == null ? null : String.valueOf(maxBillId));
        List<BillDetailsData> billDetails;
        int count = 0;
        do {
            billDetails = apiClient.getBillsDetails(req);
            OkxBillDetailsEntity entity = null;
            for (BillDetailsData billDetail : billDetails) {
                entity = billDetailsMapper.toEntity(billDetail);
                entity = billDetailsRepository.save(entity);
                count++;
            }
            if (entity != null && (maxBillId == null || maxBillId < entity.getBillId())) {
                req.setAfter(String.valueOf(entity.getBillId()));
            } else {
                break;
            }
        } while (!billDetails.isEmpty());
        log.info("Imported {} OKX bill-details.", count);
    }

}
