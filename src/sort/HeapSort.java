package sort;

import model.Product;

import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;

public class HeapSort<T>{
    public void heapify(T[] arr, int index, int len, int offset, Comparator<T> comparator) {
        int maxIndex = index;
        int leftChildIndex = offset + (index - offset) * 2 + 1;
        int rightChildIndex = offset + (index - offset) * 2 + 2;

        if (leftChildIndex < (len + offset)) {
            if (comparator.compare(arr[leftChildIndex], arr[maxIndex]) > 0) {
                maxIndex = leftChildIndex;
            }
        }
        if (rightChildIndex < (len + offset)) {
            if (comparator.compare(arr[rightChildIndex], arr[maxIndex]) > 0) {
                maxIndex = rightChildIndex;
            }
        }
        if (maxIndex != index) {
            T temp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;
            heapify(arr, maxIndex, len, offset, comparator);
        }
    }
    public void sort(T[] arr, int start, int end, Comparator<T> comparator) {
        int arrayLength = end - start;
        for (int i = start + arrayLength / 2 - 1; i >= start; i--) {
            heapify(arr, i, arrayLength, start, comparator);
        }
        for (int i = start + arrayLength - 1; i >= start; i--) {
            T temp = arr[i];
            arr[i] = arr[start];
            arr[start] = temp;
            heapify(arr, start, i - start, start, comparator);
        }
    }
    public void merge(T[] arr, int start, int mid, int end, Comparator<T> comparator) {
        int arrayLength = end - start;
        T[] temp = (T[]) new Object[arrayLength];
        int i = start, j = mid, k = 0;
        while (i < mid && j < end) {
            if (comparator.compare(arr[i], arr[j]) < 0) {
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
    public void sequentialSort(T[] arr, int start, int end, int threshold, Comparator<T> comparator) {
        int arrayLength = end - start;

        if (arrayLength < threshold) {
            sort(arr, start, end, comparator);
        }
        else {
            int mid = start + arrayLength / 2;
            sequentialSort(arr, start, mid, threshold, comparator);
            sequentialSort(arr, mid, end, threshold, comparator);
            merge(arr, start, mid, end, comparator);
        }
    }
    public void parallelSort(T[] arr, int start, int end, int poolSize, int threshold, Comparator<T> comparator) {
        if (poolSize > 1) {
            ForkJoinPool pool = new ForkJoinPool(poolSize);
            pool.invoke(new ParallelAction<T>(arr, start, end, threshold, comparator));
        }
    }
}
