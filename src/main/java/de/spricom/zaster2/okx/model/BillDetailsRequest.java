package de.spricom.zaster2.okx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Request parameters for OKX bills details (last 3 months).
 * Endpoint: GET /api/v5/account/bills-archive
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDetailsRequest {

    private String instType;
    private String instId;
    private String ccy;
    private String mgnMode;
    private String ctType;
    private String type;
    private String subType;
    private String after;
    private String before;
    private String begin;
    private String end;
    private String limit;

    /**
     * Builds the query string representing the non-null, non-blank request parameters.
     *
     * @return query string prefixed with '?' if parameters exist, or empty string if none.
     */
    public String toQueryString() {
        List<String> params = new ArrayList<>();
        addQueryParam(params, "instType", instType);
        addQueryParam(params, "instId", instId);
        addQueryParam(params, "ccy", ccy);
        addQueryParam(params, "mgnMode", mgnMode);
        addQueryParam(params, "ctType", ctType);
        addQueryParam(params, "type", type);
        addQueryParam(params, "subType", subType);
        addQueryParam(params, "after", after);
        addQueryParam(params, "before", before);
        addQueryParam(params, "begin", begin);
        addQueryParam(params, "end", end);
        addQueryParam(params, "limit", limit);

        if (params.isEmpty()) {
            return "";
        }
        return "?" + String.join("&", params);
    }

    private void addQueryParam(List<String> params, String key, String value) {
        if (value != null && !value.isBlank()) {
            params.add(key + "=" + value);
        }
    }
}
