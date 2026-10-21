package de.spricom.zaster2.runners;

import de.spricom.zaster2.service.BitgetApiImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("!test")
public class BitgetRunner implements ApplicationRunner {

    @Autowired
    private BitgetApiImportService service;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        service.importLatestOrders();
        service.importLatestPositions();
        service.importLatestTaxFutureRecords();
        service.importLatestTaxSpotRecords();
    }
}
