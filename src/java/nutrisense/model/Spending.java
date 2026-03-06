package nutrisense.model;

public class Spending {

    private User user;
    private double totalSpending;

    public Spending(User user, double totalSpending) {
        this.user = user;
        this.totalSpending = totalSpending;
    }

    public Spending() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(double totalSpending) {
        this.totalSpending = totalSpending;
    }

}
