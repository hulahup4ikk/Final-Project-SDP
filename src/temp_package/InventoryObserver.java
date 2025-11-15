package temp_package;

public interface InventoryObserver {
    void onInventoryChange(Product product, int newStock, double newPrice);
}
