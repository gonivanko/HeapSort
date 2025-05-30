package speedtest;

import model.SortingObject;
import model.SortingObjects;
import sort.HeapSort;

public class Speedtest {
    private final int objectsNumber;
    private final boolean isParallel;
    private long sortingTimeNs = 0;
    private boolean isSorted = false;
    private int poolSize = 12;
    private int parallelThreshold = 10_000;
    public Speedtest(int objectsNumber, boolean isParallel) {
        this.objectsNumber = objectsNumber;
        this.isParallel = isParallel;
    }
    public Speedtest(int objectsNumber, boolean isParallel, int poolSize, int parallelThreshold) {
        this.objectsNumber = objectsNumber;
        this.isParallel = isParallel;
        this.poolSize = poolSize;
        this.parallelThreshold = parallelThreshold;
    }

    public void run() {
        SortingObject[] sortingObjects = SortingObjects.createRandom(objectsNumber);
        long startTime = System.nanoTime();
        if (isParallel) {
            HeapSort.parallelSort(sortingObjects, 0, sortingObjects.length, poolSize, parallelThreshold);
        }
        else {
            HeapSort.sequentialSort(sortingObjects, 0, sortingObjects.length);
        }
        long endTime = System.nanoTime();

        sortingTimeNs = endTime - startTime;
        isSorted = SortingObjects.isSorted(sortingObjects);
//        System.out.println("Is Sorted: " + isSorted);

    }
    public long getSortingTimeNs() {
        return sortingTimeNs;
    }
    public double getSortingTimeMs() {
        return sortingTimeNs / 1000000.0;
    }
    public boolean isSorted() {
        return isSorted;
    }
 }
