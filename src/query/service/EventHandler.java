package query.service;

import java.time.LocalDateTime;
import java.util.HashMap;

import common.enums.OrderStatus;
import common.event.DishAddedToOrderEvent;
import common.event.DishCreatedEvent;
import common.event.DishRemovedFromOrderEvent;
import common.event.Event;
import common.event.EventBus;
import common.event.OrderCancelledEvent;
import common.event.OrderCompletedEvent;
import common.event.OrderCreatedEvent;
import query.model.DishView;
import query.model.OrderView;
import query.repository.DishViewRepository;
import query.repository.OrderViewRepository;

public class EventHandler implements EventBus.EventHandler {
    private OrderViewRepository orderViewRepository;
    private DishViewRepository dishViewRepository;

    public EventHandler(OrderViewRepository orderViewRepository, DishViewRepository dishViewRepository) {
        this.orderViewRepository = orderViewRepository;
        this.dishViewRepository = dishViewRepository;
    }

    @Override
    public void handle(Event event) {
        if (event instanceof DishCreatedEvent) {
            handleDishCreated((DishCreatedEvent) event);
        } else if (event instanceof OrderCreatedEvent) {
            handleOrderCreated((OrderCreatedEvent) event);
        } else if (event instanceof DishAddedToOrderEvent) {
            handleDishAddedToOrder((DishAddedToOrderEvent) event);
        } else if (event instanceof DishRemovedFromOrderEvent) {
            handleDishRemoverFromOrder((DishRemovedFromOrderEvent) event);
        } else if (event instanceof OrderCompletedEvent) {
            handleOrderCompleted((OrderCompletedEvent) event);
        } else if (event instanceof OrderCancelledEvent) {
            handleOrderCancelled((OrderCancelledEvent) event);
        }
    }

    private void handleDishCreated(DishCreatedEvent event) {
        DishView dish = new DishView(
            event.getDishId(), 
            event.getDishName(), 
            event.getDishPrice()
        );

        dishViewRepository.save(dish);
    }

    private void handleOrderCreated(OrderCreatedEvent event) {
        OrderView order = new OrderView(
            event.getOrderId(), 
            event.getOrderNumber(), 
            new HashMap<>(), 
            OrderStatus.IN_PROGRESS,
            LocalDateTime.now(), 
            LocalDateTime.now()
        );

        orderViewRepository.save(order);
    }

    private void handleDishAddedToOrder(DishAddedToOrderEvent event) {
        OrderView order = orderViewRepository.findById(event.getOrderId());
        DishView dish = dishViewRepository.findByName(event.getDishName());

        order.addDish(dish);
    }

    private void handleDishRemoverFromOrder(DishRemovedFromOrderEvent event) {
        OrderView order = orderViewRepository.findById(event.getOrderId());
        DishView dish = dishViewRepository.findByName(event.getDishName());

        order.removeDish(dish);
        orderViewRepository.save(order);
    }

    private void handleOrderCompleted(OrderCompletedEvent event) {
        OrderView order = orderViewRepository.findById(event.getOrderId());
        
        order.complete();
        orderViewRepository.save(order);
    }

    private void handleOrderCancelled(OrderCancelledEvent event) {
        OrderView order = orderViewRepository.findById(event.getOrderId());
        
        order.cancel();
        orderViewRepository.save(order);
    }
}
