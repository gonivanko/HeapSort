import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int arraySize = 1000_000_000;
        int testsNumber = 10;
        long[] sortingTime = new long[testsNumber];

//        Speedtest test = new Speedtest(arraySize, testsNumber, sortingTime);
//        test.start();
//        Speedtest parallelTest = new Speedtest(arraySize, testsNumber, sortingTime, true);
//        parallelTest.start();
//
//        double averageTimeSequential = test.getAverageTimeMs();
//        double averageTimeParallel = parallelTest.getAverageTimeMs();
//        System.out.printf("Average time: %.2f ms\n", averageTimeSequential);
//        System.out.printf("Average time: %.2f ms\n", averageTimeParallel);
//        System.out.printf("Speedup: %.2f\n", averageTimeSequential / averageTimeParallel);

        int[] array = ArrayUtil.randomArray(1, 100, arraySize);
        System.out.println("Chunk size: " + ArrayUtil.getChunkSize(array, ForkJoinPool.commonPool().getParallelism()));
        System.out.println("Remainder: " + array.length % ForkJoinPool.commonPool().getParallelism());
//        long startTime = System.nanoTime();
//        HeapSort heapSort = new HeapSort(array);
//
////        heapSort.sort();
//        heapSort.parallelSort();
//
//        long endTime = System.nanoTime();
//        System.out.println("Sorting time: " + (endTime - startTime) / 1_000_000 + " ms");
////        System.out.println("Array: " + Arrays.toString(array));
//
//        System.out.println("Is sorted: " + ArrayUtil.checkIfSorted(array));




    }
}