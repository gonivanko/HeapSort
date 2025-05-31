package test.speedtest;

import java.util.ArrayList;
import java.util.List;

public class Results {
    public static boolean allAreSorted(ComparativeResult[] comparativeResults) {
        for (ComparativeResult comparativeResult : comparativeResults) {
            if (!comparativeResult.sorted()) return false;
        }
        return true;
    }
    public static double avgSequentialTime(ComparativeResult[] comparativeResults) {
        double avg = 0;
        for (ComparativeResult comparativeResult : comparativeResults) {
            avg += comparativeResult.sequentialTime();
        }
        return avg / comparativeResults.length;
    }
    public static double avgParallelTime(ComparativeResult[] comparativeResults) {
        double avg = 0;
        for (ComparativeResult comparativeResult : comparativeResults) {
            avg += comparativeResult.parallelTime();
        }
        return avg / comparativeResults.length;
    }
    public static ComparativeResult getAverageResult(ComparativeResult[] comparativeResults) {
        return new ComparativeResult(
                comparativeResults[0].objectsNumber(), avgSequentialTime(comparativeResults), avgParallelTime(comparativeResults), allAreSorted(comparativeResults), comparativeResults[0].poolSize(), comparativeResults[0].heapsortThreshold()
        );
    }
    public static void display(ComparativeResult[] comparativeResults) {
        String header = "| Result # |   N   | Seq. time | Par. time | Speedup | Efficiency |";
        System.out.println("-".repeat(header.length()));
        System.out.println(header);
        System.out.println("-".repeat(header.length()));
        for (int i = 0; i < comparativeResults.length; i++) {
            System.out.printf("| %8d %s\n", (i+1), comparativeResults[i]);
        }
        System.out.println("-".repeat(header.length()));
    }
    public static List<String[]> getResultsStrList(ComparativeResult[] comparativeResults) {
        List<String[]> strResults = new ArrayList<>();
        strResults.add(new String[] {"objects_number", "sequential_time", "parallel_time", "pool_size", "parallel_threshold"});
        for (ComparativeResult comparativeResult : comparativeResults) {
            strResults.add(comparativeResult.getStrArray());
        }
        return strResults;
    }
}
