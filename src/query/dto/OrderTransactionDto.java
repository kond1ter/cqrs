package query.dto;

import java.time.LocalDateTime;

public class OrderTransactionDto {
    private String id;
    private int orderNumber;
    private String status;
    private LocalDateTime registeredAt;
    
    public OrderTransactionDto(String id, int orderNumber, String status, LocalDateTime registeredAt) {
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

    public String getStatus() {
        return status;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

}
