package ECommerce.inventory;

import ECommerce.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryManager {
    private final Map<Product, Integer> stockByProduct = new HashMap<>();
    private final List<InventoryObserver> observers = new ArrayList<>();

    public void addProduct(Product product, int initialStock) {
        stockByProduct.put(product, initialStock);
        notifyObservers(product);
    }

    public void registerObserver(InventoryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(InventoryObserver observer) {
        observers.remove(observer);
    }

    public void updatePrice(Product product, double newPrice) {
        product.updatePrice(newPrice);
        notifyObservers(product);
    }

    public void updateStock(Product product, int newStock) {
        stockByProduct.put(product, newStock);
        notifyObservers(product);
    }

    public void decrementStock(Product product, int amount) {
        int currentStock = stockByProduct.getOrDefault(product, 0);
        int updatedStock = Math.max(currentStock - amount, 0);
        stockByProduct.put(product, updatedStock);
        notifyObservers(product);
    }

    public int getStock(Product product) {
        return stockByProduct.getOrDefault(product, 0);
    }

    private void notifyObservers(Product product) {
        int stock = stockByProduct.getOrDefault(product, 0);
        double price = product.getCurrentPrice();
        for (InventoryObserver observer : observers) {
            observer.onInventoryChange(product, stock, price);
        }
    }
}
