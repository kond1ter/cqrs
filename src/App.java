import api.cli.ConsoleLineInterface;
import api.facade.RestaurantFacade;
import command.command.AddDishToOrderCommand;
import command.command.CancelOrderCommand;
import command.command.CompleteOrderCommand;
import command.command.CreateDishCommand;
import command.command.CreateOrderCommand;
import command.command.RemoveDishFromOrderCommand;
import command.handler.AddDishToOrderCommandHandler;
import command.handler.CancelOrderCommandHandler;
import command.handler.CommandBus;
import command.handler.CompleteOrderCommandHandler;
import command.handler.CreateDishCommandHandler;
import command.handler.CreateOrderCommandHandler;
import command.handler.RemoveDishFromOrderCommandHandler;
import command.repository.DishRepository;
import command.repository.OrderRepository;
import common.event.EventBus;
import query.repository.DishViewRepository;
import query.repository.OrderViewRepository;
import query.service.DishQueryService;
import query.service.EventHandler;
import query.service.OrderQueryService;

public class App {
    public static void main(String[] args) throws Exception {
        DishRepository commandDishRepository = new DishRepository();
        OrderRepository commandOrderRepository = new OrderRepository();
        DishViewRepository queryDishRepository = new DishViewRepository();
        OrderViewRepository queryOrderRepository = new OrderViewRepository();

        EventHandler eventHandler = new EventHandler(queryOrderRepository, queryDishRepository);
        EventBus.getInstance().register(eventHandler);

        CommandBus commandBus = new CommandBus();
        commandBus.register(CreateDishCommand.class, new CreateDishCommandHandler(commandDishRepository));
        commandBus.register(CreateOrderCommand.class, new CreateOrderCommandHandler(commandOrderRepository));
        commandBus.register(AddDishToOrderCommand.class, new AddDishToOrderCommandHandler(commandDishRepository, commandOrderRepository));
        commandBus.register(RemoveDishFromOrderCommand.class, new RemoveDishFromOrderCommandHandler(commandDishRepository, commandOrderRepository));
        commandBus.register(CompleteOrderCommand.class, new CompleteOrderCommandHandler(commandOrderRepository));
        commandBus.register(CancelOrderCommand.class, new CancelOrderCommandHandler(commandOrderRepository));

        DishQueryService dishQueryService = new DishQueryService(queryDishRepository);
        OrderQueryService orderQueryService = new OrderQueryService(queryOrderRepository);

        RestaurantFacade facade = new RestaurantFacade(commandBus, dishQueryService, orderQueryService);


        facade.createDish("Стейк", 700);
        facade.createDish("Салат цезарь", 400);
        facade.createDish("Салат греческий", 500);
        facade.createDish("Салат зеленый", 400);
        facade.createDish("Баклажаны в панировке", 300);
        facade.createDish("Классический борщ", 600);


        ConsoleLineInterface cli = new ConsoleLineInterface(facade);
        cli.start();
    }
}
