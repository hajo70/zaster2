package de.spricom.zaster2.bitget;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BitgetPropertiesTest.class)
@EnableConfigurationProperties(BitgetProperties.class)
@ActiveProfiles("test")
class BitgetPropertiesTest {

    @Autowired
    private BitgetProperties properties;

    @Test
    void dump() {
        System.out.println(properties.toString());
    }

}