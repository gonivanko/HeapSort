package sort;

import model.Product;

import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

public class ParallelAction extends RecursiveAction {
    private final Product[] array;
    private final int start;
    private final int end;
    private int THRESHOLD = 10_000;
    private final int arrayLength;
    private final Comparator<Product> comparator;

    public ParallelAction(Product[] array, int start, int end, int Threshold, Comparator<Product> comparator) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.comparator = comparator;
        arrayLength = end - start;
        this.THRESHOLD = Threshold;
    }

    @Override
    protected void compute() {
        if (arrayLength <= THRESHOLD) {
            HeapSort.sort(array, start, end, comparator);
        }
        else {
            int mid = (start + end) / 2;
            ParallelAction left = new ParallelAction(array, start, mid, THRESHOLD, comparator);
            ParallelAction right = new ParallelAction(array, mid, end, THRESHOLD, comparator);
            invokeAll(left, right);
            HeapSort.merge(array, start, mid, end, comparator);
        }
    }
}
