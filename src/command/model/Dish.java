package command.model;

import java.util.UUID;

import common.event.DishCreatedEvent;
import common.event.EventBus;

public class Dish {
    private String id;
    private String name;    
    private double price;

    public Dish(String name, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Цена блюда не может быть отрицательной");
        }

        if (price == 0) {
            throw new IllegalArgumentException("Блюдо должно приносить доход");
        }

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;

        EventBus.getInstance()
                .publish(new DishCreatedEvent(id, name, price));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Dish)) return false;

        Dish dish = (Dish) obj;
        return dish.getName() == this.getName();
    }
}
