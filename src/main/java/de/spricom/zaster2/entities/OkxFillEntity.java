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
@Table(name = "okx_fill")
@Getter
@Setter
@ToString
public class OkxFillEntity {

    @Id
    private Long billId;

    @Column(length = 20)
    private String instType;

    @Column(length = 50)
    private String instId;

    private Long tradeId;

    private Long ordId;

    @Column(length = 50)
    private String clOrdId;

    @Column(length = 32)
    private String tag;

    @Column(precision = 20, scale = 10)
    private BigDecimal fillPx;

    @Column(precision = 20, scale = 10)
    private BigDecimal fillSz;

    @Column(length = 10)
    private String side;

    @Column(length = 10)
    private String posSide;

    @Column(length = 10)
    private String execType;

    @Column(length = 20)
    private String feeCcy;

    @Column(precision = 20, scale = 10)
    private BigDecimal fee;

    @Column(columnDefinition = "DATETIME(0)", nullable = false)
    private Instant ts;

    @Column(columnDefinition = "DATETIME(0)", nullable = false)
    private Instant fillTime;

    @Column(precision = 20, scale = 10)
    private BigDecimal fillPxVol;

    @Column(precision = 20, scale = 10)
    private BigDecimal fillPxUsd;

    @Column(precision = 20, scale = 10)
    private BigDecimal fillMarkVol;

    @Column(precision = 20, scale = 10)
    private BigDecimal fillMarkPx;

    @Column(precision = 20, scale = 10)
    private BigDecimal tradeQuoteQty;

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

        OkxFillEntity that = (OkxFillEntity) other;
        if (billId != null) {
            return billId.equals(that.billId);
        }
        return false;
    }
}
