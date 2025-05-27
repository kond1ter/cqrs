package query.model;

import java.time.LocalDateTime;
import java.util.Map;

import common.enums.OrderStatus;

public class OrderView {
    private String id;
    private int number;
    private Map<DishView, Integer> dishes;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    public OrderView(String id, int number, Map<DishView, Integer> dishes, OrderStatus status, LocalDateTime createdAt,
            LocalDateTime lastUpdatedAt) {
        this.id = id;
        this.number = number;
        this.dishes = dishes;
        this.status = status;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public Map<DishView, Integer> getDishes() {
        return dishes;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void addDish(DishView dish) {
        if (dishes.get(dish) == null) {
            dishes.put(dish, 1);
            return;
        }

        dishes.put(dish, dishes.get(dish) + 1);
        emitUpdate();
    }

    public void removeDish(DishView dish) {
        if (dishes.get(dish) == 0) {
            dishes.remove(dish);
            return;
        }

        dishes.put(dish, dishes.get(dish) - 1);
        emitUpdate();
    }

    public void complete() {
        status = OrderStatus.COMPLETED;
        emitUpdate();
    }

    public void cancel() {
        status = OrderStatus.CANCELLED;
        emitUpdate();
    }

    private void emitUpdate() {
        lastUpdatedAt = LocalDateTime.now();
    }
}
