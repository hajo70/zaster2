package de.spricom.zaster2.tool;

import de.spricom.zaster2.bitget.BitgetApi;
import de.spricom.zaster2.mapper.BitgetTaxFutureRecordMapper;
import de.spricom.zaster2.mapper.BitgetTaxSpotRecordMapper;
import de.spricom.zaster2.repository.BitgetTaxFutureRecordRepository;
import de.spricom.zaster2.repository.BitgetTaxSpotRecordRepository;
import de.spricom.zaster2.service.BitgetApiImportService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Log4j2
public class BitgetTaxDataImporter {
    @Autowired
    private BitgetApiImportService service;

    @Autowired
    private BitgetApi api;

    @Autowired
    private BitgetTaxFutureRecordRepository futureRepository;

    @Autowired
    private BitgetTaxSpotRecordRepository spotRepository;

    @Autowired
    private BitgetTaxFutureRecordMapper futureMapper;

    @Autowired
    private BitgetTaxSpotRecordMapper spotMapper;

    @Test
    void updateAll() throws IOException {
        service.importLatestOrders();
        service.importLatestPositions();
        service.importLatestTaxFutureRecords();
        service.importLatestTaxSpotRecords();
    }

    @Test
    void importLatestOrders() throws IOException {
        service.importLatestOrders();
    }

    @Test
    void importLatestPositions() throws IOException {
        service.importLatestPositions();
    }

    @Test
    void importLatestTaxFutureRecords() throws IOException {
        service.importLatestTaxFutureRecords();
    }

    @Test
    void importLatestTaxSpotRecords() throws IOException {
        service.importLatestTaxSpotRecords();
    }
}
