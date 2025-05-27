package command.handler;

import command.command.CancelOrderCommand;
import command.model.Order;
import command.repository.OrderRepository;

public class CancelOrderCommandHandler implements CommandHandler<CancelOrderCommand> {
    private OrderRepository orderRepository;

    public CancelOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(CancelOrderCommand command) {
        Order order = orderRepository.findByOrderNumber(command.getOrderNumber());
        order.cancel();
    }
}
