package de.spricom.zaster2.okx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * OKX bill detail data as returned by GET /api/v5/account/bills-archive or GET /api/v5/account/bills.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OkxBillData {

    private String billId;
    private String ccy;
    private String bal;
    private String balChg;
    private String clOrdId;
    private String earnAmt;
    private String earnApr;
    private String execType;
    private String fee;
    private String fillFwdPx;
    private String fillIdxPx;
    private String fillMarkPx;
    private String fillMarkVol;
    private String fillPxUsd;
    private String fillPxVol;
    private String fillTime;
    private String from;
    private String instId;
    private String instType;
    private String interest;
    private String mgnMode;
    private String notes;
    private String ordId;
    private String pnl;
    private String posBal;
    private String posBalChg;
    private String px;
    private String subType;
    private String sz;
    private String tag;
    private String to;
    private String tradeId;
    private String ts;
    private String type;
}
