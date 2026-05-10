package de.spricom.zaster2.repository;

import de.spricom.zaster2.entities.BitgetFutureOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

public interface BitgetFutureOrderRepository extends JpaRepository<BitgetFutureOrderEntity, Long> {

    @Query("SELECT MAX(updatedAt) FROM BitgetFutureOrderEntity")
    Instant findMaxUpdatedAt();
}
