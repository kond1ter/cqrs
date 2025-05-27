package query.dto;

public class StatisticsDto {
    private int totalOrders;
    private int completedOrders;
    private int cancelledOrders;
    private double totalIncome;

    public StatisticsDto(int totalOrders, int completedOrders, int cancelledOrders, double totalIncome) {
        this.totalOrders = totalOrders;
        this.completedOrders = completedOrders;
        this.cancelledOrders = cancelledOrders;
        this.totalIncome = totalIncome;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public int getCancelledOrders() {
        return cancelledOrders;
    }

    public double getTotalIncome() {
        return totalIncome;
    }
}
