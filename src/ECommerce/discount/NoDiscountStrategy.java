package ECommerce.discount;

import ECommerce.cart.ShoppingCart;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double subtotal, ShoppingCart cart) {
        return subtotal;
    }
}
