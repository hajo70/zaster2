package de.spricom.zaster2.okx;

import de.spricom.zaster2.okx.model.OkxFill;
import de.spricom.zaster2.okx.model.OkxResponse;
import de.spricom.zaster2.okx.service.OkxApiClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
public class OkxApiDemo {

    @Autowired
    private OkxApiClient okxApiClient;

    @Test
    void showFillsHistory() {
        log.info("Requesting recent future fills...");
        OkxResponse<OkxFill> response = okxApiClient.getFillsHistory("FUTURES");
        if (response != null && response.isSuccess() && response.getData() != null) {
            if (response.getData().isEmpty()) {
                log.info("No recent fills found.");
            } else {
                int count = 0;
                for (OkxFill fill : response.getData()) {
                    count++;
                    log.info("#{}: {}", count, fill);
                }
            }
        } else {
            String errorMsg = (response != null) ? response.getMsg() : "Empty response";
            log.warn("Failed to get fills: {}", errorMsg);
        }
    }

}
