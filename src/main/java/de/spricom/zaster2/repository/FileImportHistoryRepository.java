package de.spricom.zaster2.repository;

import de.spricom.zaster2.entities.FileImportHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileImportHistoryRepository extends JpaRepository<FileImportHistoryEntity, Long> {
    Optional<FileImportHistoryEntity> findByMd5(String md5);
}
