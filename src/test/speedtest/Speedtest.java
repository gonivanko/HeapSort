package test.speedtest;

import model.Product;
import model.Products;
import sort.HeapSort;

import java.util.Comparator;

public class Speedtest<T> {
    private final int objectsNumber;
    private final boolean isParallel;
    private final Comparator<T> comparator;
    private long sortingTimeNs = 0;
    private boolean isSorted = false;
    private int poolSize = 12;
    private int heapsortThreshold = 10_000;
    public Speedtest(int objectsNumber, boolean isParallel, Comparator<T> comparator) {
        this.objectsNumber = objectsNumber;
        this.isParallel = isParallel;
        this.comparator = comparator;
    }
    public Speedtest(int objectsNumber, boolean isParallel, Comparator<T> comparator, int poolSize, int heapsortThreshold) {
        this.objectsNumber = objectsNumber;
        this.isParallel = isParallel;
        this.comparator = comparator;
        this.poolSize = poolSize;
        this.heapsortThreshold = heapsortThreshold;
    }

    public void run() {
        T[] products = (T[]) Products.createRandom(objectsNumber);
        long startTime = System.nanoTime();
        HeapSort<T> sorter = new HeapSort<T>();
        if (isParallel) {
            sorter.parallelSort(products, 0, products.length, poolSize, heapsortThreshold, comparator);
        }
        else {
            sorter.sequentialSort(products, 0, products.length, heapsortThreshold, comparator);
        }
        long endTime = System.nanoTime();

        sortingTimeNs = endTime - startTime;
        isSorted = Products.isSorted(products, comparator);

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
