package command.handler;

import command.command.CreateDishCommand;
import command.model.Dish;
import command.repository.DishRepository;

public class CreateDishCommandHandler implements CommandHandler<CreateDishCommand> {
    private final DishRepository dishRepository;

    public CreateDishCommandHandler(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public void handle(CreateDishCommand command) {
        if (dishRepository.existsByName(command.getDishName())) {
            throw new IllegalArgumentException("Блюдо с именем " + command.getDishName() + " уже существует");
        }

        Dish dish = new Dish(
            command.getDishName(),
            command.getDishPrice()
        );

        dishRepository.save(dish);
    }

}
