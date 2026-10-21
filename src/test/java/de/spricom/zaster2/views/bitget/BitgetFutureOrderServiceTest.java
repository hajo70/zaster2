package de.spricom.zaster2.views.bitget;

import de.spricom.zaster2.entities.BitgetFutureOrderEntity;
import de.spricom.zaster2.repository.BitgetFutureOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BitgetFutureOrderServiceTest {

    @Mock
    private BitgetFutureOrderRepository repository;

    private BitgetFutureOrderService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new BitgetFutureOrderService(repository);
    }

    @Test
    void fetchOrders_filtersBySymbol() {
        BitgetFutureOrderEntity order1 = new BitgetFutureOrderEntity();
        order1.setSymbol("BTCUSDT");
        order1.setCreatedAt(Instant.parse("2023-10-01T10:00:00Z"));

        BitgetFutureOrderEntity order2 = new BitgetFutureOrderEntity();
        order2.setSymbol("ETHUSDT");
        order2.setCreatedAt(Instant.parse("2023-10-02T10:00:00Z"));

        when(repository.findAll(any(Sort.class))).thenReturn(Arrays.asList(order2, order1));

        BitgetFutureOrderModel model = new BitgetFutureOrderModel();
        model.setSymbol("BTC");

        List<BitgetFutureOrderEntity> result = service.fetchOrders(model);

        assertEquals(1, result.size());
        assertEquals("BTCUSDT", result.getFirst().getSymbol());
    }

    @Test
    void fetchOrders_filtersByDateRange() {
        BitgetFutureOrderEntity order1 = new BitgetFutureOrderEntity();
        order1.setSymbol("BTCUSDT");
        order1.setCreatedAt(Instant.parse("2023-10-01T10:00:00Z"));

        BitgetFutureOrderEntity order2 = new BitgetFutureOrderEntity();
        order2.setSymbol("ETHUSDT");
        order2.setCreatedAt(Instant.parse("2023-10-15T10:00:00Z"));

        when(repository.findAll(any(Sort.class))).thenReturn(Arrays.asList(order2, order1));

        BitgetFutureOrderModel model = new BitgetFutureOrderModel();
        model.setFromDate(LocalDate.of(2023, 10, 10));
        model.setToDate(LocalDate.of(2023, 10, 20));

        List<BitgetFutureOrderEntity> result = service.fetchOrders(model);

        assertEquals(1, result.size());
        assertEquals("ETHUSDT", result.getFirst().getSymbol());
    }
}
