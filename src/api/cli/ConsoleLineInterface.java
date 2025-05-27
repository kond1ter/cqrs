package api.cli;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import api.facade.RestaurantFacade;
import common.exception.InvalidOrderOperationException;
import common.exception.OrderNotFoundException;
import query.dto.DishDto;
import query.dto.OrderDto;
import query.dto.OrderTransactionDto;
import query.dto.StatisticsDto;

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

            try {
                choice = tryScanInteger();
            } catch (Exception e) {
                System.out.println("\n" + e.getMessage() + "\n");
                choice = Integer.MAX_VALUE;
                continue;
            }

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
        System.out.println("7. Выполнить заказ");
        System.out.println("8. Отменить заказ");
        System.out.println("9. Показать историю выполнения заказов");
        System.out.println("10. Показать статистику за все время");
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
                handleRemoveDishFromOrder();
                break;
            case 7:
                handleCompleteOrder();
                break;
            case 8:
                handleCancelOrder();
                break;
            case 9:
                handleShowTransactions();
                break;
            case 10:
                handleShowStatistics();
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void handleCreateDish() {
        String dishName = null;
        double dishPrice = -1;

        try {
            System.out.print("Введите название блюда: ");
            dishName = scanner.nextLine().trim();
            System.out.print("Введите цену блюда: ");
            dishPrice = tryScanDouble();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            facade.createDish(dishName, dishPrice);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
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
        int orderNumber = -1;

        try {
            System.out.print("Введите номер заказа: ");
            orderNumber = tryScanInteger();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }

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
            System.out.println("Заказ номер: " + order.getNumber() + " - " + order.getStatus());

            for (java.util.Map.Entry<DishDto, Integer> dish : order.getDishes().entrySet()) {
                System.out.println(" - " + dish.getKey().getName() + " - " + dish.getValue() + " шт.");
            }
        }
    }

    private void handleAddDishToOrder() {
        int orderNumber = -1;
        String dishName = null;
        
        try {
            System.out.print("Введите номер заказа: ");
            orderNumber = tryScanInteger();
            dishName = handleDishChoice(facade.getAllDishes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            facade.addDishToOrder(dishName, orderNumber);
        } catch (OrderNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        } catch (InvalidOrderOperationException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Добавлено блюдо " + dishName + " в заказ с номером " + orderNumber);
    }

    private void handleRemoveDishFromOrder() {
        int orderNumber = -1;
        String dishName = null;

        try {
            System.out.print("Введите номер заказа: ");
            orderNumber = tryScanInteger();
            dishName = handleDishChoice(facade.getAllDishes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            facade.removeDishFromOrder(dishName, orderNumber);
        } catch (OrderNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        } catch (InvalidOrderOperationException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Удалено блюдо " + dishName + " из заказ с номером " + orderNumber);
    }

    private void handleCompleteOrder() {
        int orderNumber = -1;

        try {
            System.out.print("Введите номер заказа: ");
            orderNumber = tryScanInteger();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            facade.completeOrder(orderNumber);
        } catch (OrderNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        } catch (InvalidOrderOperationException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Заказ под номером " + orderNumber + " отмечен как выполненный");
    }

    private void handleCancelOrder() {
        int orderNumber = -1;

        try {
            System.out.print("Введите номер заказа: ");
            orderNumber = tryScanInteger();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            facade.cancelOrder(orderNumber);
        } catch (OrderNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        } catch (InvalidOrderOperationException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Заказ под номером " + orderNumber + " отмечен как отмененный");
    }

    private void handleShowTransactions() {
        List<OrderTransactionDto> transactions = facade.getTransactions();

        for (OrderTransactionDto transaction : transactions) {
            System.out.println("Заказ с номером: " + transaction.getOrderNumber() + "; состояние: "
                    + transaction.getStatus() + "; время: " + transaction.getRegisteredAt().format(dateFormatter));
        }
    }

    private void handleShowStatistics() {
        StatisticsDto stat = facade.getStatistics();

        System.out.println("Всего заказов: " + stat.getTotalOrders());
        System.out.println("Всего выполненных заказов: " + stat.getCompletedOrders());
        System.out.println("Всего отмененных заказов: " + stat.getCancelledOrders());
        System.out.println("Всего дохода: " + stat.getTotalIncome());
    }

    private String handleDishChoice(List<DishDto> dishes) {
        for (int i = 0; i < dishes.size(); i++) {
            DishDto dish = dishes.get(i);
            System.out.println(i + ". " + dish.getName());
        }

        System.out.print("Выберите блюдо: ");
        int dishIndex = tryScanInteger();
        return dishes.get(dishIndex).getName();
    }

    private int tryScanInteger() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new RuntimeException("Неверно введено число");
        }
    }

    private double tryScanDouble() {
        try {
            String input = scanner.nextLine().trim();
            return Double.parseDouble(input);
        } catch (Exception e) {
            System.out.println("ok");
            throw new RuntimeException("Неверно введено число");
        }
    }
}
