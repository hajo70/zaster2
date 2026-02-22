package de.spricom.zaster2.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.spricom.zaster2.entities.BitgetTaxSpotRecordEntity;
import de.spricom.zaster2.mapper.BitgetTaxSpotRecordMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Disabled("manual")
class BitgetTaxSpotRecordRepositoryTest {
    @Autowired
    private BitgetTaxSpotRecordRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BitgetTaxSpotRecordMapper recordMapper;

    @Test
    void save_record() throws JsonProcessingException {
        String json = """
                {
                	"id":"1407386901673619456",
                	"coin":"XRP",
                	"spotTaxType":"Withdrawal",
                	"amount":"-899.8",
                	"fee":"-0.2",
                	"balance":"200.000000008",
                	"ts":"1771273106008",
                	"bizOrderId":"1407386306736173056"
                }
                """;
        Map<String, String> map = mapper.readValue(json, new TypeReference<>() {
        });
        System.out.println(map);
        BitgetTaxSpotRecordEntity entity = recordMapper.toEntity(map);
        System.out.println(entity);
        BitgetTaxSpotRecordEntity saved = repository.save(entity);
        System.out.println(saved);
    }
}