package model;

public class SortingObjects {
    public static SortingObject[] createRandom(int size) {
        SortingObject[] sortingObjects = new SortingObject[size];
        for (int i = 0; i < size; i++) {
            sortingObjects[i] = SortingObject.createRandomObject();
        }
        return sortingObjects;
    }
    public static void print(SortingObject[] sortingObjects) {
        System.out.println("|   Index   |   Name   | int | double |");
        for (int i = 0; i < sortingObjects.length; i++) {
            System.out.println("-".repeat(38));
            System.out.printf("| %9d | %8s | %3d | %6.2f |\n", i, sortingObjects[i].getName(), sortingObjects[i].getIntValue(),
                    sortingObjects[i].getDoubleValue());
        }
    }
    public static boolean isSorted(SortingObject[] sortingObjects) {
        for (int i = 1; i < sortingObjects.length; i++) {
            if (sortingObjects[i].compareTo(sortingObjects[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
}
