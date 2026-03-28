package de.spricom.zaster2.tool;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.spricom.zaster2.dto.OlbGiroCsvDto;
import de.spricom.zaster2.service.OlbGiroImportService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
@Log4j2
public class OlbGrioImporter {
  private static final File CURRENT_DIR =
      new File("/media/hjhessmann/4C05-B576/work/privat/2026/OLB/csv");

  private static final List<File> DIRS =
      List.of(
          CURRENT_DIR
      );

  private static final File FILE =
      new File(
          "/media/hjhessmann/4C05-B576/work/privat/2026/OLB/csv/Umsätze_CSV-Export_2026-03-28.csv");

  @Autowired private OlbGiroImportService importService;

  @Test
  void testParseSingleFile() throws IOException {
    try (FileReader reader = new FileReader(FILE, StandardCharsets.ISO_8859_1)) {
      CsvToBean<OlbGiroCsvDto> csvToBean = new CsvToBeanBuilder<OlbGiroCsvDto>(reader)
              .withType(OlbGiroCsvDto.class)
              .withSeparator(';')
              .withIgnoreLeadingWhiteSpace(true)
              .withStrictQuotes(false)
              .withThrowExceptions(false)
              .withVerifier(bean -> bean.getBookingDate() != null)
              .build();
      List<OlbGiroCsvDto> dtos = csvToBean.parse();
      dtos.forEach(System.out::println);
    }
  }

  @Test
  void importAllOlbGiroCsvFiles() {
    DIRS.forEach(this::importOlbGiroCsvFiles);
  }

  @Test
  void importCurrentOlbGiroCsvFiles() {
    importOlbGiroCsvFiles(CURRENT_DIR);
  }

  private void importOlbGiroCsvFiles(File dir) {
    for (File file :
        dir.listFiles(
            file -> file.getName().matches(".*\\.csv"))) {
      importOlbGiroCsvFile(file);
    }
  }

  private void importOlbGiroCsvFile(File file) {
    try (FileInputStream fis = new FileInputStream(file)) {
      importService.importCsv(file.getCanonicalPath(), fis);
    } catch (IOException ex) {
      log.error("Cannot import {}", file, ex);
    }
  }
}
