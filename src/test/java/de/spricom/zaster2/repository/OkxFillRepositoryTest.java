package de.spricom.zaster2.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest
@Disabled("manual")
class OkxFillRepositoryTest {

    @Autowired
    private OkxFillRepository repository;

    @Test
    void show_maxValues() {
        Instant maxFillTime = repository.findMaxFillTime();
        Instant maxTs = repository.findMaxTs();
        Long maxBillId = repository.findMaxBillId();
        System.out.println("Max fillTime: " + maxFillTime);
        System.out.println("Max ts: " + maxTs);
        System.out.println("Max billId: " + maxBillId);
    }
}
