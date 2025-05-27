package command.handler;

import command.command.RemoveDishFromOrderCommand;
import command.model.Dish;
import command.model.Order;
import command.repository.DishRepository;
import command.repository.OrderRepository;

public class RemoveDishFromOrderCommandHandler implements CommandHandler<RemoveDishFromOrderCommand> {
    private DishRepository dishRepository;
    private OrderRepository orderRepository;

    public RemoveDishFromOrderCommandHandler(DishRepository dishRepository, OrderRepository orderRepository) {
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(RemoveDishFromOrderCommand command) {
        Order order = orderRepository.findByOrderNumber(command.getOrderNumber());
        Dish dish = dishRepository.findByName(command.getDishName());
        
        order.removeDish(dish);
    }

}
