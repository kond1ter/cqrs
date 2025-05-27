package command.repository;

import java.util.HashMap;
import java.util.Map;

import command.model.Dish;
import common.exception.DishNotFoundException;

public class DishRepository {
    private final Map<String, Dish> dishes = new HashMap<>();

    public void save(Dish dish) {
        dishes.put(dish.getId(), dish);
    }

    public Dish findByName(String name) {
        return dishes.values().stream()
                .filter(d -> d.getName().equals(name)).findFirst()
                .orElseThrow(() -> new DishNotFoundException("Не найдено блюдо по имени: " + name));
    }

    public boolean existsByName(String name) {
        return dishes.values().stream()
                .filter(d -> d.getName().equals(name))
                .findFirst().isPresent();
    }
}
