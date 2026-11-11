package de.spricom.zaster2.okx.service;

import de.spricom.zaster2.okx.model.OkxResponse;
import lombok.Getter;

@Getter
public class OkxApiException extends RuntimeException {
    private final String requestType;
    private final String requestPath;
    private final OkxResponse<?> response;

    public OkxApiException(String requestType, String requestPath, OkxResponse<?> response) {
        super(requestType + " " + requestPath + " returned: " + (response != null ? response.getMsg() : "Empty response"));
        this.requestType = requestType;
        this.requestPath = requestPath;
        this.response = response;
    }
}
