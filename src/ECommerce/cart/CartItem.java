package ECommerce.cart;

import ECommerce.product.Product;

public interface CartItem {
    Product getProduct();

    int getQuantity();

    double getCost();

    String getDescription();
}