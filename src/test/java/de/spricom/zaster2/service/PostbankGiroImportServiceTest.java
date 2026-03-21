package de.spricom.zaster2.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import de.spricom.zaster2.entities.PostbankGiroEntity;
import de.spricom.zaster2.repository.FileImportHistoryRepository;
import de.spricom.zaster2.repository.PostbankGiroRepository;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class PostbankGiroImportServiceTest {

    @Autowired
    private PostbankGiroImportService importer;

    @MockitoBean
    private PostbankGiroRepository postbankGiroRepository;

    @MockitoBean
    private FileImportHistoryRepository fileImportHistoryRepository;

    @Test
    public void testImportCsv() throws Exception {
        String csvContent = "\n\n\n\n\n\n\n" + // 7 lines to skip
                "Buchungstag;Wert;Umsatzart;Begünstigter / Auftraggeber;Verwendungszweck;IBAN / Kontonummer;BIC;Kundenreferenz;Mandatsreferenz;Gläubiger ID;Fremde Gebühren;Betrag;Abweichender Empfänger;Anzahl der Aufträge;Anzahl der Schecks;Soll;Haben;Währung\n" +
                "31.12.2024;31.12.2024;SEPA Lastschrift;Bitpanda GmbH;Bitpanda Savings Plan savings-1432415539 - Thanks for investing with us;DE12202208000000020906;;Dl7ZzQEJy(bIJHtF;DDM-VFUDWY2EBVHLZAMOXWXQ4P;AT34ZZZ00000066060;;-25,00;;;;-25;;EUR\n" +
                "Kontostand;31.12.2024;;;287,47;EUR";

        when(fileImportHistoryRepository.findByMd5(anyString())).thenReturn(Optional.empty());
        when(fileImportHistoryRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        importer.importCsv("test.csv", new ByteArrayInputStream(csvContent.getBytes(StandardCharsets.UTF_8)));

        verify(postbankGiroRepository, times(1)).saveAll(argThat(list -> {
            int count = 0;
            for (Object o : list) count++;
            return count == 1;
        }));
        verify(fileImportHistoryRepository, times(1)).save(any());
    }

    @Test
    public void testImportCsvWithThousandsSeparator() throws Exception {
        String csvContent = "\n\n\n\n\n\n\n" + // 7 lines to skip
                "Buchungstag;Wert;Umsatzart;Begünstigter / Auftraggeber;Verwendungszweck;IBAN / Kontonummer;BIC;Kundenreferenz;Mandatsreferenz;Gläubiger ID;Fremde Gebühren;Betrag;Abweichender Empfänger;Anzahl der Aufträge;Anzahl der Schecks;Soll;Haben;Währung\n" +
                "31.12.2024;31.12.2024;SEPA Lastschrift;Bitpanda GmbH;Bitpanda Savings Plan;DE12202208000000020906;;Dl7ZzQEJy;DDM-VFUDWY2;AT34ZZZ00000066060;;3.000,50;;;;-25;;EUR\n";

        when(fileImportHistoryRepository.findByMd5(anyString())).thenReturn(Optional.empty());
        when(fileImportHistoryRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        importer.importCsv("test_thousands.csv", new ByteArrayInputStream(csvContent.getBytes(StandardCharsets.UTF_8)));

        verify(postbankGiroRepository, times(1)).saveAll(argThat(list -> {
            PostbankGiroEntity entity = (PostbankGiroEntity) list.iterator().next();
            System.out.println("[DEBUG_LOG] Amount: " + entity.getAmount());
            return entity.getAmount().compareTo(new BigDecimal("3000.50")) == 0;
        }));
    }
}
