package ECommerce.ui;

import ECommerce.inventory.InventoryObserver;
import ECommerce.product.Product;

public class StorefrontDisplay implements InventoryObserver {
    private final String channelName;

    public StorefrontDisplay(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void onInventoryChange(Product product, int newStock, double newPrice) {
        System.out.println("[" + channelName + "] " + product.getName() + " now costs $" +
                String.format("%.2f", newPrice) + " with " + newStock + " units available.");
    }
}
