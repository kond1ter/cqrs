package query.service;

import java.util.List;
import java.util.stream.Collectors;

import query.dto.OrderTransactionDto;
import query.model.OrderTransactionView;
import query.repository.OrderTransactionViewRepository;

public class OrderTransactionQueryService {
    private OrderTransactionViewRepository orderTransactionViewRepository;

    public OrderTransactionQueryService(OrderTransactionViewRepository orderTransactionViewRepository) {
        this.orderTransactionViewRepository = orderTransactionViewRepository;
    }

    public List<OrderTransactionDto> getAll() {
        return orderTransactionViewRepository.findAll().stream()
                .map((OrderTransactionView orderView) -> mapToDto(orderView))
                .collect(Collectors.toList());
    }

    private OrderTransactionDto mapToDto(OrderTransactionView t) {
        return new OrderTransactionDto(t.getId(), t.getOrderNumber(), t.getStatus().name(), t.getRegisteredAt());
    }
}
