package speedtest;

public class MultipleTests {
    private final int objectsNumber;
    private final int testsNumber;
    private final int poolSize;
    private final int parallelThreshold;
    private final long[] sequentialSortingTimes;
    private final long[] parallelSortingTimes;
    private final Result[] results;

    public MultipleTests(int objectsNumber, int testsNumber, int poolSize, int parallelThreshold) {
        this.objectsNumber = objectsNumber;
        this.testsNumber = testsNumber;
        sequentialSortingTimes = new long[testsNumber];
        parallelSortingTimes = new long[testsNumber];
        results = new Result[testsNumber];
        this.poolSize = poolSize;
        this.parallelThreshold = parallelThreshold;
    }
    public void run() {
        int sequentialSorted = 0;
        int parallelSorted = 0;
        for (int i = 0; i < testsNumber; i++) {
            Speedtest sequentialTest = new Speedtest(objectsNumber, false);
            sequentialTest.run();
            sequentialSortingTimes[i] = sequentialTest.getSortingTimeNs();
            if (sequentialTest.isSorted()) sequentialSorted++;

            Speedtest parallelTest = new Speedtest(objectsNumber, true, poolSize, parallelThreshold);
            parallelTest.run();
            parallelSortingTimes[i] = parallelTest.getSortingTimeNs();
            if (parallelTest.isSorted()) parallelSorted++;
            results[i] = new Result(
                    objectsNumber, sequentialTest.getSortingTimeMs(), parallelTest.getSortingTimeMs(),
                    sequentialTest.isSorted() && parallelTest.isSorted(), poolSize, parallelThreshold
            );
            System.out.printf("Test #%d: %d sequential, %d parallel\r", (i + 1), sequentialSorted, parallelSorted);
        }
        if (sequentialSorted == testsNumber && parallelSorted == testsNumber) {
            System.out.println("All sorted");
        }

    }
    public static double getAverageTimeMs(long[] timesNs) {
        double averageTime = 0;
        for (long time : timesNs) {
            averageTime += time;
        }
        averageTime /= timesNs.length;
        return (averageTime / 1000_000);
    }
    public double getAverageSequentialTimeMs() {
        return getAverageTimeMs(sequentialSortingTimes);
    }
    public double getAverageParallelTimeMs() {
        return getAverageTimeMs(parallelSortingTimes);
    }
    public void displayAverageTimeMs() {
        System.out.println("Average sequential time: " + getAverageTimeMs(sequentialSortingTimes) + " ms");
        System.out.println("Average parallel time: " + getAverageTimeMs(parallelSortingTimes) + " ms");
    }
    public Result[] getResults() {
        return results;
    }
    public Result getAverageResult() {
        return Results.getAverageResult(results);
    }
    public long[] getSequentialSortingTimes() {
        return sequentialSortingTimes;
    }
    public long[] getParallelSortingTimes() {
        return parallelSortingTimes;
    }
}
