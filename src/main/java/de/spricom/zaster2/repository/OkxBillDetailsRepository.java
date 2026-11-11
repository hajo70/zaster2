package de.spricom.zaster2.repository;

import de.spricom.zaster2.entities.OkxBillDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

public interface OkxBillDetailsRepository extends JpaRepository<OkxBillDetailsEntity, Long> {

    @Query("SELECT MAX(ts) FROM OkxBillDetailsEntity")
    Instant findMaxTs();

    @Query("SELECT MAX(billId) FROM OkxBillDetailsEntity")
    Long findMaxBillId();

    @Query("SELECT MAX(fillTime) FROM OkxBillDetailsEntity")
    Instant findMaxFillTime();
}
