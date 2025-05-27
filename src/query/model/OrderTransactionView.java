package query.model;

import java.time.LocalDateTime;

import common.enums.OrderStatus;

public class OrderTransactionView {
    private String id;
    private int orderNumber;
    private OrderStatus status;
    private LocalDateTime registeredAt;
    
    public OrderTransactionView(String id, int orderNumber, OrderStatus status, LocalDateTime registeredAt) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.status = status;
        this.registeredAt = registeredAt;
    }

    public String getId() {
        return id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }
}
