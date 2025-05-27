package common.event;

public class OrderCompletedEvent extends Event {
    private String orderId;

    public OrderCompletedEvent(String orderId) {
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
