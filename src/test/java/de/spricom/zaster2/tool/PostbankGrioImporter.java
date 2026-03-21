package de.spricom.zaster2.tool;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.spricom.zaster2.dto.PostbankGiroCsvDto;
import de.spricom.zaster2.repository.BitgetTaxFutureRecordRepository;
import de.spricom.zaster2.service.PostbankGiroImportService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
@Log4j2
public class PostbankGrioImporter {
  private static final File CURRENT_DIR =
      new File("/media/hjhessmann/4C05-B576/work/privat/2026/Postbank/CSV-Exporte-Giro");

  private static final List<File> DIRS =
      List.of(
          CURRENT_DIR,
          new File("/media/hjhessmann/4C05-B576/work/privat/2025/Postbank/CSV-Exporte-Giro"),
          new File("/media/hjhessmann/4C05-B576/work/privat/2024/Konto/Postbank Giro.csv"),
          new File("/media/hjhessmann/4C05-B576/work/privat/2023/Konto/Postbank-Konto"));

  private static final File FILE =
      new File(
          "/media/hjhessmann/4C05-B576/work/privat/2023/Konto/Postbank-Konto/Kontoumsaetze_210_5140645_00_20230609_123439.csv");

  @Autowired private PostbankGiroImportService importService;

  @Test
  void testParseSingleFile() throws IOException {
    try (FileReader reader = new FileReader(FILE, StandardCharsets.UTF_8)) {
      CsvToBean<PostbankGiroCsvDto> csvToBean =
          new CsvToBeanBuilder<PostbankGiroCsvDto>(reader)
              .withType(PostbankGiroCsvDto.class)
              .withSeparator(';')
              .withIgnoreLeadingWhiteSpace(true)
              .withStrictQuotes(false)
              .withSkipLines(7)
              .withThrowExceptions(false)
              .withVerifier(bean -> bean.getBookingDate() != null)
              .build();
      List<PostbankGiroCsvDto> dtos = csvToBean.parse();
      dtos.forEach(System.out::println);
    }
  }

  @Test
  void importAllPostbankGiroCsvFiles() {
    DIRS.forEach(this::importPostbankGiroCsvFiles);
  }

  @Test
  void importCurrentPostbankGiroCsvFiles() {
    importPostbankGiroCsvFiles(CURRENT_DIR);
  }

  private void importPostbankGiroCsvFiles(File dir) {
    for (File file :
        dir.listFiles(
            file -> file.getName().matches("Kontoumsaetze_210_5140645_00_\\d{8}_\\d{6}\\.csv"))) {
      importPostbankGiroCsvFile(file);
    }
  }

  private void importPostbankGiroCsvFile(File file) {
    try (FileInputStream fis = new FileInputStream(file)) {
      importService.importCsv(file.getCanonicalPath(), fis);
    } catch (IOException ex) {
      log.error("Cannot import {}", file, ex);
    }
  }
}
