public class SpeedtestResults {
    int arraySize;
    int testsNumber;
    Result[] results;
    boolean isParallel = false;

    public SpeedtestResults(int arraySize, int testsNumber) {
        this.arraySize = arraySize;
        this.testsNumber = testsNumber;
        this.results = new Result[testsNumber];
    }
    public SpeedtestResults(int arraySize, int testsNumber, boolean isParallel) {
        this.arraySize = arraySize;
        this.testsNumber = testsNumber;
        this.isParallel = isParallel;
        this.results = new Result[testsNumber];
    }
    public void setResult(Result result, int index) {
        results[index] = result;
    }
    public Result getResult(int index) {
        return results[index];
    }
    public long getAverageTimeNs() {
        long averageTime = 0;
        for (int i = 0; i < testsNumber; i++) {
            averageTime += results[i].getSortingTime();
        }
        return averageTime / testsNumber;
    }

    public double getAverageTimeMs() {
        double averageTime = 0;
        int nanoInMilli = 1000_1000;
        for (int i = 0; i < testsNumber; i++) {
            averageTime += (double) results[i].getSortingTime() / nanoInMilli;
        }
        return averageTime / testsNumber;
    }
    public void printAverageTimeMs() {
        System.out.printf("Average time: %.2f ms\n", getAverageTimeMs());
    }
    public void printResults() {
        for (int i = 0; i < testsNumber; i++) {
            System.out.println(results[i].toString());
        }
    }


}
