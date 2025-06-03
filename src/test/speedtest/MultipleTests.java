package test.speedtest;

import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.function.Supplier;

import static test.speedtest.Results.allAreSorted;

public class MultipleTests<T> {
    private final int objectsNumber;
    private final int testsNumber;
    private final int poolSize;
    private final int heapsortThreshold;

    boolean runSequential = true;
    boolean runParallel = true;

    private final Result[] results;

    private final Comparator<T> comparator;
    private final Supplier<T> generator;
    private final IntFunction<T[]> arrayFactory;



    public MultipleTests(int objectsNumber, int testsNumber, int poolSize, int heapsortThreshold, Comparator<T> comparator, Supplier<T> generator, IntFunction<T[]> arrayFactory) {
        this.objectsNumber = objectsNumber;
        this.testsNumber = testsNumber;
        results = new Result[testsNumber];
        this.poolSize = poolSize;
        this.heapsortThreshold = heapsortThreshold;

        this.comparator = comparator;
        this.generator = generator;
        this.arrayFactory = arrayFactory;
    }
    public MultipleTests(int objectsNumber, int testsNumber, int poolSize, int heapsortThreshold, Comparator<T> comparator, boolean runSequential, boolean runParallel, Supplier<T> generator, IntFunction<T[]> arrayFactory) {
        this.objectsNumber = objectsNumber;
        this.testsNumber = testsNumber;
        results = new Result[testsNumber];
        this.poolSize = poolSize;
        this.heapsortThreshold = heapsortThreshold;

        this.runSequential = runSequential;
        this.runParallel = runParallel;

        this.comparator = comparator;
        this.generator = generator;
        this.arrayFactory = arrayFactory;
    }
    public void run() {
        if (!(runSequential || runParallel)) {
            System.out.println("No tests to run");
            return;
        }
        for (int i = 0; i < testsNumber; i++) {
            Speedtest<T> sequentialTest, parallelTest;
            boolean isSortedSequential = true, isSortedParallel = true;
            double sequentialTime = -1, parallelTime = -1;
            if (runSequential) {
                sequentialTest = new Speedtest<T>(objectsNumber, false, comparator, generator, arrayFactory);
                sequentialTest.run();
                sequentialTime = sequentialTest.getSortingTimeMs();
                isSortedSequential = sequentialTest.isSorted();
            }

            if (runParallel) {
                parallelTest = new Speedtest<T>(objectsNumber, true, comparator, poolSize, heapsortThreshold, generator, arrayFactory);
                parallelTest.run();
                parallelTime = parallelTest.getSortingTimeMs();
                isSortedParallel = parallelTest.isSorted();
            }

            results[i] = new Result(
                    objectsNumber, sequentialTime, parallelTime,
                    isSortedSequential && isSortedParallel, poolSize, heapsortThreshold
            );
            System.out.printf("Test #%d, objects: %d\r", (i + 1), objectsNumber);
        }
        if (allAreSorted(results)) {
            System.out.println("All are sorted");
        }
        else {
            System.out.println("Not all are sorted");
        }

    }
    public Result[] getResults() {
        return results;
    }
    public Result getAverageResult() {
        return Results.getAverageResult(results);
    }
}
