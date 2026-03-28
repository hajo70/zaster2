package de.spricom.zaster2.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class OlbGiroCsvDto {

    @CsvBindByName(column = "Inhaberkonto")
    private String accountHolder;

    @CsvBindByName(column = "Buchungsdatum")
    @CsvDate("dd.MM.yyyy")
    private LocalDate bookingDate;

    @CsvBindByName(column = "Valuta")
    @CsvDate("dd.MM.yyyy")
    private LocalDate valueDate;

    @CsvBindByName(column = "Empfänger/Auftraggeber")
    private String partnerName;

    @CsvBindByName(column = "IBAN")
    private String iban;

    @CsvBindByName(column = "BIC")
    private String bic;

    @CsvBindByName(column = "Verwendungszweck")
    private String paymentReference;

    @CsvCustomBindByName(column = "Betrag", converter = GermanBigDecimalConverter.class)
    private BigDecimal amount;

    @CsvBindByName(column = "Währung")
    private String currency;

    @CsvBindByName(column = "Kundenreferenz")
    private String customerReference;

    @CsvBindByName(column = "Bankreferenz")
    private String bankReference;

    @CsvBindByName(column = "Primatnota")
    private String primaryNota;

    @CsvBindByName(column = "Transaktions-Code")
    private String transactionCode;

    @CsvBindByName(column = "Transaktions-Text")
    private String transactionText;
}
