import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class HeapSort {
    private final int[] arr;
    public HeapSort(int[] arr) {
        this.arr = arr;
    }
    public static void heapify(int[] arr, int index, int len) {
        int maxIndex = index;
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;

        if (leftChildIndex < len) {
            if (arr[leftChildIndex] > arr[maxIndex]) {
                maxIndex = leftChildIndex;
            }
        }
        if (rightChildIndex < len) {
            if (arr[rightChildIndex] > arr[maxIndex]) {
                maxIndex = rightChildIndex;
            }
        }
        if (maxIndex != index) {
            int temp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;
            heapify(arr, maxIndex, len);
        }
    }
    public void sort() {
        int arrayLength = arr.length;
//        long heapifyTime1 = System.nanoTime();
        for (int i = arrayLength / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arrayLength);
        }
//        long heapifyTime2 = System.nanoTime();
//        System.out.println("Heapify time: " + (heapifyTime2 - heapifyTime1) / 1_000_000);
//        long sortTime1 = System.nanoTime();
        for (int i = arrayLength - 1; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapify(arr, 0, i);
        }
//        long sortTime2 = System.nanoTime();
//        System.out.println("Sorting time: " + (sortTime2 - sortTime1) / 1_000_000);

    }
    public void parallelSort() {
        int arrayLength = arr.length;
        ForkJoinPool pool = new ForkJoinPool();
//        long heapifyTime1 = System.nanoTime();
        pool.invoke(new HeapifyAction(arr, 0));
//        long heapifyTime2 = System.nanoTime();
//        System.out.println("Heapify time: " + (heapifyTime2 - heapifyTime1) / 1_000_000);
//        long sortTime1 = System.nanoTime();
        for (int i = arrayLength - 1; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapify(arr, 0, i);
        }
//        long sortTime2 = System.nanoTime();
//        System.out.println("Sorting time: " + (sortTime2 - sortTime1) / 1_000_000);


    }
}
