package temp_package;

import java.util.List;

public class CheckoutFacade {
    private final ShoppingCart cart;
    private final InventoryManager inventoryManager;

    public CheckoutFacade(ShoppingCart cart, InventoryManager inventoryManager) {
        this.cart = cart;
        this.inventoryManager = inventoryManager;
    }

    public void checkout(PaymentFactory factory, String orderId) {
        if (!hasSufficientInventory()) {
            System.out.println("Cannot proceed with checkout. Some items are out of stock.");
            return;
        }

        System.out.println("--- Checkout Summary ---");
        System.out.println(cart.getSummary());

        double total = cart.calculateTotal();
        System.out.println("Subtotal: $" + String.format("%.2f", cart.calculateSubtotal()));
        System.out.println("Total after discounts: $" + String.format("%.2f", total));

        PaymentProcessor processor = factory.createProcessor();
        boolean success = processor.process(total);
        if (!success) {
            System.out.println("Payment failed. Please try another method.");
            return;
        }

        List<CartItem> items = cart.getItems();
        for (CartItem item : items) {
            inventoryManager.decrementStock(item.getProduct(), item.getQuantity());
        }

        ReceiptGenerator receiptGenerator = factory.createReceiptGenerator();
        String receipt = receiptGenerator.generate(orderId, total);
        System.out.println(receipt);
        System.out.println("Order completed successfully!\n");
    }

    private boolean hasSufficientInventory() {
        return cart.getItems().stream()
                .allMatch(item -> inventoryManager.getStock(item.getProduct()) >= item.getQuantity());
    }
}
