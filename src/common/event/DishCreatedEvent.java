package common.event;

public class DishCreatedEvent extends Event {
    private String dishId;
    private String dishName;
    private double dishPrice;
    
    public DishCreatedEvent(String dishId, String dishName, double dishPrice) {
        super();
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }
}
