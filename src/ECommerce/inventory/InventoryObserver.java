package ECommerce.inventory;

import ECommerce.product.Product;

public interface InventoryObserver {
    void onInventoryChange(Product product, int newStock, double newPrice);
}
