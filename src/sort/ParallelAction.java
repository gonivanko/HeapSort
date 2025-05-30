package sort;

import model.SortingObject;

import java.util.concurrent.RecursiveAction;

public class ParallelAction extends RecursiveAction {
    private final SortingObject[] array;
    private final int start;
    private final int end;
    private int THRESHOLD = 10_000;
    private final int arrayLength;

    public ParallelAction(SortingObject[] array, int start, int end, int Threshold) {
        this.array = array;
        this.start = start;
        this.end = end;
        arrayLength = end - start;
        this.THRESHOLD = Threshold;
    }

    @Override
    protected void compute() {
        if (arrayLength <= THRESHOLD) {
            HeapSort.sort(array, start, end);
        }
        else {
            int mid = (start + end) / 2;
            ParallelAction left = new ParallelAction(array, start, mid, THRESHOLD);
            ParallelAction right = new ParallelAction(array, mid, end, THRESHOLD);
            invokeAll(left, right);
            HeapSort.merge(array, start, mid, end);
        }
    }
}
