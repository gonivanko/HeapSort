package test.speedtest;

import model.SortingObjects;
import sort.HeapSort;

import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class Speedtest<T> {
    private final int objectsNumber;
    private final boolean isParallel;

    private long sortingTimeNs = 0;
    private boolean isSorted = false;
    private int poolSize = 12;
    private int heapsortThreshold = 10_000;

    private final Comparator<T> comparator;
    private final Supplier<T> generator;
    private final IntFunction<T[]> arrayFactory;


    public Speedtest(int objectsNumber, boolean isParallel, Comparator<T> comparator, Supplier<T> generator, IntFunction<T[]> arrayFactory) {
        this.objectsNumber = objectsNumber;
        this.isParallel = isParallel;
        this.comparator = comparator;
        this.generator = generator;
        this.arrayFactory = arrayFactory;
    }
    public Speedtest(int objectsNumber, boolean isParallel, Comparator<T> comparator, int poolSize, int heapsortThreshold, Supplier<T> generator, IntFunction<T[]> arrayFactory) {
        this.objectsNumber = objectsNumber;
        this.isParallel = isParallel;
        this.comparator = comparator;
        this.poolSize = poolSize;
        this.heapsortThreshold = heapsortThreshold;
        this.generator = generator;
        this.arrayFactory = arrayFactory;
    }

    public void run() {
        T[] products = SortingObjects.createRandomArray(objectsNumber, generator, arrayFactory);
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
        isSorted = SortingObjects.isSorted(products, comparator);

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
