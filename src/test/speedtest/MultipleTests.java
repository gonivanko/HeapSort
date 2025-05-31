package test.speedtest;

import model.Product;

import java.util.Comparator;

import static test.speedtest.Results.allAreSorted;

public class MultipleTests {
    private final int objectsNumber;
    private final int testsNumber;
    private final int poolSize;
    private final int heapsortThreshold;

    boolean runSequential = true;
    boolean runParallel = true;

    private final Comparator<Product> comparator;

    private final ComparativeResult[] comparativeResults;

    public MultipleTests(int objectsNumber, int testsNumber, int poolSize, int heapsortThreshold, Comparator<Product> comparator) {
        this.objectsNumber = objectsNumber;
        this.testsNumber = testsNumber;
        comparativeResults = new ComparativeResult[testsNumber];
        this.poolSize = poolSize;
        this.heapsortThreshold = heapsortThreshold;
        this.comparator = comparator;
    }
    public MultipleTests(int objectsNumber, int testsNumber, int poolSize, int heapsortThreshold, Comparator<Product> comparator, boolean runSequential, boolean runParallel) {
        this.objectsNumber = objectsNumber;
        this.testsNumber = testsNumber;
        comparativeResults = new ComparativeResult[testsNumber];
        this.poolSize = poolSize;
        this.heapsortThreshold = heapsortThreshold;
        this.comparator = comparator;
        this.runSequential = runSequential;
        this.runParallel = runParallel;
    }
    public void run() {
        if (!(runSequential || runParallel)) {
            System.out.println("No tests to run");
            return;
        }
        for (int i = 0; i < testsNumber; i++) {
            Speedtest sequentialTest, parallelTest;
            boolean isSortedSequential = true, isSortedParallel = true;
            double sequentialTime = 0, parallelTime = 0;
            if (runSequential) {
                sequentialTest = new Speedtest(objectsNumber, false, comparator);
                sequentialTest.run();
                sequentialTime = sequentialTest.getSortingTimeMs();
                isSortedSequential = sequentialTest.isSorted();
            }

            if (runParallel) {
                parallelTest = new Speedtest(objectsNumber, true, comparator, poolSize, heapsortThreshold);
                parallelTest.run();
                parallelTime = parallelTest.getSortingTimeMs();
                isSortedParallel = parallelTest.isSorted();
            }

            comparativeResults[i] = new ComparativeResult(
                    objectsNumber, sequentialTime, parallelTime,
                    isSortedSequential && isSortedParallel, poolSize, heapsortThreshold
            );
            System.out.printf("Test #%d\r", (i + 1));
        }
        if (allAreSorted(comparativeResults)) {
            System.out.println("All are sorted");
        }
        else {
            System.out.println("Not all are sorted");
        }

    }
    public ComparativeResult[] getResults() {
        return comparativeResults;
    }
    public ComparativeResult getAverageResult() {
        return Results.getAverageResult(comparativeResults);
    }
}
