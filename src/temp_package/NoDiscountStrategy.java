package temp_package;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double subtotal, ShoppingCart cart) {
        return subtotal;
    }
}
