package de.spricom.zaster2.repository;

import de.spricom.zaster2.entities.PostbankGiroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostbankGiroRepository extends JpaRepository<PostbankGiroEntity, Long> {

    @Query("""
            SELECT count(e) > 0 FROM PostbankGiroEntity e
            WHERE e.bookingDate = :#{#booking.bookingDate}
            AND e.valueDate = :#{#booking.valueDate}
            AND (e.transactionType = :#{#booking.transactionType} OR (e.transactionType IS NULL AND :#{#booking.transactionType} IS NULL))
            AND (e.partnerName = :#{#booking.partnerName} OR (e.partnerName IS NULL AND :#{#booking.partnerName} IS NULL))
            AND (e.paymentReference = :#{#booking.paymentReference} OR (e.paymentReference IS NULL AND :#{#booking.paymentReference} IS NULL))
            AND (e.bic = :#{#booking.bic} OR (e.bic IS NULL AND :#{#booking.bic} IS NULL))
            AND (e.customerReference = :#{#booking.customerReference} OR (e.customerReference IS NULL AND :#{#booking.customerReference} IS NULL))
            AND (e.mandateReference = :#{#booking.mandateReference} OR (e.mandateReference IS NULL AND :#{#booking.mandateReference} IS NULL))
            AND (e.creditorId = :#{#booking.creditorId} OR (e.creditorId IS NULL AND :#{#booking.creditorId} IS NULL))
            AND (e.foreignFees = :#{#booking.foreignFees} OR (e.foreignFees IS NULL AND :#{#booking.foreignFees} IS NULL))
            AND e.amount = :#{#booking.amount}
            AND (e.deviatingRecipient = :#{#booking.deviatingRecipient} OR (e.deviatingRecipient IS NULL AND :#{#booking.deviatingRecipient} IS NULL))
            AND (e.currency = :#{#booking.currency} OR (e.currency IS NULL AND :#{#booking.currency} IS NULL))
            """)
    boolean existsByContent(@Param("booking") PostbankGiroEntity booking);
}
