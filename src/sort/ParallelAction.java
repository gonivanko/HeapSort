package sort;

import model.SortingObject;

import java.util.concurrent.RecursiveAction;

public class ParallelAction extends RecursiveAction {
    private SortingObject[] array;
    private int start, end;
    private final int THRESHOLD = 100_000;
    private int arrayLength;

    public ParallelAction(SortingObject[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
        arrayLength = end - start;
    }

    @Override
    protected void compute() {
        if (arrayLength <= THRESHOLD) {
            HeapSort.sort(array, start, end);
        }
        else {
            int mid = (start + end) / 2;
            ParallelAction left = new ParallelAction(array, start, mid);
            ParallelAction right = new ParallelAction(array, mid, end);
            left.fork();

        }
    }
}
