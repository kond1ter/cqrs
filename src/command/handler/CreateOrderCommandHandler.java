package command.handler;

import command.command.CreateOrderCommand;
import command.model.Order;
import command.repository.OrderRepository;

public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {
    private final OrderRepository orderRepository;

    public CreateOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(CreateOrderCommand command) {
        if (orderRepository.existsByOrderNumber(command.getOrderNumber())) {
            throw new IllegalArgumentException("Заказ с номером " + command.getOrderNumber() + " уже существует");
        }

        Order order = new Order(
            command.getOrderNumber()
        );

        orderRepository.save(order);
    }
}
