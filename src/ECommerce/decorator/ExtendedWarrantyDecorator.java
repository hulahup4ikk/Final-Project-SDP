package ECommerce.decorator;

import ECommerce.cart.CartItem;

public class ExtendedWarrantyDecorator extends CartItemDecorator {
    private final int years;
    private final double yearlyCost;

    public ExtendedWarrantyDecorator(CartItem item, int years, double yearlyCost) {
        super(item);
        this.years = years;
        this.yearlyCost = yearlyCost;
    }

    @Override
    public double getCost() {
        return item.getCost() + yearlyCost * years;
    }

    @Override
    public String getDescription() {
        return item.getDescription() + " + " + years + " year warranty";
    }
}
