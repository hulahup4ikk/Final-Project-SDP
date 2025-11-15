package ECommerce.product;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProductBuilder {
    private String name;
    private String category;
    private double basePrice;
    private final Map<String, String> attributes = new LinkedHashMap<>();

    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public ProductBuilder withBasePrice(double basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public ProductBuilder addAttribute(String key, String value) {
        attributes.put(key, value);
        return this;
    }

    public Product build() {
        if (name == null || name.isEmpty()) {
            throw new IllegalStateException("Product must have a name");
        }
        if (category == null || category.isEmpty()) {
            throw new IllegalStateException("Product must have a category");
        }
        if (basePrice <= 0) {
            throw new IllegalStateException("Product must have a positive price");
        }
        return new Product(name, category, attributes, basePrice);
    }
}
