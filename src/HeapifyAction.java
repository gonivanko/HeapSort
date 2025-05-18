import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class HeapifyAction extends RecursiveAction {
    int[] arr;
    int index;

    public HeapifyAction(int[] arr, int index) {
        this.arr = arr;
        this.index = index;
    }

    public static int getLeftChild(int index) {
        return index * 2 + 1;
    }
    public static int getRightChild(int index) {
        return index * 2 + 2;
    }
    public static int getParent(int index) {
        return (index - 1) / 2;
    }
    public static int getDepth(int index){
        return (int) Math.floor(Math.log(index + 1) / Math.log(2));
    }

    @Override
    protected void compute() {

        int len = arr.length;

        if (index > len - 1) {
            return;
        }

        int depthDifference = getDepth(len - 1) - getDepth(index);

//        if (getDepth(index) > 2) {
//            long startTime = System.nanoTime();
//            int lastIndex = index;
//            while (getRightChild(lastIndex) <= len - 1) {
//                lastIndex = lastIndex * 2 + 2;
//            }
//
//
//            while (lastIndex >= index) {
//                for (int j = 0; j < Math.pow(2, getDepth(lastIndex)); j++) {
//                    HeapSort.heapify(arr, lastIndex - j, len);
//                }
//                lastIndex = getParent(lastIndex);
//            }
//            long endTime = System.nanoTime();
//            return;
//        }



        if (depthDifference < 2) {
            HeapSort.heapify(arr, index, len);
            return;
        }

        long startTimeGeneral = System.nanoTime();
        int leftChildIndex = getLeftChild(index);
        int rightChildIndex = getRightChild(index);



        HeapifyAction left = new HeapifyAction(arr, leftChildIndex);
        HeapifyAction right = new HeapifyAction(arr, rightChildIndex);

        invokeAll(left, right);
//        left.invoke();
//        right.invoke();
        long endTimeGeneral = System.nanoTime();

        long heapifyTime1 = System.nanoTime();
        HeapSort.heapify(arr, index, len);
        long heapifyTime2 = System.nanoTime();


//        System.out.printf("index: %d, general time: %.2f, heapify time: %.2f\n", index, ((double) (endTimeGeneral - startTimeGeneral) / 1_000_000), ((double) (heapifyTime2 - heapifyTime1) / 1000_000));




    }
}
