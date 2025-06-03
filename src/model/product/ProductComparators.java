package model.product;

import java.util.Comparator;

public class ProductComparators {
    public static final Comparator<Product> BY_PRICE =
            Comparator.comparingInt(Product::getCentsPrice);

    public static final Comparator<Product> BY_WEIGHT =
            Comparator.comparingDouble(Product::getWeight);

    public static final Comparator<Product> BY_NAME =
            Comparator.comparing(Product::getName);
}
