package command.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import command.model.Order;
import common.exception.OrderNotFoundException;

public class OrderRepository {
    private final Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    public Order findById(String id) {
        return Optional.ofNullable(orders.get(id))
                .orElseThrow(() -> new OrderNotFoundException("Не найден заказ по id: " + id));
    }

    public Order findByOrderNumber(int orderNumber) {
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
