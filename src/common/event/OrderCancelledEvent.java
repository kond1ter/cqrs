package common.event;

public class OrderCancelledEvent extends Event {
    private String orderId;

    public OrderCancelledEvent(String orderId) {
        super();
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
