package temp_package;

public class ProductCartItem implements CartItem {
    private final Product product;
    private final int quantity;

    public ProductCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public double getCost() {
        return product.getCurrentPrice() * quantity;
    }

    @Override
    public String getDescription() {
        return quantity + " x " + product.getName();
    }
}
