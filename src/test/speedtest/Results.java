package test.speedtest;

import java.util.ArrayList;
import java.util.List;

public class Results {
    public static boolean allAreSorted(Result[] results) {
        for (Result result : results) {
            if (!result.sorted()) return false;
        }
        return true;
    }
    public static double avgSequentialTime(Result[] results) {
        double avg = 0;
        for (Result result : results) {
            avg += result.sequentialTime();
        }
        return avg / results.length;
    }
    public static double avgParallelTime(Result[] results) {
        double avg = 0;
        for (Result result : results) {
            avg += result.parallelTime();
        }
        return avg / results.length;
    }
    public static Result getAverageResult(Result[] results) {
        return new Result(
                results[0].objectsNumber(), avgSequentialTime(results), avgParallelTime(results), allAreSorted(results), results[0].poolSize(), results[0].heapsortThreshold()
        );
    }
    public static void display(Result[] results) {
        String header = "| Result # |   N   | Threshold | Pool Size | Seq. time | Par. time | Speedup | Efficiency |";
        System.out.println("-".repeat(header.length()));
        System.out.println(header);
        System.out.println("-".repeat(header.length()));
        for (int i = 0; i < results.length; i++) {
            System.out.printf("| %8d %s\n", (i+1), results[i]);
        }
        System.out.println("-".repeat(header.length()));
    }
    public static void displaySequential(Result[] results) {
        String header = "| Result # |   N   | Threshold | Seq. time |";
        System.out.println("-".repeat(header.length()));
        System.out.println(header);
        System.out.println("-".repeat(header.length()));
        for (int i = 0; i < results.length; i++) {
            System.out.printf("| %8d %s\n", (i+1), results[i].sequentialOnly());
        }
        System.out.println("-".repeat(header.length()));
    }
    public static List<String[]> getResultsStrList(Result[] results) {
        List<String[]> strResults = new ArrayList<>();
        strResults.add(new String[] {"objects_number", "sequential_time", "parallel_time", "pool_size", "threshold"});
        for (Result result : results) {
            strResults.add(result.getStrArray());
        }
        return strResults;
    }
}
