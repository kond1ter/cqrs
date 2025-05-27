package query.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import common.enums.OrderStatus;
import query.dto.DishDto;
import query.dto.OrderDto;
import query.dto.StatisticsDto;
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

    public StatisticsDto getStatistics() {
        List<OrderView> orders = orderViewRepository.findAll();

        int totalOrders = orders.size();

        int completedOrders = orders.stream()
                .filter((OrderView o) -> o.getStatus().equals(OrderStatus.COMPLETED))
                .toList().size();

        int cancelledOrders = orders.stream()
                .filter((OrderView o) -> o.getStatus().equals(OrderStatus.CANCELLED))
                .toList().size();

        double totalIncome = orders.stream()
                .filter((OrderView o) -> o.getStatus().equals(OrderStatus.COMPLETED))
                .map((OrderView o) -> o.getDishes().keySet().stream().map((DishView d) -> d.getPrice())
                        .mapToDouble(Double::doubleValue).sum())
                .mapToDouble(Double::doubleValue).sum();

        return new StatisticsDto(totalOrders, completedOrders, cancelledOrders, totalIncome);
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
                order.getLastUpdatedAt());
    }
}
