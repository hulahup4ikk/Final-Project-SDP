package ECommerce.app;

import ECommerce.cart.CartItem;
import ECommerce.cart.ProductCartItem;
import ECommerce.cart.ShoppingCart;
import ECommerce.checkout.CheckoutFacade;
import ECommerce.decorator.ExtendedWarrantyDecorator;
import ECommerce.decorator.GiftWrapDecorator;
import ECommerce.discount.BulkPurchaseStrategy;
import ECommerce.discount.PercentageDiscountStrategy;
import ECommerce.inventory.InventoryManager;
import ECommerce.payment.CreditCardPaymentFactory;
import ECommerce.payment.PayPalPaymentFactory;
import ECommerce.product.Product;
import ECommerce.product.ProductBuilder;
import ECommerce.ui.StorefrontDisplay;

public class Main {
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();
        ShoppingCart cart = new ShoppingCart();
        StorefrontDisplay storefrontDisplay = new StorefrontDisplay("Web Store");

        inventoryManager.registerObserver(cart);
        inventoryManager.registerObserver(storefrontDisplay);

        Product gamingLaptop = new ProductBuilder()
                .withName("Zephyr Gaming Laptop")
                .withCategory("Electronics")
                .withBasePrice(1499.99)
                .addAttribute("GPU", "RTX 4070")
                .addAttribute("RAM", "32GB")
                .addAttribute("Storage", "1TB NVMe")
                .build();

        Product noiseCancellingHeadphones = new ProductBuilder()
                .withName("SilentWave Headphones")
                .withCategory("Audio")
                .withBasePrice(249.99)
                .addAttribute("Battery", "30h")
                .addAttribute("Color", "Midnight Blue")
                .build();

        inventoryManager.addProduct(gamingLaptop, 4);
        inventoryManager.addProduct(noiseCancellingHeadphones, 12);

        inventoryManager.updatePrice(gamingLaptop, 1399.99);
        inventoryManager.updateStock(noiseCancellingHeadphones, 10);

        CartItem laptopWithWarranty = new ProductCartItem(gamingLaptop, 1);
        laptopWithWarranty = new ExtendedWarrantyDecorator(laptopWithWarranty, 2, 129.99);
        laptopWithWarranty = new GiftWrapDecorator(laptopWithWarranty, "Premium Matte Black", 7.99);
        cart.addItem(laptopWithWarranty);

        CartItem headphonesBundle = new ProductCartItem(noiseCancellingHeadphones, 2);
        headphonesBundle = new GiftWrapDecorator(headphonesBundle, "Minimalist", 4.99);
        cart.addItem(headphonesBundle);

        System.out.println("\nSubtotal before discounts: $" + String.format("%.2f", cart.calculateSubtotal()));

        cart.setDiscountStrategy(new PercentageDiscountStrategy(10));
        System.out.println("Applying seasonal sale (10% off). Total: $" + String.format("%.2f", cart.calculateTotal()));

        cart.setDiscountStrategy(new BulkPurchaseStrategy(3, 15));
        System.out.println("Applying loyalty bulk discount (15% off for 3+ items). Total: $" +
                String.format("%.2f", cart.calculateTotal()));

        CheckoutFacade checkoutFacade = new CheckoutFacade(cart, inventoryManager);
        checkoutFacade.checkout(new CreditCardPaymentFactory(), "ORD-1001");

        System.out.println("Switching to PayPal factory for a quick accessories restock.\n");
        ShoppingCart accessoryCart = new ShoppingCart();
        inventoryManager.registerObserver(accessoryCart);
        accessoryCart.setDiscountStrategy(new PercentageDiscountStrategy(5));

        CartItem headphonesSingle = new ProductCartItem(noiseCancellingHeadphones, 1);
        accessoryCart.addItem(headphonesSingle);

        CheckoutFacade accessoryCheckout = new CheckoutFacade(accessoryCart, inventoryManager);
        accessoryCheckout.checkout(new PayPalPaymentFactory(), "ORD-1002");
    }
}