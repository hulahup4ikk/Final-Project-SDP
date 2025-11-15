package temp_package;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private final double percentage;

    public PercentageDiscountStrategy(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double subtotal, ShoppingCart cart) {
        double multiplier = Math.max(0, Math.min(percentage, 100)) / 100.0;
        return subtotal * (1 - multiplier);
    }
}
