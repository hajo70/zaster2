package de.spricom.zaster2.okx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * OKX trade fill (execution) as returned by the fills endpoints.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OkxFill {

    private String instType;
    private String instId;
    /**
     * Deprecated by OKX, use {@link #billId} instead.
     */
    private String tradeId;
    private String ordId;
    private String clOrdId;
    private String billId;
    private String tag;
    private String fillPx;
    private String fillSz;
    private String side;
    private String posSide;
    private String execType;
    private String feeCcy;
    private String fee;
    private String ts;
    private String fillTime;
    private String fillPxVol;
    private String fillPxUsd;
    private String fillMarkVol;
    private String fillMarkPx;
    private String tradeQuoteQty;
}
