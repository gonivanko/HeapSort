package model;

import java.util.Comparator;

public class Products {
    public static Product[] createRandom(int size) {
        Product[] products = new Product[size];
        for (int i = 0; i < size; i++) {
            products[i] = Product.createRandom();
        }
        return products;
    }
    public static void print(Product[] products) {
        String header = "|   Index   |   Name   | Price | Weight |";
        System.out.println("-".repeat(header.length()));
        System.out.println(header);
        System.out.println("-".repeat(header.length()));

        for (int i = 0; i < products.length; i++) {
            System.out.printf("| %9d | %8s | %5d | %6.2f |\n", i, products[i].getName(), products[i].getCentsPrice(),
                    products[i].getWeight());
        }
        System.out.println("-".repeat(header.length()));
    }
    public static boolean isSorted(Product[] products, Comparator<Product> comparator) {
        for (int i = 1; i < products.length; i++) {
            if (comparator.compare(products[i], products[i - 1]) < 0)
                return false;
        }
        return true;
    }
}
