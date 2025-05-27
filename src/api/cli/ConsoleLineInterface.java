package api.cli;

import java.security.KeyStore.Entry;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import api.facade.RestaurantFacade;
import query.dto.DishDto;
import query.dto.OrderDto;

public class ConsoleLineInterface {
    private RestaurantFacade facade;
    private Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public ConsoleLineInterface(RestaurantFacade facade) {
        this.facade = facade;
    }

    public void start() {
        System.out.println("Система управления клиентскими заказами");
        System.out.println();
        int choice;
        do {
            showMainMenu();
            choice = scanner.nextInt();

            handleMainMenuChoice(choice);
            System.out.println();
        } while (choice != 0);
    }

    private void showMainMenu() {
        System.out.println("1. Добавить блюдо в меню");
        System.out.println("2. Показать меню");
        System.out.println("3. Создать заказ");
        System.out.println("4. Показать все заказы");
        System.out.println("5. Добавить блюдо в заказ");
        System.out.println("6. Удалить блюдо из заказа");
        System.out.println("0. Выйти из системы");
        System.out.print("Выберите действие: ");
    }

    private void handleMainMenuChoice(int choice) {
        System.out.println();
        switch (choice) {
            case 0:
                System.out.println("Выход из программы...");
                break;
            case 1:
                handleCreateDish();
                break;
            case 2:
                handleShowDishes();
                break;
            case 3:
                handleCreateOrder();
                break;
            case 4:
                handleShowOrders();
                break;
            case 5:
                handleAddDishToOrder();
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void handleCreateDish() {
        System.out.print("Введите название блюда: ");
        String dishName = scanner.next();
        System.out.print("Введите цену блюда: ");
        int dishPrice = scanner.nextInt();

        facade.createDish(dishName, dishPrice);
        System.out.println("Создано блюдо с названием " + dishName + " и ценой " + dishPrice + " p.");
    }

    private void handleShowDishes() {
        List<DishDto> dishes = facade.getAllDishes();
        System.out.println("Меню:");

        for (DishDto dish : dishes) {
            System.out.println(dish.getName() + " - " + dish.getPrice() + " р.");
        }
    }

    private void handleCreateOrder() {
        System.out.print("Введите номер заказа: ");
        int orderNumber = scanner.nextInt();

        try {
            facade.createOrder(orderNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Создан заказ с номером " + orderNumber);
    }

    private void handleShowOrders() {
        System.out.println("Заказы:");
        List<OrderDto> orders = facade.getAllOrders();

        for (OrderDto order : orders) {
            System.out.println("Заказ номер: " + order.getNumber());

            for (java.util.Map.Entry<DishDto, Integer> dish : order.getDishes().entrySet()) {
                System.out.println("\t" + dish.getKey().getName() + " - " + dish.getValue() + " шт.");
            }
        }
    }

    private void handleAddDishToOrder() {
        System.out.print("Введите номер заказа: ");
        int orderNumber = scanner.nextInt();
        String dishName = handleDishChoice(facade.getAllDishes());

        facade.addDishToOrder(dishName, orderNumber);
        System.out.println("Добавлено блюдо " + dishName + " в заказ с номером " + orderNumber);
    }

    private String handleDishChoice(List<DishDto> dishes) {
        for (int i = 0; i < dishes.size(); i++) {
            DishDto dish = dishes.get(i);
            System.out.println(i + ". " + dish.getName());
        }

        System.out.print("Выберите блюдо: ");
        int dishIndex = scanner.nextInt();
        return dishes.get(dishIndex).getName();
    }
}
