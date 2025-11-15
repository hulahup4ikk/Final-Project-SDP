package ECommerce.discount;

import ECommerce.cart.ShoppingCart;

public interface DiscountStrategy {
    double applyDiscount(double subtotal, ShoppingCart cart);
}
