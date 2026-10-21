package de.spricom.zaster2.views.bitget;

import de.spricom.zaster2.entities.BitgetFutureOrderEntity;
import de.spricom.zaster2.repository.BitgetFutureOrderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BitgetFutureOrderService {

    private final BitgetFutureOrderRepository repository;

    public BitgetFutureOrderService(BitgetFutureOrderRepository repository) {
        this.repository = repository;
    }

    public List<BitgetFutureOrderEntity> fetchOrders(BitgetFutureOrderModel model) {
        List<BitgetFutureOrderEntity> all = repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        
        return all.stream()
                .filter(order -> {
                    if (model.getSymbol() != null && !model.getSymbol().isEmpty()) {
                        return order.getSymbol().contains(model.getSymbol());
                    }
                    return true;
                })
                .filter(order -> {
                    if (model.getFromDate() != null) {
                        return !order.getCreatedAt().isBefore(model.getFromDate().atStartOfDay().toInstant(ZoneOffset.UTC));
                    }
                    return true;
                })
                .filter(order -> {
                    if (model.getToDate() != null) {
                        return !order.getCreatedAt().isAfter(model.getToDate().atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC));
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
