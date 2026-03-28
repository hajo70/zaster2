package de.spricom.zaster2.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.spricom.zaster2.dto.OlbGiroCsvDto;
import de.spricom.zaster2.entities.FileImportHistoryEntity;
import de.spricom.zaster2.entities.OlbGiroEntity;
import de.spricom.zaster2.mapper.OlbGiroMapper;
import de.spricom.zaster2.repository.FileImportHistoryRepository;
import de.spricom.zaster2.repository.OlbGiroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OlbGiroImportService {

    private final OlbGiroRepository olbGiroRepository;
    private final FileImportHistoryRepository fileImportHistoryRepository;
    private final OlbGiroMapper olbGiroMapper;

    @Transactional
    public void importCsv(String filename, InputStream inputStream) throws IOException {
        byte[] content = inputStream.readAllBytes();
        String md5 = DigestUtils.md5DigestAsHex(content);

        if (fileImportHistoryRepository.findByMd5(md5).isPresent()) {
            log.info("File {} with MD5 {} already imported, skipping.", filename, md5);
            return;
        }

        FileImportHistoryEntity history = new FileImportHistoryEntity();
        history.setFilename(filename);
        history.setMd5(md5);
        history = fileImportHistoryRepository.save(history);

        String csv = new String(content, StandardCharsets.ISO_8859_1);

        try (Reader reader = new StringReader(csv)) {
            CsvToBean<OlbGiroCsvDto> csvToBean = new CsvToBeanBuilder<OlbGiroCsvDto>(reader)
                    .withType(OlbGiroCsvDto.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withStrictQuotes(false)
                    .withThrowExceptions(false)
                    .withVerifier(bean -> bean.getBookingDate() != null)
                    .build();

            List<OlbGiroCsvDto> dtos = csvToBean.parse();
            csvToBean.getCapturedExceptions().forEach(e -> log.debug("Skipping invalid CSV line: {}", e.getMessage()));
            final FileImportHistoryEntity finalHistory = history;
            List<OlbGiroEntity> entities = dtos.stream()
                    .map(dto -> {
                        OlbGiroEntity entity = olbGiroMapper.toEntity(dto);
                        entity.setFileImportHistory(finalHistory);
                        return entity;
                    })
                    .filter(entity -> {
                        if (olbGiroRepository.existsByContent(entity)) {
                            log.info("Transaction already exists, skipping: {}", entity);
                            return false;
                        }
                        return true;
                    })
                    .collect(Collectors.toList());

            if (!entities.isEmpty()) {
                olbGiroRepository.saveAll(entities);
            }
            log.info("Imported {} records from {}", entities.size(), filename);
        }
    }
}
