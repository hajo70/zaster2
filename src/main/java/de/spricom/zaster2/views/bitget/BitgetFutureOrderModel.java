package de.spricom.zaster2.views.bitget;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BitgetFutureOrderModel {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String symbol;
}
