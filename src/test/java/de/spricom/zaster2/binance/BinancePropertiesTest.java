package de.spricom.zaster2.binance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BinancePropertiesTest.class)
@EnableConfigurationProperties(BinanceProperties.class)
@ActiveProfiles("test")
class BinancePropertiesTest {

    @Autowired
    private BinanceProperties properties;

    @Test
    void dump() {
        System.out.println(properties.toString());
    }
}