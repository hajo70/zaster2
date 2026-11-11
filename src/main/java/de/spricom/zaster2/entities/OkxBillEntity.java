package de.spricom.zaster2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "okx_bill")
@Getter
@Setter
@ToString
public class OkxBillEntity {

    @Id
    private Long billId;

    @Column(length = 20)
    private String ccy;

    @Column(precision = 30, scale = 16)
    private BigDecimal bal;

    @Column(precision = 30, scale = 16)
    private BigDecimal balChg;

    @Column(length = 50)
    private String clOrdId;

    @Column(precision = 30, scale = 16)
    private BigDecimal earnAmt;

    @Column(precision = 20, scale = 10)
    private BigDecimal earnApr;

    @Column(length = 10)
    private String execType;

    @Column(precision = 30, scale = 16)
    private BigDecimal fee;

    @Column(precision = 20, scale = 10)
    private BigDecimal fillFwdPx;

    @Column(precision = 20, scale = 10)
    private BigDecimal fillIdxPx;

    @Column(precision = 20, scale = 10)
    private BigDecimal fillMarkPx;

    @Column(precision = 20, scale = 10)
    private BigDecimal fillMarkVol;

    @Column(precision = 20, scale = 10)
    private BigDecimal fillPxUsd;

    @Column(precision = 20, scale = 10)
    private BigDecimal fillPxVol;

    @Column(columnDefinition = "DATETIME(0)")
    private Instant fillTime;

    @Column(name = "`from`", length = 20)
    private String from;

    @Column(length = 50)
    private String instId;

    @Column(length = 20)
    private String instType;

    @Column(precision = 30, scale = 16)
    private BigDecimal interest;

    @Column(length = 20)
    private String mgnMode;

    @Column(length = 255)
    private String notes;

    private Long ordId;

    @Column(precision = 30, scale = 16)
    private BigDecimal pnl;

    @Column(precision = 30, scale = 16)
    private BigDecimal posBal;

    @Column(precision = 30, scale = 16)
    private BigDecimal posBalChg;

    @Column(precision = 20, scale = 10)
    private BigDecimal px;

    @Column(length = 20)
    private String subType;

    @Column(precision = 30, scale = 16)
    private BigDecimal sz;

    @Column(length = 32)
    private String tag;

    @Column(name = "`to`", length = 20)
    private String to;

    private Long tradeId;

    @Column(columnDefinition = "DATETIME(0)", nullable = false)
    private Instant ts;

    @Column(length = 20)
    private String type;

    @Override
    public int hashCode() {
        return billId != null ? billId.hashCode() : super.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        OkxBillEntity that = (OkxBillEntity) other;
        if (billId != null) {
            return billId.equals(that.billId);
        }
        return false;
    }
}
