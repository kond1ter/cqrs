package query.service;

import java.util.List;
import java.util.stream.Collectors;

import query.dto.DishDto;
import query.model.DishView;
import query.repository.DishViewRepository;

public class DishQueryService {
    private DishViewRepository dishViewRepository;

    public DishQueryService(DishViewRepository dishViewRepository) {
        this.dishViewRepository = dishViewRepository;
    }

    public List<DishDto> getAll() {
        return dishViewRepository.findAll().stream()
                .map((DishView dish) -> mapToDto(dish))
                .collect(Collectors.toList());
    }

    private DishDto mapToDto(DishView dish) {
        return new DishDto(dish.getId(), dish.getName(), dish.getPrice());
    }
}
