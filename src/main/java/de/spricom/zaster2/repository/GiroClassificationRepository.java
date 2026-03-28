package de.spricom.zaster2.repository;

import de.spricom.zaster2.entities.GiroClassificationEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiroClassificationRepository extends JpaRepository<GiroClassificationEntity, Long> {

    @EntityGraph(attributePaths = {"postbankBookings", "olbBookings"})
    Optional<GiroClassificationEntity> findByName(String name);
}
