package de.spricom.zaster2.mapper;

import de.spricom.zaster2.entities.BitgetFutureOrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BitgetFutureOrderMapperTest {

    @Autowired
    private BitgetFutureOrderMapper mapper;

    @Test
    public void testMapEmptyFieldsToNull() {
        Map<String, String> data = new HashMap<>();
        data.put("orderId", "12345");
        data.put("price", "");
        data.put("size", "");
        data.put("cTime", "");
        data.put("uTime", "1620000000000");

        BitgetFutureOrderEntity entity = mapper.toEntity(data);

        assertThat(entity.getPrice()).isNull();
        assertThat(entity.getSize()).isNull();
        assertThat(entity.getCreatedAt()).isNull();
        assertThat(entity.getUpdatedAt()).isNotNull();
    }
}
