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
@Table(name = "bitget_future_position")
@Getter
@Setter
@ToString
public class BitgetFuturePositionEntity {
    @Id
    private Long positionId;

    @Column(length = 20)
    private String symbol;

    @Column(length = 10)
    private String marginCoin;

    @Column(length = 10)
    private String holdSide;

    @Column(length = 20)
    private String posMode;

    @Column(precision = 20, scale = 10)
    private BigDecimal openAvgPrice;

    @Column(precision = 20, scale = 10)
    private BigDecimal closeAvgPrice;

    @Column(length = 10)
    private String marginMode;

    @Column(precision = 20, scale = 10)
    private BigDecimal openTotalPos;

    @Column(precision = 20, scale = 10)
    private BigDecimal closeTotalPos;

    @Column(precision = 20, scale = 10)
    private BigDecimal pnl;

    @Column(precision = 20, scale = 10)
    private BigDecimal netProfit;

    @Column(precision = 20, scale = 10)
    private BigDecimal totalFunding;

    @Column(precision = 20, scale = 10)
    private BigDecimal openFee;

    @Column(precision = 20, scale = 10)
    private BigDecimal closeFee;

    @Column(columnDefinition = "DATETIME(0)", nullable = false)
    private Instant createdAt;

    @Column(columnDefinition = "DATETIME(0)", nullable = false)
    private Instant updatedAt;

    public int hashCode() {
        return positionId != null ? positionId.hashCode() : super.hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        BitgetFuturePositionEntity that = (BitgetFuturePositionEntity) other;
        if (positionId != null) {
            return positionId.equals(that.positionId);
        }
        return false;
    }
}
