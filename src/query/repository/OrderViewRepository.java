package query.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import common.exception.OrderNotFoundException;
import query.model.OrderView;

public class OrderViewRepository {
    private final Map<String, OrderView> orders = new HashMap<>();

    public void save(OrderView order) {
        orders.put(order.getId(), order);
    }

    public List<OrderView> findAll() {
        return new ArrayList<>(orders.values());
    }

    public OrderView findById(String id) {
        return Optional.ofNullable(orders.get(id))
                .orElseThrow(() -> new OrderNotFoundException("Не найден заказ по id: " + id));
    }

    public OrderView findByOrderNumber(int orderNumber) {
        return orders.values().stream()
                .filter(o -> o.getNumber() == orderNumber).findFirst()
                .orElseThrow(() -> new OrderNotFoundException("Не найден заказ по номеру: " + orderNumber));
    }

    public boolean existsByOrderNumber(int orderNumber) {
        return orders.values().stream()
                .filter(o -> o.getNumber() == orderNumber)
                .findFirst().isPresent();
    }
}
