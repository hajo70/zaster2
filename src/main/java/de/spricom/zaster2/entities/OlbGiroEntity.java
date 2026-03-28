package de.spricom.zaster2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "olb_giro")
@Getter
@Setter
@ToString
public class OlbGiroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private FileImportHistoryEntity fileImportHistory;

    @Column(length = 34)
    private String accountHolder;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private LocalDate valueDate;

    @Column(length = 255)
    private String partnerName;

    @Column(length = 34)
    private String iban;

    @Column(length = 11)
    private String bic;

    @Column(length = 511)
    private String paymentReference;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(length = 3)
    private String currency;

    @Column(length = 63)
    private String customerReference;

    @Column(length = 63)
    private String bankReference;

    @Column(length = 31)
    private String primaryNota;

    @Column(length = 15)
    private String transactionCode;

    @Column(length = 63)
    private String transactionText;

    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        OlbGiroEntity that = (OlbGiroEntity) other;
        if (id != null) {
            return id.equals(that.id);
        }
        return false;
    }
}
