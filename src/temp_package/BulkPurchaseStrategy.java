package temp_package;

public class BulkPurchaseStrategy implements DiscountStrategy {
    private final int threshold;
    private final double bulkDiscount;

    public BulkPurchaseStrategy(int threshold, double bulkDiscount) {
        this.threshold = threshold;
        this.bulkDiscount = bulkDiscount;
    }

    @Override
    public double applyDiscount(double subtotal, ShoppingCart cart) {
        if (cart.getTotalQuantity() >= threshold) {
            double multiplier = Math.max(0, Math.min(bulkDiscount, 100)) / 100.0;
            return subtotal * (1 - multiplier);
        }
        return subtotal;
    }
}