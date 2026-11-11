package de.spricom.zaster2.okx.service;

import de.spricom.zaster2.okx.OkxProperties;
import de.spricom.zaster2.okx.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Slf4j
@Service
public class OkxApiClient {

    private final RestClient restClient;
    private final OkxProperties okxProperties;
    private final OkxSignatureGenerator signatureGenerator;
    private final ObjectMapper objectMapper;

    public OkxApiClient(RestClient.Builder restClientBuilder,
                        OkxProperties okxProperties,
                        OkxSignatureGenerator signatureGenerator,
                        ObjectMapper objectMapper) {
        this.okxProperties = okxProperties;
        this.signatureGenerator = signatureGenerator;
        this.objectMapper = objectMapper;
        this.restClient = restClientBuilder
                .baseUrl(okxProperties.baseUrl())
                .build();
    }

    /**
     * Retrieve recently filled trade details for the last three months.
     * Endpoint: GET /api/v5/trade/fills-history
     *
     * @param instType instrument type, e.g. SPOT, MARGIN, SWAP, FUTURES, OPTION (required by OKX)
     * @param instId   instrument ID; all instruments of the given type if {@code null} or blank
     */
    public OkxResponse<OkxFill> getFillsHistory(String instType, String instId) {
        return getFillsHistory(instType, instId, null);
    }

    /**
     * Retrieve recently filled trade details for the last three months.
     * Endpoint: GET /api/v5/trade/fills-history
     *
     * @param limit maximum number of results, 1-100 (OKX default: 100)
     */
    public OkxResponse<OkxFill> getFillsHistory(String instType, String instId, String limit) {
        String path = "/api/v5/trade/fills-history?instType=" + instType;
        if (instId != null && !instId.isBlank()) {
            path += "&instId=" + instId;
        }
        if (limit != null && !limit.isBlank()) {
            path += "&limit=" + limit;
        }
        return executeGet(path, new ParameterizedTypeReference<OkxResponse<OkxFill>>() {
        });
    }

    /**
     * Retrieve recently filled trade details for all instruments of the given type.
     * Endpoint: GET /api/v5/trade/fills-history
     */
    public OkxResponse<OkxFill> getFillsHistory(String instType) {
        return getFillsHistory(instType, null, null);
    }

    /**
     * Retrieve bill details for the last 3 months.
     * Endpoint: GET /api/v5/account/bills-archive
     *
     * @param request query parameters for filtering bills
     * @return list of bill details data, or empty list if none
     */
    public List<BillDetailsData> getBillsDetails(BillDetailsRequest request) {
        String queryString = (request != null) ? request.toQueryString() : "";
        String path = "/api/v5/account/bills-archive" + queryString;
        return executeGetData(path, BillDetailsData.class);
    }

    /**
     * Retrieve server time.
     * Endpoint: GET /api/v5/public/time
     */
    public OkxResponse<OkxServerTime> getServerTime() {
        String path = "/api/v5/public/time";
        log.debug("Sending public GET request to OKX path: {}", path);
        return restClient.get()
                .uri(path)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<OkxResponse<OkxServerTime>>() {
                });
    }

    /**
     * Retrieve timestamp from OKX server time endpoint instead of local time.
     */
    public String getServerTimestamp() {
        OkxResponse<OkxServerTime> response = getServerTime();
        if (response != null && response.isSuccess() && response.getData() != null && !response.getData().isEmpty()) {
            OkxServerTime serverTime = response.getData().getFirst();
            if (serverTime != null && serverTime.getTs() != null && !serverTime.getTs().isBlank()) {
                return signatureGenerator.formatTimestamp(serverTime.getTs());
            }
        }
        throw new IllegalStateException("Failed to retrieve server timestamp from OKX API: " + response);
    }

    public <T> List<T> executeGetData(String requestPath, Class<T> dataClass) {
        // Dynamically build ParameterizedTypeReference<OkxResponse<T>> using ResolvableType
        ParameterizedTypeReference<OkxResponse<T>> typeReference =
                (ParameterizedTypeReference<OkxResponse<T>>) (ParameterizedTypeReference<?>)
                        ParameterizedTypeReference.forType(
                                ResolvableType.forClassWithGenerics(OkxResponse.class, dataClass).getType()
                        );
        OkxResponse<T> response = executeGet(requestPath, typeReference);
        if (response != null && response.isSuccess() && response.getData() != null) {
            return response.getData();
        } else {
            throw new OkxApiException("GET", requestPath, response);
        }
    }

    /**
     * Execute a signed GET request to the OKX API.
     */
    public <T> OkxResponse<T> executeGet(String requestPath, ParameterizedTypeReference<OkxResponse<T>> typeReference) {
        String timestamp = getServerTimestamp();
        String signature = signatureGenerator.generateSignature(
                timestamp,
                "GET",
                requestPath,
                "",
                okxProperties.read().secretKey()
        );

        log.debug("Sending GET request to OKX path: {}", requestPath);

        return restClient.get()
                .uri(requestPath)
                .headers(headers -> applyAuthHeaders(headers, timestamp, signature))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(typeReference);
    }

    private void applyAuthHeaders(HttpHeaders headers, String timestamp, String signature) {
        headers.set("OK-ACCESS-KEY", okxProperties.read().apiKey());
        headers.set("OK-ACCESS-SIGN", signature);
        headers.set("OK-ACCESS-TIMESTAMP", timestamp);
        headers.set("OK-ACCESS-PASSPHRASE", okxProperties.read().passphrase());
        headers.set("Content-Type", "application/json");
    }
}
