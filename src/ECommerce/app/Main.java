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

    private static final String STORE_NAME = "Web Store";

    private static final String LAPTOP_NAME = "Zephyr Gaming Laptop";
    private static final String LAPTOP_CATEGORY = "Electronics";
    private static final double LAPTOP_BASE_PRICE = 1499.99;
    private static final double LAPTOP_UPDATED_PRICE = 1399.99;

    private static final String HEADPHONES_NAME = "SilentWave Headphones";
    private static final String HEADPHONES_CATEGORY = "Audio";
    private static final double HEADPHONES_BASE_PRICE = 249.99;

    private static final int LAPTOP_INITIAL_STOCK = 4;
    private static final int HEADPHONES_INITIAL_STOCK = 12;
    private static final int HEADPHONES_UPDATED_STOCK = 10;

    private static final int WARRANTY_YEARS = 2;
    private static final double WARRANTY_YEARLY_COST = 129.99;

    private static final String GIFT_WRAP_LAPTOP_STYLE = "Premium Matte Black";
    private static final double GIFT_WRAP_LAPTOP_COST = 7.99;

    private static final String GIFT_WRAP_HEADPHONES_STYLE = "Minimalist";
    private static final double GIFT_WRAP_HEADPHONES_COST = 4.99;

    private static final double SEASONAL_PERCENT_DISCOUNT = 10.0;
    private static final int BULK_THRESHOLD = 3;
    private static final double BULK_PERCENT_DISCOUNT = 15.0;
    private static final double ACCESSORY_PERCENT_DISCOUNT = 5.0;

    private static final String ORDER_ID_1 = "ORD-1001";
    private static final String ORDER_ID_2 = "ORD-1002";


    public static void main(String[] args) {

        // Managers & UI
        InventoryManager inventoryManager = new InventoryManager();
        ShoppingCart cart = new ShoppingCart();
        StorefrontDisplay storefrontDisplay = new StorefrontDisplay(STORE_NAME);

        inventoryManager.registerObserver(cart);
        inventoryManager.registerObserver(storefrontDisplay);

        // ============================
        //      PRODUCT CREATION
        // ============================

        Product gamingLaptop = new ProductBuilder()
                .withName(LAPTOP_NAME)
                .withCategory(LAPTOP_CATEGORY)
                .withBasePrice(LAPTOP_BASE_PRICE)
                .addAttribute("GPU", "RTX 4070")
                .addAttribute("RAM", "32GB")
                .addAttribute("Storage", "1TB NVMe")
                .build();

        Product headphones = new ProductBuilder()
                .withName(HEADPHONES_NAME)
                .withCategory(HEADPHONES_CATEGORY)
                .withBasePrice(HEADPHONES_BASE_PRICE)
                .addAttribute("Battery", "30h")
                .addAttribute("Color", "Midnight Blue")
                .build();

        inventoryManager.addProduct(gamingLaptop, LAPTOP_INITIAL_STOCK);
        inventoryManager.addProduct(headphones, HEADPHONES_INITIAL_STOCK);

        inventoryManager.updatePrice(gamingLaptop, LAPTOP_UPDATED_PRICE);
        inventoryManager.updateStock(headphones, HEADPHONES_UPDATED_STOCK);

        // ============================
        //        CART ITEMS
        // ============================

        CartItem laptopItem = new ProductCartItem(gamingLaptop, 1);
        laptopItem = new ExtendedWarrantyDecorator(laptopItem, WARRANTY_YEARS, WARRANTY_YEARLY_COST);
        laptopItem = new GiftWrapDecorator(laptopItem, GIFT_WRAP_LAPTOP_STYLE, GIFT_WRAP_LAPTOP_COST);
        cart.addItem(laptopItem);

        CartItem headphonesItem = new ProductCartItem(headphones, 2);
        headphonesItem = new GiftWrapDecorator(headphonesItem, GIFT_WRAP_HEADPHONES_STYLE, GIFT_WRAP_HEADPHONES_COST);
        cart.addItem(headphonesItem);

        // ============================
        //       DISCOUNTS
        // ============================

        System.out.println("\nSubtotal before discounts: $" + String.format("%.2f", cart.calculateSubtotal()));

        cart.setDiscountStrategy(new PercentageDiscountStrategy(SEASONAL_PERCENT_DISCOUNT));
        System.out.println("Applying seasonal sale (" + SEASONAL_PERCENT_DISCOUNT +
                "% off). Total: $" + String.format("%.2f", cart.calculateTotal()));

        cart.setDiscountStrategy(new BulkPurchaseStrategy(BULK_THRESHOLD, BULK_PERCENT_DISCOUNT));
        System.out.println("Applying bulk discount (" + BULK_PERCENT_DISCOUNT +
                "% off for " + BULK_THRESHOLD + "+ items). Total: $" +
                String.format("%.2f", cart.calculateTotal()));

        // ============================
        //       CHECKOUT #1
        // ============================

        CheckoutFacade checkout1 = new CheckoutFacade(cart, inventoryManager);
        checkout1.checkout(new CreditCardPaymentFactory(), ORDER_ID_1);

        // ============================
        //       CHECKOUT #2
        // ============================

        System.out.println("Switching to PayPal factory for a quick accessories restock.\n");

        ShoppingCart accessoryCart = new ShoppingCart();
        inventoryManager.registerObserver(accessoryCart);
        accessoryCart.setDiscountStrategy(new PercentageDiscountStrategy(ACCESSORY_PERCENT_DISCOUNT));

        CartItem singleHeadphones = new ProductCartItem(headphones, 1);
        accessoryCart.addItem(singleHeadphones);

        CheckoutFacade checkout2 = new CheckoutFacade(accessoryCart, inventoryManager);
        checkout2.checkout(new PayPalPaymentFactory(), ORDER_ID_2);
    }
}
