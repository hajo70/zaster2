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
@Table(name = "bitget_future_order")
@Getter
@Setter
@ToString
public class BitgetFutureOrderEntity {
    @Id
    private Long orderId;

    @Column(length = 20)
    private String symbol;

    @Column(precision = 20, scale = 10)
    private BigDecimal size;

    @Column(length = 50)
    private String clientOid;

    @Column(precision = 20, scale = 10)
    private BigDecimal baseVolume;

    @Column(precision = 20, scale = 10)
    private BigDecimal fee;

    @Column(precision = 20, scale = 10)
    private BigDecimal price;

    @Column(precision = 20, scale = 10)
    private BigDecimal priceAvg;

    @Column(length = 20)
    private String status;

    @Column(length = 10)
    private String side;

    @Column(length = 10, name = "forceType")
    private String force;

    @Column(precision = 20, scale = 10)
    private BigDecimal totalProfits;

    @Column(length = 10)
    private String posSide;

    @Column(length = 10)
    private String marginCoin;

    @Column(precision = 20, scale = 10)
    private BigDecimal quoteVolume;

    private Integer leverage;

    @Column(length = 10)
    private String marginMode;

    @Column(length = 20)
    private String enterPointSource;

    @Column(length = 20)
    private String tradeSide;

    @Column(length = 20)
    private String posMode;

    @Column(length = 20)
    private String orderType;

    @Column(length = 20)
    private String orderSource;

    @Column(precision = 20, scale = 10)
    private BigDecimal presetStopSurplusPrice;

    @Column(precision = 20, scale = 10)
    private BigDecimal presetStopLossPrice;

    @Column(precision = 20, scale = 10)
    private BigDecimal posAvg;

    @Column(length = 10)
    private String reduceOnly;

    @Column(columnDefinition = "DATETIME(0)", nullable = false)
    private Instant createdAt;

    @Column(columnDefinition = "DATETIME(0)", nullable = false)
    private Instant updatedAt;

    public int hashCode() {
        return orderId != null ? orderId.hashCode() : super.hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        BitgetFutureOrderEntity that = (BitgetFutureOrderEntity) other;
        if (orderId != null) {
            return orderId.equals(that.orderId);
        }
        return false;
    }
}
