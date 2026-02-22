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
@Table(name = "bitget_tax_spot_record")
@Getter
@Setter
@ToString
public class BitgetTaxSpotRecordEntity {
    @Id
    private Long id;
    @Column(length = 12)
    private String coin;
    @Column(length = 64)
    private String spotTaxType;
    @Column(precision = 16, scale = 10)
    private BigDecimal amount;
    @Column(precision = 16, scale = 10)
    private BigDecimal fee;
    @Column(precision = 16, scale = 10)
    private BigDecimal balance;
    @Column(columnDefinition = "DATETIME(0)", nullable = false)
    private LocalDateTime ts;
    private Long bizOrderId;

    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        BitgetTaxSpotRecordEntity that = (BitgetTaxSpotRecordEntity) other;
        if (id != null) {
            return id.equals(that.id);
        }
        return false;
    }
}
