package temp_package;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Product {
    private final String name;
    private final String category;
    private final Map<String, String> attributes;
    private final double basePrice;
    private double currentPrice;

    Product(String name, String category, Map<String, String> attributes, double basePrice) {
        this.name = name;
        this.category = category;
        this.attributes = new LinkedHashMap<>(attributes);
        this.basePrice = basePrice;
        this.currentPrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void updatePrice(double newPrice) {
        this.currentPrice = newPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", attributes=" + attributes +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
