package de.spricom.zaster2.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bitget_tax_future_record")
@Getter
@Setter
@ToString
public class BitgetTaxFutureRecordEntity {
    @Id
    Long id;
    @Column(length = 12)
    String symbol;
    @Column(length = 6)
    String marginCoin;
    @Column(length = 64)
    String futureTaxType;
    @Column(precision = 16, scale = 10)
    BigDecimal amount;
    @Column(precision = 16, scale = 10)
    BigDecimal fee;
    @Column(columnDefinition = "DATETIME(0)", nullable = false)
    LocalDateTime ts;

    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        BitgetTaxFutureRecordEntity that = (BitgetTaxFutureRecordEntity) other;
        if (id != null) {
            return id.equals(that.id);
        }
        return false;
    }
}
