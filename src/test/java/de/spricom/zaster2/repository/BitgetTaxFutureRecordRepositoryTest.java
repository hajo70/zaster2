package de.spricom.zaster2.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.spricom.zaster2.entities.BitgetTaxFutureRecordEntity;
import de.spricom.zaster2.mapper.BitgetTaxFutureRecordMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Disabled("manual")
class BitgetTaxFutureRecordRepositoryTest {
    @Autowired
    private BitgetTaxFutureRecordRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BitgetTaxFutureRecordMapper recordMapper;

    @Test
    void save_record() throws JsonProcessingException {
        String json = """
                {
                	"id":"1401500221916745735",
                	"symbol":"DOGEUSDT",
                	"marginCoin":"USDT",
                	"futureTaxType":"buy_deal",
                	"amount":"-0.0682",
                	"fee":"-0.0140184",
                	"ts":"1769869612196"
                }
                """;
        Map<String, String> map = mapper.readValue(json, new TypeReference<>() {});
        System.out.println(map);
        BitgetTaxFutureRecordEntity entity = recordMapper.toEntity(map);
        BitgetTaxFutureRecordEntity saved = repository.save(entity);
        System.out.println(saved);
    }

    @Test
    void show_minId() {
        Long minId = repository.getMinId();
        System.out.println(minId);
    }
}