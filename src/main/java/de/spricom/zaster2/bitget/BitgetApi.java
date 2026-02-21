package de.spricom.zaster2.bitget;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bitget.openapi.common.client.BitgetRestClient;
import com.bitget.openapi.common.domain.ClientParameter;
import com.bitget.openapi.common.enums.SupportedLocaleEnum;
import com.bitget.openapi.dto.response.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class BitgetApi implements InitializingBean {

    private final BitgetProperties props;

    private BitgetRestClient client;

    @Override
    public void afterPropertiesSet() {
        client = BitgetRestClient.builder()
                .configuration(clientParameter())
                .build();
    }

    private ClientParameter clientParameter() {
        return ClientParameter.builder()
                .apiKey(props.apiKey())
                .secretKey(props.secretKey())
                .passphrase(props.passPhrase())
                .baseUrl(props.url())
                .locale(SupportedLocaleEnum.EN_US.getName())
                .build();
    }

    public void taxSpotRecord(Instant startTime, Instant endTime) throws IOException {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("startTime", startTime.getEpochSecond() + "000");
        paramMap.put("endTime", endTime.getEpochSecond() + "000");
        ResponseResult response = client.bitget().v2().request().get("/api/v2/tax/spot-record", paramMap);
        log.info("Spot Transaction Records between {}  and {}:\n{}", startTime, endTime, render(response));
    }

    public void taxFutureRecord(Instant startTime, Instant endTime) throws IOException {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("startTime", startTime.getEpochSecond() + "000");
        paramMap.put("endTime", endTime.getEpochSecond() + "000");
        ResponseResult response = client.bitget().v2().request().get("/api/v2/tax/future-record", paramMap);
        log.info("Future Transaction Records between {}  and {}:\n{}", startTime, endTime, render(response));
    }

    @NotNull
    private static String render(ResponseResult result) {
        return JSON.toJSONString(result, SerializerFeature.PrettyFormat);
    }
}
