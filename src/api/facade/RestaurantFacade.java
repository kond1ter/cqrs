package api.facade;

import java.util.List;

import command.command.AddDishToOrderCommand;
import command.command.CancelOrderCommand;
import command.command.CompleteOrderCommand;
import command.command.CreateDishCommand;
import command.command.CreateOrderCommand;
import command.command.RemoveDishFromOrderCommand;
import command.handler.CommandBus;
import query.dto.DishDto;
import query.dto.OrderDto;
import query.dto.OrderTransactionDto;
import query.dto.StatisticsDto;
import query.service.DishQueryService;
import query.service.OrderQueryService;
import query.service.OrderTransactionQueryService;

public class RestaurantFacade {
    private CommandBus commandBus;
    private DishQueryService dishQueryService;
    private OrderQueryService orderQueryService;
    private OrderTransactionQueryService orderTransactionQueryService;

    public RestaurantFacade(CommandBus commandBus, DishQueryService dishQueryService,
            OrderQueryService orderQueryService, OrderTransactionQueryService orderTransactionQueryService) {
        this.commandBus = commandBus;
        this.dishQueryService = dishQueryService;
        this.orderQueryService = orderQueryService;
        this.orderTransactionQueryService = orderTransactionQueryService;
    }

    public void createDish(String dishName, double dishPrice) {
        commandBus.dispatch(new CreateDishCommand(dishName, dishPrice));
    }

    public void createOrder(int orderNumber) {
        commandBus.dispatch(new CreateOrderCommand(orderNumber));
    }

    public void addDishToOrder(String dishName, int orderNumber) {
        commandBus.dispatch(new AddDishToOrderCommand(orderNumber, dishName));
    }

    public void removeDishFromOrder(String dishName, int orderNumber) {
        commandBus.dispatch(new RemoveDishFromOrderCommand(orderNumber, dishName));
    }

    public void completeOrder(int orderNumber) {
        commandBus.dispatch(new CompleteOrderCommand(orderNumber));
    }

    public void cancelOrder(int orderNumber) {
        commandBus.dispatch(new CancelOrderCommand(orderNumber));
    }

    public List<DishDto> getAllDishes() {
        return dishQueryService.getAll();
    }

    public List<OrderDto> getAllOrders() {
        return orderQueryService.getAll();
    }

    public OrderDto getOrder(int orderNumber) {
        return orderQueryService.getOrderByNumber(orderNumber);
    }

    public StatisticsDto getStatistics() {
        return orderQueryService.getStatistics();
    }

    public List<OrderTransactionDto> getTransactions() {
        return orderTransactionQueryService.getAll();
    }
}
