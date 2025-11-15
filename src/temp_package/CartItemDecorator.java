package temp_package;

public abstract class CartItemDecorator implements CartItem {
    protected final CartItem item;

    protected CartItemDecorator(CartItem item) {
        this.item = item;
    }

    @Override
    public Product getProduct() {
        return item.getProduct();
    }

    @Override
    public int getQuantity() {
        return item.getQuantity();
    }
}
