package de.spricom.zaster2.runners;

import de.spricom.zaster2.service.BitgetApiImportService;
import de.spricom.zaster2.service.OkxApiImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Profile("!test")
public class ApiImportRunner implements ApplicationRunner {

    @Autowired
    private BitgetApiImportService bitgetApiImportService;

    @Autowired
    private OkxApiImportService okxApiImportService;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        okxApiImportService.importLastBillDetails();

        bitgetApiImportService.importLatestOrders();
        bitgetApiImportService.importLatestPositions();
        bitgetApiImportService.importLatestTaxFutureRecords();
        bitgetApiImportService.importLatestTaxSpotRecords();
    }
}
