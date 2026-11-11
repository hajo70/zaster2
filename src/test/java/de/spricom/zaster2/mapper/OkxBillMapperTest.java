package de.spricom.zaster2.mapper;

import de.spricom.zaster2.entities.OkxBillEntity;
import de.spricom.zaster2.okx.model.BillDetailsData;
import de.spricom.zaster2.okx.model.OkxBillData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class OkxBillMapperTest {

    @Autowired
    private OkxBillMapper mapper;

    @Test
    void testMapOkxBillDataSample1() {
        OkxBillData data = new OkxBillData();
        data.setBillId("4051013181533589506");
        data.setCcy("USDC");
        data.setBal("81.7712695459343799");
        data.setBalChg("0.0000000000000000");
        data.setClOrdId("");
        data.setEarnAmt("");
        data.setEarnApr("");
        data.setExecType("");
        data.setFee("0");
        data.setFillFwdPx("");
        data.setFillIdxPx("");
        data.setFillMarkPx("");
        data.setFillMarkVol("");
        data.setFillPxUsd("");
        data.setFillPxVol("");
        data.setFillTime("1793232000832");
        data.setFrom("");
        data.setInstId("GRAM-USD_UM_XPERP-310620");
        data.setInstType("FUTURES");
        data.setInterest("0");
        data.setMgnMode("isolated");
        data.setNotes("");
        data.setOrdId("");
        data.setPnl("-0.00600915");
        data.setPosBal("42.8248581833333333");
        data.setPosBalChg("-0.0060091500000000");
        data.setPx("1.239");
        data.setSubType("173");
        data.setSz("97");
        data.setTag("");
        data.setTo("");
        data.setTradeId("0");
        data.setTs("1793232000832");
        data.setType("8");

        OkxBillEntity entity = mapper.toEntity(data);

        assertThat(entity).isNotNull();
        assertThat(entity.getBillId()).isEqualTo(4051013181533589506L);
        assertThat(entity.getCcy()).isEqualTo("USDC");
        assertThat(entity.getBal()).isEqualByComparingTo(new BigDecimal("81.7712695459343799"));
        assertThat(entity.getBalChg()).isEqualByComparingTo(new BigDecimal("0.0000000000000000"));
        assertThat(entity.getClOrdId()).isEqualTo("");
        assertThat(entity.getEarnAmt()).isNull();
        assertThat(entity.getEarnApr()).isNull();
        assertThat(entity.getExecType()).isEqualTo("");
        assertThat(entity.getFee()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(entity.getFillFwdPx()).isNull();
        assertThat(entity.getFillIdxPx()).isNull();
        assertThat(entity.getFillMarkPx()).isNull();
        assertThat(entity.getFillMarkVol()).isNull();
        assertThat(entity.getFillPxUsd()).isNull();
        assertThat(entity.getFillPxVol()).isNull();
        assertThat(entity.getFillTime()).isEqualTo(Instant.ofEpochMilli(1793232000832L));
        assertThat(entity.getFrom()).isEqualTo("");
        assertThat(entity.getInstId()).isEqualTo("GRAM-USD_UM_XPERP-310620");
        assertThat(entity.getInstType()).isEqualTo("FUTURES");
        assertThat(entity.getInterest()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(entity.getMgnMode()).isEqualTo("isolated");
        assertThat(entity.getNotes()).isEqualTo("");
        assertThat(entity.getOrdId()).isNull();
        assertThat(entity.getPnl()).isEqualByComparingTo(new BigDecimal("-0.00600915"));
        assertThat(entity.getPosBal()).isEqualByComparingTo(new BigDecimal("42.8248581833333333"));
        assertThat(entity.getPosBalChg()).isEqualByComparingTo(new BigDecimal("-0.0060091500000000"));
        assertThat(entity.getPx()).isEqualByComparingTo(new BigDecimal("1.239"));
        assertThat(entity.getSubType()).isEqualTo("173");
        assertThat(entity.getSz()).isEqualByComparingTo(new BigDecimal("97"));
        assertThat(entity.getTag()).isEqualTo("");
        assertThat(entity.getTo()).isEqualTo("");
        assertThat(entity.getTradeId()).isEqualTo(0L);
        assertThat(entity.getTs()).isEqualTo(Instant.ofEpochMilli(1793232000832L));
        assertThat(entity.getType()).isEqualTo("8");
    }

    @Test
    void testMapOkxBillDataSample2() {
        OkxBillData data = new OkxBillData();
        data.setBillId("4051013172775882800");
        data.setCcy("USDC");
        data.setBal("81.7712695459343799");
        data.setBalChg("0.0000000000000000");
        data.setFee("0");
        data.setFillTime("1793232000571");
        data.setInstId("ETH-USD_UM_XPERP-310404");
        data.setInstType("FUTURES");
        data.setInterest("0");
        data.setMgnMode("isolated");
        data.setPnl("-0.01885744");
        data.setPosBal("67.3600177336824038");
        data.setPosBalChg("-0.0188574400000000");
        data.setPx("2357.18");
        data.setSubType("173");
        data.setSz("80");
        data.setTradeId("0");
        data.setTs("1793232000571");
        data.setType("8");

        OkxBillEntity entity = mapper.toEntity(data);

        assertThat(entity).isNotNull();
        assertThat(entity.getBillId()).isEqualTo(4051013172775882800L);
        assertThat(entity.getInstId()).isEqualTo("ETH-USD_UM_XPERP-310404");
        assertThat(entity.getPx()).isEqualByComparingTo(new BigDecimal("2357.18"));
        assertThat(entity.getSz()).isEqualByComparingTo(new BigDecimal("80"));
        assertThat(entity.getPnl()).isEqualByComparingTo(new BigDecimal("-0.01885744"));
        assertThat(entity.getPosBal()).isEqualByComparingTo(new BigDecimal("67.3600177336824038"));
    }

    @Test
    void testMapBillDetailsData() {
        BillDetailsData details = new BillDetailsData();
        details.setBillId("4050529984391680002");
        details.setCcy("USDC");
        details.setBal("81.7712695459343799");
        details.setInstId("GRAM-USD_UM_XPERP-310620");
        details.setInstType("FUTURES");
        details.setTs("1793217600435");
        details.setFillTime("1793217600435");
        details.setPnl("-0.00604795");
        details.setPx("1.247");
        details.setSz("97");
        details.setSubType("173");
        details.setType("8");

        OkxBillEntity entity = mapper.toEntity(details);

        assertThat(entity).isNotNull();
        assertThat(entity.getBillId()).isEqualTo(4050529984391680002L);
        assertThat(entity.getInstId()).isEqualTo("GRAM-USD_UM_XPERP-310620");
        assertThat(entity.getPx()).isEqualByComparingTo(new BigDecimal("1.247"));
        assertThat(entity.getTs()).isEqualTo(Instant.ofEpochMilli(1793217600435L));
    }

    @Test
    void testMapEmptyFieldsToNull() {
        OkxBillData data = new OkxBillData();
        data.setBillId("12345");
        data.setBal("");
        data.setBalChg("");
        data.setEarnAmt("");
        data.setEarnApr("");
        data.setFee("");
        data.setFillFwdPx("");
        data.setFillIdxPx("");
        data.setFillMarkPx("");
        data.setFillMarkVol("");
        data.setFillPxUsd("");
        data.setFillPxVol("");
        data.setFillTime("");
        data.setInterest("");
        data.setOrdId("");
        data.setPnl("");
        data.setPosBal("");
        data.setPosBalChg("");
        data.setPx("");
        data.setSz("");
        data.setTradeId("");
        data.setTs("");

        OkxBillEntity entity = mapper.toEntity(data);

        assertThat(entity.getBillId()).isEqualTo(12345L);
        assertThat(entity.getBal()).isNull();
        assertThat(entity.getBalChg()).isNull();
        assertThat(entity.getEarnAmt()).isNull();
        assertThat(entity.getEarnApr()).isNull();
        assertThat(entity.getFee()).isNull();
        assertThat(entity.getFillFwdPx()).isNull();
        assertThat(entity.getFillIdxPx()).isNull();
        assertThat(entity.getFillMarkPx()).isNull();
        assertThat(entity.getFillMarkVol()).isNull();
        assertThat(entity.getFillPxUsd()).isNull();
        assertThat(entity.getFillPxVol()).isNull();
        assertThat(entity.getFillTime()).isNull();
        assertThat(entity.getInterest()).isNull();
        assertThat(entity.getOrdId()).isNull();
        assertThat(entity.getPnl()).isNull();
        assertThat(entity.getPosBal()).isNull();
        assertThat(entity.getPosBalChg()).isNull();
        assertThat(entity.getPx()).isNull();
        assertThat(entity.getSz()).isNull();
        assertThat(entity.getTradeId()).isNull();
        assertThat(entity.getTs()).isNull();
    }

    @Test
    void testMapList() {
        OkxBillData data = new OkxBillData();
        data.setBillId("2001");
        List<OkxBillEntity> entities = mapper.toEntities(List.of(data));
        assertThat(entities).hasSize(1);
        assertThat(entities.getFirst().getBillId()).isEqualTo(2001L);

        BillDetailsData details = new BillDetailsData();
        details.setBillId("2002");
        List<OkxBillEntity> detailsEntities = mapper.toEntitiesFromDetails(List.of(details));
        assertThat(detailsEntities).hasSize(1);
        assertThat(detailsEntities.getFirst().getBillId()).isEqualTo(2002L);
    }

    @Test
    void testMapFromMap() {
        Map<String, String> data = new HashMap<>();
        data.put("billId", "8888");
        data.put("bal", "100.5");
        data.put("instId", "BTC-USDC");
        data.put("ts", "1793232000832");
        data.put("type", "8");
        data.put("subType", "173");

        OkxBillEntity entity = mapper.toEntity(data);

        assertThat(entity.getBillId()).isEqualTo(8888L);
        assertThat(entity.getBal()).isEqualByComparingTo(new BigDecimal("100.5"));
        assertThat(entity.getInstId()).isEqualTo("BTC-USDC");
        assertThat(entity.getTs()).isEqualTo(Instant.ofEpochMilli(1793232000832L));
        assertThat(entity.getType()).isEqualTo("8");
        assertThat(entity.getSubType()).isEqualTo("173");
    }
}
