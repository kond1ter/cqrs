package command.handler;

import command.command.AddDishToOrderCommand;
import command.model.Dish;
import command.model.Order;
import command.repository.DishRepository;
import command.repository.OrderRepository;

public class AddDishToOrderCommandHandler implements CommandHandler<AddDishToOrderCommand> {
    private DishRepository dishRepository;
    private OrderRepository orderRepository;

    public AddDishToOrderCommandHandler(DishRepository dishRepository, OrderRepository orderRepository) {
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(AddDishToOrderCommand command) {
        Order order = orderRepository.findByOrderNumber(command.getOrderNumber());
        Dish dish = dishRepository.findByName(command.getDishName());
        
        order.addDish(dish);
    }
}
