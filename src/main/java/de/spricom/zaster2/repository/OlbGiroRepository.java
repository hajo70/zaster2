package de.spricom.zaster2.repository;

import de.spricom.zaster2.entities.OlbGiroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OlbGiroRepository extends JpaRepository<OlbGiroEntity, Long> {

    @Query("""
            SELECT count(e) > 0 FROM OlbGiroEntity e
            WHERE e.bookingDate = :#{#booking.bookingDate}
            AND e.valueDate = :#{#booking.valueDate}
            AND (e.accountHolder = :#{#booking.accountHolder} OR (e.accountHolder IS NULL AND :#{#booking.accountHolder} IS NULL))
            AND (e.partnerName = :#{#booking.partnerName} OR (e.partnerName IS NULL AND :#{#booking.partnerName} IS NULL))
            AND (e.paymentReference = :#{#booking.paymentReference} OR (e.paymentReference IS NULL AND :#{#booking.paymentReference} IS NULL))
            AND (e.iban = :#{#booking.iban} OR (e.iban IS NULL AND :#{#booking.iban} IS NULL))
            AND (e.bic = :#{#booking.bic} OR (e.bic IS NULL AND :#{#booking.bic} IS NULL))
            AND (e.customerReference = :#{#booking.customerReference} OR (e.customerReference IS NULL AND :#{#booking.customerReference} IS NULL))
            AND (e.bankReference = :#{#booking.bankReference} OR (e.bankReference IS NULL AND :#{#booking.bankReference} IS NULL))
            AND (e.primaryNota = :#{#booking.primaryNota} OR (e.primaryNota IS NULL AND :#{#booking.primaryNota} IS NULL))
            AND (e.transactionCode = :#{#booking.transactionCode} OR (e.transactionCode IS NULL AND :#{#booking.transactionCode} IS NULL))
            AND (e.transactionText = :#{#booking.transactionText} OR (e.transactionText IS NULL AND :#{#booking.transactionText} IS NULL))
            AND e.amount = :#{#booking.amount}
            AND (e.currency = :#{#booking.currency} OR (e.currency IS NULL AND :#{#booking.currency} IS NULL))
            """)
    boolean existsByContent(@Param("booking") OlbGiroEntity booking);
}
