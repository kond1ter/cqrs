package command.command;

import java.util.UUID;

public class CreateDishCommand implements Command {
    private String commandId;
    private String dishName;
    private double dishPrice;

    public CreateDishCommand(String dishName, double dishPrice) {
        this.commandId = UUID.randomUUID().toString();
        this.dishName = dishName;
        this.dishPrice = dishPrice;
    }

    @Override
    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
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
