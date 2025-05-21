package model;

public class SortingObject implements Comparable<SortingObject> {
    private final String name;
    private final int intValue;
    private final double doubleValue;

    public SortingObject(String name, int value, double doubleValue) {
        this.name = name;
        this.intValue = value;
        this.doubleValue = doubleValue;
    }
    public String getName() {
        return name;
    }
    public int getIntValue() {
        return intValue;
    }
    public double getDoubleValue() {
        return doubleValue;
    }

    @Override
    public int compareTo(SortingObject o) {
        return Integer.compare(this.intValue, o.intValue);
    }
    public String toString() {
        return String.format("Name: %8s, int: %2d, double: %2.2f", name, intValue, doubleValue);
    }
    public static SortingObject createRandomObject() {
        String[] names = {"Ivan", "Denys", "Yehor", "Ruslan", "Mykola"};
        String randomName = names[(int) (Math.random() * names.length)];
        int randomInt = (int) (Math.random() * 100);
        double randomDouble = Math.random() * 100;
        return new SortingObject(randomName, randomInt, randomDouble);
    }

}
