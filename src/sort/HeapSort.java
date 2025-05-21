package sort;

import model.SortingObject;

public class HeapSort{
//    private final SortingObject[] arr;
//    public HeapSort(SortingObject[] arr) {
//        this.arr = arr;
//    }
    public static void heapify(SortingObject[] arr, int index, int len) {
        int maxIndex = index;
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;

        if (leftChildIndex < len) {
            if (arr[leftChildIndex].compareTo(arr[maxIndex]) > 0) {
                maxIndex = leftChildIndex;
            }
        }
        if (rightChildIndex < len) {
            if (arr[rightChildIndex].compareTo(arr[maxIndex]) > 0) {
                maxIndex = rightChildIndex;
            }
        }
        if (maxIndex != index) {
            SortingObject temp = arr[index];
            arr[index] = arr[maxIndex];
            arr[maxIndex] = temp;
            heapify(arr, maxIndex, len);
        }
    }
    public static void sort(SortingObject[] arr, int start, int end) {
        int arrayLength = end - start;
        System.out.println("Length: " + arrayLength);
        for (int i = start + arrayLength / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arrayLength);
        }
        for (int i = arrayLength - 1; i >= 0; i--) {
            SortingObject temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapify(arr, 0, i);
        }
    }
}
