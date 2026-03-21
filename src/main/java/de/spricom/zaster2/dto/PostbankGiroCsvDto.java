package de.spricom.zaster2.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class PostbankGiroCsvDto {

    @CsvBindByName(column = "Buchungstag")
    @CsvDate("d.M.yyyy")
    private LocalDate bookingDate;

    @CsvBindByName(column = "Wert")
    @CsvDate("d.M.yyyy")
    private LocalDate valueDate;

    @CsvBindByName(column = "Umsatzart")
    private String transactionType;

    @CsvBindByName(column = "Begünstigter / Auftraggeber")
    private String partnerName;

    @CsvBindByName(column = "Verwendungszweck")
    private String paymentReference;

    @CsvBindByName(column = "IBAN / Kontonummer")
    private String iban;

    @CsvBindByName(column = "BIC")
    private String bic;

    @CsvBindByName(column = "Kundenreferenz")
    private String customerReference;

    @CsvBindByName(column = "Mandatsreferenz")
    private String mandateReference;

    @CsvBindByName(column = "Gläubiger ID")
    private String creditorId;

    @CsvCustomBindByName(column = "Fremde Gebühren", converter = GermanBigDecimalConverter.class)
    private BigDecimal foreignFees;

    @CsvCustomBindByName(column = "Betrag", converter = GermanBigDecimalConverter.class)
    private BigDecimal amount;

    @CsvBindByName(column = "Abweichender Empfänger")
    private String deviatingRecipient;

    @CsvBindByName(column = "Währung")
    private String currency;
}
