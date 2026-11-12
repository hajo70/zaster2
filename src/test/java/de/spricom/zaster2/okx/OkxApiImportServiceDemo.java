package de.spricom.zaster2.okx;

import de.spricom.zaster2.service.OkxApiImportService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
public class OkxApiImportServiceDemo {

    @Autowired
    private OkxApiImportService service;

    @Test
    void importLatestBillDetails() {
        service.importLastBillDetails();
    }
}
