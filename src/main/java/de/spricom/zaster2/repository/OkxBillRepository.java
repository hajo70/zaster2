package de.spricom.zaster2.repository;

import de.spricom.zaster2.entities.OkxBillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

public interface OkxBillRepository extends JpaRepository<OkxBillEntity, Long> {

    @Query("SELECT MAX(ts) FROM OkxBillEntity")
    Instant findMaxTs();

    @Query("SELECT MAX(billId) FROM OkxBillEntity")
    Long findMaxBillId();

    @Query("SELECT MAX(fillTime) FROM OkxBillEntity")
    Instant findMaxFillTime();
}
