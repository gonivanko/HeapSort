public class Result {
    long sortingTime;
    boolean isSorted;

    public Result(long executionTime, boolean isSorted) {
        this.sortingTime = executionTime;
        this.isSorted = isSorted;
    }
    public Result(long executionTime, int[] array) {
        this.sortingTime = executionTime;
        isSorted = ArrayUtil.checkIfSorted(array);
    }
    public long getSortingTime() {
        return sortingTime;
    }
    public boolean isSorted() {
        return isSorted;
    }
    @Override
    public String toString() {
        return "sortingTime: " + (sortingTime / 1000_1000) + "ms" + ", isSorted: " + isSorted;
    }
}
