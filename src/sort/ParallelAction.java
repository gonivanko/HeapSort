package sort;

import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

public class ParallelAction<T> extends RecursiveAction {
    private final T[] array;
    private final int start;
    private final int end;
    private int THRESHOLD = 10_000;
    private final int arrayLength;
    private final HeapSort<T> sorter;
    private final Comparator<T> comparator;

    public ParallelAction(T[] array, int start, int end, int Threshold, Comparator<T> comparator) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.sorter = new HeapSort<>();
        this.comparator = comparator;
        arrayLength = end - start;
        this.THRESHOLD = Threshold;
    }

    @Override
    protected void compute() {
        if (arrayLength <= THRESHOLD) {
            sorter.sort(array, start, end, comparator);
        }
        else {
            int mid = (start + end) / 2;
            ParallelAction<T> left = new ParallelAction<T>(array, start, mid, THRESHOLD, comparator);
            ParallelAction<T> right = new ParallelAction<T>(array, mid, end, THRESHOLD, comparator);
            invokeAll(left, right);
            sorter.merge(array, start, mid, end, comparator);
        }
    }
}
