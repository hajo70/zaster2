package de.spricom.zaster2.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.spricom.zaster2.dto.PostbankGiroCsvDto;
import de.spricom.zaster2.entities.FileImportHistoryEntity;
import de.spricom.zaster2.entities.PostbankGiroEntity;
import de.spricom.zaster2.mapper.PostbankGiroMapper;
import de.spricom.zaster2.repository.FileImportHistoryRepository;
import de.spricom.zaster2.repository.PostbankGiroRepository;
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
public class PostbankGiroImportService {

    private final PostbankGiroRepository postbankGiroRepository;
    private final FileImportHistoryRepository fileImportHistoryRepository;
    private final PostbankGiroMapper postbankGiroMapper;

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

        String csv = new String(content, StandardCharsets.UTF_8);
        csv = csv.replace(";Verwendungszweck;IBAN;BIC;", ";Verwendungszweck;IBAN / Kontonummer;BIC;");

        try (Reader reader = new StringReader(csv)) {
            CsvToBean<PostbankGiroCsvDto> csvToBean = new CsvToBeanBuilder<PostbankGiroCsvDto>(reader)
                    .withType(PostbankGiroCsvDto.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withStrictQuotes(false)
                    .withSkipLines(7)
                    .withThrowExceptions(false)
                    .withVerifier(bean -> bean.getBookingDate() != null)
                    .build();

            List<PostbankGiroCsvDto> dtos = csvToBean.parse();
            csvToBean.getCapturedExceptions().forEach(e -> log.debug("Skipping invalid CSV line: {}", e.getMessage()));
            final FileImportHistoryEntity finalHistory = history;
            List<PostbankGiroEntity> entities = dtos.stream()
                    .map(dto -> {
                        PostbankGiroEntity entity = postbankGiroMapper.toEntity(dto);
                        entity.setFileImportHistory(finalHistory);
                        return entity;
                    })
                    .filter(entity -> {
                        if (postbankGiroRepository.existsByContent(entity)) {
                            log.info("Transaction already exists, skipping: {}", entity);
                            return false;
                        }
                        return true;
                    })
                    .collect(Collectors.toList());

            if (!entities.isEmpty()) {
                postbankGiroRepository.saveAll(entities);
            }
            log.info("Imported {} records from {}", entities.size(), filename);
        }
    }
}
