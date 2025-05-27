package command.command;

import java.util.UUID;

public class CreateOrderCommand implements Command {
    private String commandId;
    private int orderNumber;

    public CreateOrderCommand(int orderNumber) {
        this.commandId = UUID.randomUUID().toString();
        this.orderNumber = orderNumber;
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
}
