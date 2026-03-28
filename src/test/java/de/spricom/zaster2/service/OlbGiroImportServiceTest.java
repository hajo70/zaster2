package de.spricom.zaster2.service;

import de.spricom.zaster2.dto.OlbGiroCsvDto;
import de.spricom.zaster2.entities.FileImportHistoryEntity;
import de.spricom.zaster2.entities.OlbGiroEntity;
import de.spricom.zaster2.mapper.OlbGiroMapper;
import de.spricom.zaster2.repository.FileImportHistoryRepository;
import de.spricom.zaster2.repository.OlbGiroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OlbGiroImportServiceTest {

    @Mock
    private OlbGiroRepository olbGiroRepository;

    @Mock
    private FileImportHistoryRepository fileImportHistoryRepository;

    @Mock
    private OlbGiroMapper olbGiroMapper;

    @InjectMocks
    private OlbGiroImportService olbGiroImportService;

    private String csvContent;

    @BeforeEach
    void setUp() {
        csvContent = "Inhaberkonto;Buchungsdatum;Valuta;Empfänger/Auftraggeber;IBAN;BIC;Verwendungszweck;Betrag;Währung;Kundenreferenz;Bankreferenz;Primatnota;Transaktions-Code;Transaktions-Text\n" +
                "DE21280200505305248600;24.03.2026;24.03.2026;KAUFLAND SIEGBURG 4910;DE53370400440135990002;COBADEFF370;KAUFLAND SIEGBURG 4910 /WILHELM-OSTWALD-STRA/SIEGB URG/DE/1 2026-03-21T20:10:2 9 FOLGENR.010 VERFALLD.2028 -12 ;-156,38;EUR;NONREF;;0004770;105;LASTS ELCASH";
    }

    @Test
    void testImportCsv() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes(StandardCharsets.ISO_8859_1));
        String filename = "test.csv";

        when(fileImportHistoryRepository.findByMd5(anyString())).thenReturn(Optional.empty());
        when(fileImportHistoryRepository.save(any(FileImportHistoryEntity.class))).thenReturn(new FileImportHistoryEntity());
        
        OlbGiroEntity entity = new OlbGiroEntity();
        entity.setBookingDate(LocalDate.of(2026, 3, 24));
        entity.setAmount(new BigDecimal("-156.38"));
        
        when(olbGiroMapper.toEntity(any(OlbGiroCsvDto.class))).thenReturn(entity);
        when(olbGiroRepository.existsByContent(any(OlbGiroEntity.class))).thenReturn(false);

        olbGiroImportService.importCsv(filename, inputStream);

        verify(olbGiroRepository, times(1)).saveAll(anyList());
        verify(fileImportHistoryRepository, times(1)).save(any(FileImportHistoryEntity.class));
    }
}
