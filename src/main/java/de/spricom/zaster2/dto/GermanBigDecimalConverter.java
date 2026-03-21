package de.spricom.zaster2.dto;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class GermanBigDecimalConverter extends AbstractBeanField<BigDecimal, String> {
    @Override
    protected BigDecimal convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY);
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
            decimalFormat.setParseBigDecimal(true);
            return (BigDecimal) decimalFormat.parse(value.trim());
        } catch (ParseException e) {
            throw new CsvDataTypeMismatchException(value, BigDecimal.class, "Cannot parse German BigDecimal: " + value);
        }
    }
}
