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
@Table(name = "postbank_giro")
@Getter
@Setter
@ToString
public class PostbankGiroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private FileImportHistoryEntity fileImportHistory;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private LocalDate valueDate;

    @Column(length = 63)
    private String transactionType;

    @Column(length = 255)
    private String partnerName;

    @Column(length = 511)
    private String paymentReference;

    @Column(length = 34)
    private String iban;

    @Column(length = 11)
    private String bic;

    @Column(length = 63)
    private String customerReference;

    @Column(length = 63)
    private String mandateReference;

    @Column(length = 31)
    private String creditorId;

    @Column(precision = 12, scale = 2)
    private BigDecimal foreignFees;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(length = 255)
    private String deviatingRecipient;

    @Column(length = 3)
    private String currency;

    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        PostbankGiroEntity that = (PostbankGiroEntity) other;
        if (id != null) {
            return id.equals(that.id);
        }
        return false;
    }
}
