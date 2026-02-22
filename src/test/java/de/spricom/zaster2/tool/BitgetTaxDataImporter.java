package de.spricom.zaster2.tool;

import com.bitget.openapi.dto.response.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.spricom.zaster2.bitget.BitgetApi;
import de.spricom.zaster2.entities.BitgetTaxFutureRecordEntity;
import de.spricom.zaster2.mapper.BitgetTaxFutureRecordMapper;
import de.spricom.zaster2.repository.BitgetTaxFutureRecordRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Log4j2
public class BitgetTaxDataImporter {
    @Autowired
    private BitgetApi api;

    @Autowired
    private BitgetTaxFutureRecordRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BitgetTaxFutureRecordMapper recordMapper;

    @Test
    void importCurrentTaxFutureRecords() throws IOException {
        ResponseResult<List> result = api.taxFutureRecord(Instant.now().minus(30, ChronoUnit.DAYS), Instant.now());
        saveTaxFutureRecords(result);
    }

    @Test
    void importHistoricTaxFutureRecords() throws IOException, InterruptedException {
        Instant limit = Instant.now();
        while (!isLimitReached(limit)) {
            limit = previous(limit);
            ResponseResult<List> result = api.taxFutureRecord(limit.minus(30, ChronoUnit.DAYS), limit);
            saveTaxFutureRecords(result);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private Instant previous(Instant last) {
        return last.minus(30, ChronoUnit.DAYS).plus(5, ChronoUnit.MINUTES);
    }

    private boolean isLimitReached(Instant last) {
        return last.atOffset(ZoneOffset.UTC).toLocalDate().isBefore(LocalDate.of(2024, Month.JANUARY, 1));
    }

    private void saveTaxFutureRecords(ResponseResult<List> result) {
        for (Object json : result.getData()) {
            Map<String, String> data = (Map<String, String>) json;
            BitgetTaxFutureRecordEntity entity = recordMapper.toEntity(data);
            BitgetTaxFutureRecordEntity saved = repository.save(entity);
            log.info("saved {}", saved);
        }
    }
}
