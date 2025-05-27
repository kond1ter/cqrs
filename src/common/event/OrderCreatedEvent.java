package common.event;

public class OrderCreatedEvent extends Event {
    private String orderId;
    private int orderNumber;

    public OrderCreatedEvent(String orderId, int orderNumber) {
        super();
        this.orderId = orderId;
        this.orderNumber = orderNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
