package de.spricom.zaster2.mapper;

import de.spricom.zaster2.entities.OkxFillEntity;
import de.spricom.zaster2.okx.model.OkxFill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OkxFillMapperTest {

    @Autowired
    private OkxFillMapper mapper;

    @Test
    void testMapOkxFill() {
        OkxFill fill = new OkxFill();
        fill.setInstType("FUTURES");
        fill.setInstId("ZEC-USD_UM_XPERP-310530");
        fill.setTradeId("6081356");
        fill.setOrdId("4005381605299064832");
        fill.setClOrdId("");
        fill.setBillId("4010217361792307200");
        fill.setTag("OTversionB");
        fill.setFillPx("1125");
        fill.setFillSz("3");
        fill.setSide("buy");
        fill.setPosSide("net");
        fill.setExecType("M");
        fill.setFeeCcy("USDC");
        fill.setFee("-0.00675");
        fill.setTs("1792016190660");
        fill.setFillTime("1792016190659");
        fill.setFillPxVol("");
        fill.setFillPxUsd("");
        fill.setFillMarkVol("");
        fill.setFillMarkPx("1126.07");
        fill.setTradeQuoteQty(null);

        OkxFillEntity entity = mapper.toEntity(fill);

        assertThat(entity).isNotNull();
        assertThat(entity.getBillId()).isEqualTo(4010217361792307200L);
        assertThat(entity.getInstType()).isEqualTo("FUTURES");
        assertThat(entity.getInstId()).isEqualTo("ZEC-USD_UM_XPERP-310530");
        assertThat(entity.getTradeId()).isEqualTo(6081356L);
        assertThat(entity.getOrdId()).isEqualTo(4005381605299064832L);
        assertThat(entity.getClOrdId()).isEqualTo("");
        assertThat(entity.getTag()).isEqualTo("OTversionB");
        assertThat(entity.getFillPx()).isEqualByComparingTo(new BigDecimal("1125"));
        assertThat(entity.getFillSz()).isEqualByComparingTo(new BigDecimal("3"));
        assertThat(entity.getSide()).isEqualTo("buy");
        assertThat(entity.getPosSide()).isEqualTo("net");
        assertThat(entity.getExecType()).isEqualTo("M");
        assertThat(entity.getFeeCcy()).isEqualTo("USDC");
        assertThat(entity.getFee()).isEqualByComparingTo(new BigDecimal("-0.00675"));
        assertThat(entity.getTs()).isEqualTo(Instant.ofEpochMilli(1792016190660L));
        assertThat(entity.getFillTime()).isEqualTo(Instant.ofEpochMilli(1792016190659L));
        assertThat(entity.getFillPxVol()).isNull();
        assertThat(entity.getFillPxUsd()).isNull();
        assertThat(entity.getFillMarkVol()).isNull();
        assertThat(entity.getFillMarkPx()).isEqualByComparingTo(new BigDecimal("1126.07"));
        assertThat(entity.getTradeQuoteQty()).isNull();
    }

    @Test
    void testMapOkxFillWithClOrdId() {
        OkxFill fill = new OkxFill();
        fill.setInstType("FUTURES");
        fill.setInstId("ZEC-USD_UM_XPERP-310530");
        fill.setTradeId("5935698");
        fill.setOrdId("4002909740722819072");
        fill.setClOrdId("O4002909740664674304");
        fill.setBillId("4002909741159026688");
        fill.setTag("OTversionB");
        fill.setFillPx("1298.28");
        fill.setFillSz("8");
        fill.setSide("sell");
        fill.setPosSide("net");
        fill.setExecType("T");
        fill.setFeeCcy("USDC");
        fill.setFee("-0.0519312");
        fill.setTs("1791798406594");
        fill.setFillTime("1791798406581");
        fill.setFillPxVol("");
        fill.setFillPxUsd("");
        fill.setFillMarkVol("");
        fill.setFillMarkPx("1300.27");

        OkxFillEntity entity = mapper.toEntity(fill);

        assertThat(entity.getBillId()).isEqualTo(4002909741159026688L);
        assertThat(entity.getClOrdId()).isEqualTo("O4002909740664674304");
        assertThat(entity.getFillPx()).isEqualByComparingTo(new BigDecimal("1298.28"));
        assertThat(entity.getFillSz()).isEqualByComparingTo(new BigDecimal("8"));
        assertThat(entity.getFee()).isEqualByComparingTo(new BigDecimal("-0.0519312"));
        assertThat(entity.getFillMarkPx()).isEqualByComparingTo(new BigDecimal("1300.27"));
    }

    @Test
    void testMapEmptyFieldsToNull() {
        OkxFill fill = new OkxFill();
        fill.setBillId("12345");
        fill.setFillPx("");
        fill.setFillSz("");
        fill.setFee("");
        fill.setTs("");
        fill.setFillTime("");
        fill.setTradeId("");
        fill.setOrdId("");

        OkxFillEntity entity = mapper.toEntity(fill);

        assertThat(entity.getBillId()).isEqualTo(12345L);
        assertThat(entity.getFillPx()).isNull();
        assertThat(entity.getFillSz()).isNull();
        assertThat(entity.getFee()).isNull();
        assertThat(entity.getTs()).isNull();
        assertThat(entity.getFillTime()).isNull();
        assertThat(entity.getTradeId()).isNull();
        assertThat(entity.getOrdId()).isNull();
    }

    @Test
    void testMapList() {
        OkxFill fill = new OkxFill();
        fill.setBillId("1001");
        List<OkxFillEntity> entities = mapper.toEntities(List.of(fill));
        assertThat(entities).hasSize(1);
        assertThat(entities.getFirst().getBillId()).isEqualTo(1001L);
    }

    @Test
    void testMapFromMap() {
        Map<String, String> data = new HashMap<>();
        data.put("billId", "9999");
        data.put("fillPx", "100.5");
        data.put("instId", "BTC-USDT");
        data.put("ts", "1792016190660");

        OkxFillEntity entity = mapper.toEntity(data);

        assertThat(entity.getBillId()).isEqualTo(9999L);
        assertThat(entity.getFillPx()).isEqualByComparingTo(new BigDecimal("100.5"));
        assertThat(entity.getInstId()).isEqualTo("BTC-USDT");
        assertThat(entity.getTs()).isEqualTo(Instant.ofEpochMilli(1792016190660L));
    }
}
