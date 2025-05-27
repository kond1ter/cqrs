package common.event;

public class DishRemovedFromOrderEvent extends Event {
    private String orderId;
    private String dishName;

    public DishRemovedFromOrderEvent(String orderId, String dishName) {
        super();
        this.orderId = orderId;
        this.dishName = dishName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

}
