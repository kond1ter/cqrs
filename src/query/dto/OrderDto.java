package query.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class OrderDto {
    private String id;
    private int number;
    private Map<DishDto, Integer> dishes;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    public OrderDto(String id, int number, Map<DishDto, Integer> dishes, String status, LocalDateTime createdAt,
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

    public Map<DishDto, Integer> getDishes() {
        return dishes;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }
}
