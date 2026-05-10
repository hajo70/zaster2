package de.spricom.zaster2.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest
@Disabled("manual")
class BitgetFutureOrderRepositoryTest {
    @Autowired
    private BitgetFutureOrderRepository repository;

    @Test
    void show_maxUTime() {
        Instant maxUTime = repository.findMaxUpdatedAt();
        System.out.println("Max uTime: " + maxUTime);
    }
}
