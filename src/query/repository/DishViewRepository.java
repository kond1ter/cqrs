package query.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.exception.DishNotFoundException;
import query.model.DishView;

public class DishViewRepository {
    private final Map<String, DishView> dishes = new HashMap<>();

    public void save(DishView dish) {
        dishes.put(dish.getId(), dish);
    }

    public List<DishView> findAll() {
        return new ArrayList<>(dishes.values());
    }

    public DishView findByName(String name) {
        return dishes.values().stream()
                .filter(d -> d.getName().equals(name)).findFirst()
                .orElseThrow(() -> new DishNotFoundException("Не найдено блюдо по имени: " + name));
    }
}
