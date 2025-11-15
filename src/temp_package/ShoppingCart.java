package temp_package;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart implements InventoryObserver {
    private final List<CartItem> items = new ArrayList<>();
    private DiscountStrategy discountStrategy = new NoDiscountStrategy();

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculateSubtotal() {
        return items.stream()
                .mapToDouble(CartItem::getCost)
                .sum();
    }

    public double calculateTotal() {
        double subtotal = calculateSubtotal();
        return discountStrategy.applyDiscount(subtotal, this);
    }

    public int getTotalQuantity() {
        return items.stream().mapToInt(CartItem::getQuantity).sum();
    }

    public int getQuantityForProduct(Product product) {
        return items.stream()
                .filter(item -> item.getProduct().equals(product))
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    @Override
    public void onInventoryChange(Product product, int newStock, double newPrice) {
        boolean inCart = getQuantityForProduct(product) > 0;
        if (inCart) {
            System.out.println("[Cart] Updated price for " + product.getName() + ": " + newPrice);
            if (newStock == 0) {
                System.out.println("[Cart] Warning: " + product.getName() + " is now out of stock!");
            }
        }
        System.out.println("[Inventory] " + product.getName() + " - stock: " + newStock + ", price: " + newPrice);
    }

    public String getSummary() {
        return items.stream()
                .map(item -> " - " + item.getDescription() + " => " + item.getCost())
                .collect(Collectors.joining("\n"));
    }
}
