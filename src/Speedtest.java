//public class Speedtest {
//    int arraySize;
//    int testsNumber;
////    long[] sortingTime;
//    boolean isParallel = false;
//    SpeedtestResults results;
//
//    public Speedtest(int arraySize, int testsNumber) {
//        this.testsNumber = testsNumber;
//        this.arraySize = arraySize;
//
//        results = new SpeedtestResults(arraySize, testsNumber, isParallel);
//
////        sortingTime = new long[testsNumber];
//    }
//
//    public Speedtest(int arraySize, int testsNumber, long[] sortingTime) {
//        this.testsNumber = testsNumber;
//        this.arraySize = arraySize;
//        results = new SpeedtestResults(arraySize, testsNumber, isParallel);
//    }
//
//    public Speedtest(int arraySize, int testsNumber, long[] sortingTime, boolean isParallel) {
//        this.testsNumber = testsNumber;
//        this.arraySize = arraySize;
//        this.isParallel = isParallel;
//        results = new SpeedtestResults(arraySize, testsNumber, isParallel);
//    }
//
//    public void start() {
//        if (isParallel) {
//            System.out.println("Running parallel sorting speedtest...");
//        }
//        else {
//            System.out.println("Running sequential sorting speedtest...");
//        }
//        for (int i = 0; i < testsNumber; i++) {
//            int[] array = ArrayUtil.randomArray(1, 100, arraySize);
//            long startTime = System.nanoTime();
//            HeapSort heapSort = new HeapSort(array);
//
//            if (isParallel) heapSort.parallelSort();
//            else heapSort.sort();
//
//            long endTime = System.nanoTime();
//            if (ArrayUtil.checkIfSorted(array))
//                System.out.print("Array sorted x" + (i + 1) + "\r");
//            else
//                System.out.print("Array not sorted \n");
//            Result result = new Result((endTime - startTime), ArrayUtil.checkIfSorted(array));
//            results.setResult(result, i);
////            sortingTime[i] = (endTime - startTime) / 1_000_000;
//        }
//        System.out.println("Speedtest completed");
//        results.printAverageTimeMs();
////        results.printResults();
//    }
//    public double getAverageTimeMs() {
//        return results.getAverageTimeMs();
//    }
//}
