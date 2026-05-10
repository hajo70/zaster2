package de.spricom.zaster2.repository;

import de.spricom.zaster2.entities.BitgetFuturePositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

public interface BitgetFuturePositionRepository extends JpaRepository<BitgetFuturePositionEntity, Long> {

    @Query("SELECT MAX(updatedAt) FROM BitgetFuturePositionEntity")
    Instant findMaxUpdatedAt();
}
