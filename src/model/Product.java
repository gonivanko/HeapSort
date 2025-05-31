package model;

public class Product implements Comparable<Product> {
    private final String name;
    private final int centsPrice;
    private final double weight;

    public Product(String name, int value, double weight) {
        this.name = name;
        this.centsPrice = value;
        this.weight = weight;
    }
    public String getName() {
        return name;
    }
    public int getCentsPrice() {
        return centsPrice;
    }
    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Product o) {
        return Integer.compare(this.centsPrice, o.centsPrice);
    }
    public String toString() {
        return String.format("Name: %8s, int: %2d, double: %2.2f", name, centsPrice, weight);
    }
    public static Product createRandom() {
        String[] productNames = {"Milk", "Bread", "Cheese", "Apple", "Banana"};
        String randomName = productNames[(int) (Math.random() * productNames.length)];
        int randomPrice = 1 + (int) (Math.random() * 100);
        double randomWeight = 20 + Math.random() * 500;
        return new Product(randomName, randomPrice, randomWeight);
    }

}
