package command.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import common.enums.OrderStatus;
import common.event.DishAddedToOrderEvent;
import common.event.DishRemovedFromOrderEvent;
import common.event.EventBus;
import common.event.OrderCompletedEvent;
import common.event.OrderCreatedEvent;
import common.exception.InvalidOrderOperationException;

public class Order {
    private String id;
    private int number;
    private Map<Dish, Integer> dishes;
    private OrderStatus status;

    public Order(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Номер заказа не может быть отрицательным");
        }

        this.id = UUID.randomUUID().toString();
        this.status = OrderStatus.IN_PROGRESS;
        this.number = number;
        this.dishes = new HashMap<>();

        EventBus.getInstance()
                .publish(new OrderCreatedEvent(id, number));
    }

    public void addDish(Dish dish) {
        checkStatusIsSetted();

        if (dishes.get(dish) == null) {
            dishes.put(dish, 1);
        } else {
            dishes.put(dish, dishes.get(dish) + 1);
        }
 
        EventBus.getInstance()
                .publish(new DishAddedToOrderEvent(id, dish.getName()));
    }

    public void removeDish(Dish dish) {
        if (dishes.get(dish) == null) {
            throw new InvalidOrderOperationException("Ошибка при удалении блюда " + dish.getName() + " из заказа с номером " + number + ": данного блюда не существует в заказе");
        }

        if (dishes.get(dish) == 1) {
            dishes.remove(dish);
        } else {
            dishes.put(dish, dishes.get(dish) - 1);
        }

        EventBus.getInstance()
                .publish(new DishRemovedFromOrderEvent(id, dish.getName()));
    }

    public void complete() {
        checkStatusIsSetted();
        status = OrderStatus.COMPLETED;

        EventBus.getInstance()
                .publish(new OrderCompletedEvent(id));
    }

    public void cancel() {
        checkStatusIsSetted();
        status = OrderStatus.CANCELLED;

        EventBus.getInstance()
                .publish(new OrderCompletedEvent(id));
    }

    private void checkStatusIsSetted() {
        if (status.equals(OrderStatus.CANCELLED)) {
            throw new InvalidOrderOperationException("Данный заказ уже считается отмененным: " + number);
        }

        if (status.equals(OrderStatus.COMPLETED)) {
            throw new InvalidOrderOperationException("Данный заказ уже считается выполненным: " + number);
        }
    }

    public String getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Map<Dish, Integer> getDishes() {
        return dishes;
    }
}
