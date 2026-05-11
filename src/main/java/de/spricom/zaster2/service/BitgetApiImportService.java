package de.spricom.zaster2.service;

import com.bitget.openapi.dto.response.ResponseResult;
import de.spricom.zaster2.bitget.BitgetApi;
import de.spricom.zaster2.entities.BitgetFutureOrderEntity;
import de.spricom.zaster2.entities.BitgetFuturePositionEntity;
import de.spricom.zaster2.entities.BitgetTaxFutureRecordEntity;
import de.spricom.zaster2.entities.BitgetTaxSpotRecordEntity;
import de.spricom.zaster2.mapper.BitgetFutureOrderMapper;
import de.spricom.zaster2.mapper.BitgetFuturePositionMapper;
import de.spricom.zaster2.mapper.BitgetTaxFutureRecordMapper;
import de.spricom.zaster2.mapper.BitgetTaxSpotRecordMapper;
import de.spricom.zaster2.repository.BitgetFutureOrderRepository;
import de.spricom.zaster2.repository.BitgetFuturePositionRepository;
import de.spricom.zaster2.repository.BitgetTaxFutureRecordRepository;
import de.spricom.zaster2.repository.BitgetTaxSpotRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class BitgetApiImportService {
    private final BitgetApi api;
    private final BitgetFutureOrderRepository futureOrderRepository;
    private final BitgetFuturePositionRepository futurePositionRepository;
    private final BitgetTaxFutureRecordRepository taxFutureRecordRepository;
    private final BitgetTaxSpotRecordRepository taxSpotRecordRepository;
    private final BitgetFutureOrderMapper futureOrderMapper;
    private final BitgetFuturePositionMapper futurePositionMapper;
    private final BitgetTaxFutureRecordMapper taxFutureRecordMapper;
    private final BitgetTaxSpotRecordMapper taxSpotRecordMapper;

    public void importLatestOrders() throws IOException {
        Instant startTime = withinLast90Days(futureOrderRepository.findMaxUpdatedAt());
        log.info("importing orders from {}", startTime);
        Long endId = null;
        for(int i = 0; i < 100; i++) {
            ResponseResult<Map> result = api.getHistoryOrder(startTime, endId);
            String endIdString = (String) result.getData().get("endId");
            if (endIdString == null || endIdString.isBlank()) {
                break;
            }
            endId = Long.parseLong(endIdString);
            List<?> entrustedList = (List<?>) result.getData().get("entrustedList");
            if (entrustedList.isEmpty()) {
                break;
            }
            log.info("got {} history orders, endId: {}", entrustedList.size(), endId);
            saveFutureOrders(entrustedList);
        }
    }

    public void importLatestPositions() throws IOException {
        Instant startTime = withinLast90Days(futurePositionRepository.findMaxUpdatedAt());
        log.info("importing positions from {}", startTime);
        Long endId = null;
        for(int i = 0; i < 100; i++) {
            ResponseResult<Map> result = api.getHistoryPosition(startTime, endId);
            String endIdString = (String) result.getData().get("endId");
            if (endIdString == null || endIdString.isBlank()) {
                break;
            }
            endId = Long.parseLong(endIdString);
            List<?> list = (List<?>) result.getData().get("list");
            if (list.isEmpty()) {
                break;
            }
            log.info("got {} history orders, endId: {}", list.size(), endId);
            saveFuturePositions(list);
        }
    }

    private Instant withinLast90Days(Instant utime) {
        if (utime != null) {
            return utime;
        }
        return Instant.now().minus(91, ChronoUnit.DAYS);
    }

    public void importCurrentTaxFutureRecords() throws IOException {
        ResponseResult<List> result = api.taxFutureRecord(Instant.now().minus(30, ChronoUnit.DAYS), Instant.now());
        saveTaxFutureRecords(result);
    }

    private void saveFutureOrders(List<?> resultList) {
        for (Object json : resultList) {
            Map<String, String> data = (Map<String, String>) json;
            BitgetFutureOrderEntity entity = futureOrderMapper.toEntity(data);
            BitgetFutureOrderEntity saved = futureOrderRepository.save(entity);
            log.info("saved {}", saved);
        }
    }

    private void saveFuturePositions(List<?> resultList) {
        for (Object json : resultList) {
            Map<String, String> data = (Map<String, String>) json;
            BitgetFuturePositionEntity entity = futurePositionMapper.toEntity(data);
            BitgetFuturePositionEntity saved = futurePositionRepository.save(entity);
            log.info("saved {}", saved);
        }
    }

    private void saveTaxFutureRecords(ResponseResult<List> result) {
        for (Object json : result.getData()) {
            Map<String, String> data = (Map<String, String>) json;
            BitgetTaxFutureRecordEntity entity = taxFutureRecordMapper.toEntity(data);
            BitgetTaxFutureRecordEntity saved = taxFutureRecordRepository.save(entity);
            log.info("saved {}", saved);
        }
    }

    private void saveTaxSpotRecords(ResponseResult<List> result) {
        for (Object json : result.getData()) {
            Map<String, String> data = (Map<String, String>) json;
            BitgetTaxSpotRecordEntity entity = taxSpotRecordMapper.toEntity(data);
            BitgetTaxSpotRecordEntity saved = taxSpotRecordRepository.save(entity);
            log.info("saved {}", saved);
        }
    }

}
