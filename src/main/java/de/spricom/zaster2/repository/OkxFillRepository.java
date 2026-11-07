package de.spricom.zaster2.repository;

import de.spricom.zaster2.entities.OkxFillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

public interface OkxFillRepository extends JpaRepository<OkxFillEntity, Long> {

    @Query("SELECT MAX(fillTime) FROM OkxFillEntity")
    Instant findMaxFillTime();

    @Query("SELECT MAX(ts) FROM OkxFillEntity")
    Instant findMaxTs();

    @Query("SELECT MAX(billId) FROM OkxFillEntity")
    Long findMaxBillId();
}
