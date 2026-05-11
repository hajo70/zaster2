package de.spricom.zaster2.repository;

import de.spricom.zaster2.entities.BitgetTaxFutureRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

public interface BitgetTaxFutureRecordRepository extends JpaRepository<BitgetTaxFutureRecordEntity, Long> {

    @Query("SELECT MAX(ts) FROM BitgetTaxFutureRecordEntity")
    Instant findMaxTimestamp();
}
