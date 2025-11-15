package ECommerce.decorator;

import ECommerce.cart.CartItem;
import ECommerce.product.Product;

public abstract class CartItemDecorator implements CartItem {
    protected final CartItem item;

    protected CartItemDecorator(CartItem item) {
        this.item = item;
    }

    @Override
    public Product getProduct() {
        return item.getProduct();
    }

    @Override
    public int getQuantity() {
        return item.getQuantity();
    }
}
