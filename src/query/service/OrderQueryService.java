package query.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import query.dto.DishDto;
import query.dto.OrderDto;
import query.model.DishView;
import query.model.OrderView;
import query.repository.OrderViewRepository;

public class OrderQueryService {
    private OrderViewRepository orderViewRepository;

    public OrderQueryService(OrderViewRepository orderViewRepository) {
        this.orderViewRepository = orderViewRepository;
    }

    public OrderDto getOrderByNumber(int number) {
        return mapToDto(orderViewRepository.findByOrderNumber(number));
    }

    public List<OrderDto> getAll() {
        return orderViewRepository.findAll().stream()
                .map((OrderView orderView) -> mapToDto(orderView))
                .collect(Collectors.toList());
    }

    private OrderDto mapToDto(OrderView order) {
        Map<DishDto, Integer> dishes = new HashMap<>();

        for (Entry<DishView, Integer> entry : order.getDishes().entrySet()) {
            DishView dishView = entry.getKey();
            DishDto dishDto = new DishDto(dishView.getId(), dishView.getName(), dishView.getPrice());
            dishes.put(dishDto, entry.getValue());
        }

        return new OrderDto(
            order.getId(), 
            order.getNumber(), 
            dishes, 
            order.getStatus().name(), 
            order.getCreatedAt(), 
            order.getLastUpdatedAt()
        );
    }
}
