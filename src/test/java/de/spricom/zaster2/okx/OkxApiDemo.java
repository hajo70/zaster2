package de.spricom.zaster2.okx;

import de.spricom.zaster2.okx.model.BillDetailsData;
import de.spricom.zaster2.okx.model.BillDetailsRequest;
import de.spricom.zaster2.okx.service.OkxApiClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
public class OkxApiDemo {

    @Autowired
    private OkxApiClient okxApiClient;

    @Test
    void showBillDetails() {
        log.info("Requesting bill details...");
        BillDetailsRequest req = new BillDetailsRequest();
        req.setAfter("4051496405116391426");
        List<BillDetailsData> data = okxApiClient.getBillsDetails(req);
        int count = 0;
        for (BillDetailsData details : data) {
            count++;
            log.info("#{}: {}", count, details);
        }
    }

}
