package temp_package;

public class GiftWrapDecorator extends CartItemDecorator {
    private final String wrapStyle;
    private final double wrapCost;

    public GiftWrapDecorator(CartItem item, String wrapStyle, double wrapCost) {
        super(item);
        this.wrapStyle = wrapStyle;
        this.wrapCost = wrapCost;
    }

    @Override
    public double getCost() {
        return item.getCost() + wrapCost;
    }

    @Override
    public String getDescription() {
        return item.getDescription() + " + Gift Wrap (" + wrapStyle + ")";
    }
}