package command.command;

import java.util.UUID;

public class CancelOrderCommand implements Command {
    private String commandId;
    private int orderNumber;

    public CancelOrderCommand(int orderNumber) {
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
