package query.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import query.model.OrderTransactionView;

public class OrderTransactionViewRepository {
    private final Map<String, OrderTransactionView> orders = new HashMap<>();

    public void save(OrderTransactionView order) {
        orders.put(order.getId(), order);
    }

    public List<OrderTransactionView> findAll() {
        return new ArrayList<>(orders.values()).stream()
                .sorted((OrderTransactionView v0,
                        OrderTransactionView v1) -> v0.getRegisteredAt().isBefore(v1.getRegisteredAt()) ? -1 : 1)
                .toList();
    }
}
