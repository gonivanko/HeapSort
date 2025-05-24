package sort;

import model.SortingObject;

import java.util.concurrent.ForkJoinPool;

public class HeapSort{
    public static void heapify(SortingObject[] arr, int index, int len, int offset) {
        int maxIndex = index;
        int leftChildIndex = offset + (index - offset) * 2 + 1;
        int rightChildIndex = offset + (index - offset) * 2 + 2;

        if (leftChildIndex < (len + offset)) {
            if (arr[leftChildIndex].compareTo(arr[maxIndex]) > 0) {
                maxIndex = leftChildIndex;
            }
        }
        if (rightChildIndex < (len + offset)) {
            if (arr[rightChildIndex].compareTo(arr[maxIndex]) > 0) {
                maxIndex = rightChildIndex;
            }
        }
        if (maxIndex != index) {
            SortingObject temp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;
            heapify(arr, maxIndex, len, offset);
        }
    }
    public static void sort(SortingObject[] arr, int start, int end) {
        int arrayLength = end - start;
        for (int i = start + arrayLength / 2 - 1; i >= start; i--) {
            heapify(arr, i, arrayLength, start);
        }
        for (int i = start + arrayLength - 1; i >= start; i--) {
            SortingObject temp = arr[i];
            arr[i] = arr[start];
            arr[start] = temp;
            heapify(arr, start, i - start, start);
        }
    }
    public static void merge(SortingObject[] arr, int start, int mid, int end) {
        int arrayLength = end - start;
        SortingObject[] temp = new SortingObject[arrayLength];
        int i = start, j = mid, k = 0;
        while (i < mid && j < end) {
            if (arr[i].compareTo(arr[j]) < 0) {
                temp[k++] = arr[i++];
            }
            else {
                temp[k++] = arr[j++];
            }
        }
        while (i < mid) {
            temp[k++] = arr[i++];
        }
        while (j < end) {
            temp[k++] = arr[j++];
        }
        System.arraycopy(temp, 0, arr, start, temp.length);
    }
    public static void sequentialSort(SortingObject[] arr, int start, int end) {
        final int THRESHOLD = 100_000;
        int arrayLength = end - start;

        if (arrayLength < THRESHOLD) {
            sort(arr, start, end);
        }
        else {
            int mid = start + arrayLength / 2;
            sequentialSort(arr, start, mid);
            sequentialSort(arr, mid, end);
            merge(arr, start, mid, end);
        }
    }
    public static void parallelSort(SortingObject[] arr, int start, int end) {

        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new ParallelAction(arr, start, end));
    }
}
