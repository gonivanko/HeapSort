public class ArrayUtil {
    public static int[] randomArray(int min, int max, int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * (max - min + 1)) + min;
        }
        return arr;
    }
    public static boolean checkIfSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    public static int getChunkSize(int[] arr, int chunksNumber) {
        return arr.length / chunksNumber;
    }
}
