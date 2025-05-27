package command.command;

import java.util.UUID;

public class RemoveDishFromOrderCommand implements Command {
    private String commandId;
    private int orderNumber;
    private String dishName;

    public RemoveDishFromOrderCommand(int orderNumber, String dishName) {
        this.commandId = UUID.randomUUID().toString();
        this.orderNumber = orderNumber;
        this.dishName = dishName;
    }

    @Override
    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
}
