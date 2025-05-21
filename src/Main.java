import sort.HeapSort;
import model.SortingObject;
import model.SortingObjects;

public class Main {
    public static void main(String[] args) {

        int objectsNumber = 10;

        SortingObject[] sortingObjects = SortingObjects.createRandom(objectsNumber);
        long startTime = System.nanoTime();

        HeapSort.sort(sortingObjects, 0, sortingObjects.length);
        long endTime = System.nanoTime();
        SortingObjects.print(sortingObjects);
        System.out.println("=".repeat(50));
        System.out.println("Is Sorted: " + SortingObjects.isSorted(sortingObjects));
        System.out.println("Sorting Time: " + ((endTime - startTime) / 1_000_000) + " ms");
    }
}