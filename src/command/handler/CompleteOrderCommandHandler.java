package command.handler;

import command.command.CompleteOrderCommand;
import command.model.Order;
import command.repository.OrderRepository;

public class CompleteOrderCommandHandler implements CommandHandler<CompleteOrderCommand> {
    private OrderRepository orderRepository;

    public CompleteOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(CompleteOrderCommand command) {
        Order order = orderRepository.findByOrderNumber(command.getOrderNumber());
        order.complete();
    }
}
