package speedtest;

import java.util.ArrayList;
import java.util.List;

public class Results {
    private final Result[] results;
    public Results(Result[] results) {
        this.results = results;
    }
    public Result[] getResults() {
        return results;
    }
    public static Result getAverageResult(Result[] results) {
        double avgSequentialTime = 0;
        double avgParallelTime = 0;
        boolean sorted = true;
        for (Result result : results) {
            avgSequentialTime += result.sequentialTime();
            avgParallelTime += result.parallelTime();
            sorted = sorted && result.sorted();
        }
        avgSequentialTime /= results.length;
        avgParallelTime /= results.length;
        return new Result(results[0].objectsNumber(), avgSequentialTime, avgParallelTime, sorted, results[0].poolSize(), results[0].parallelThreshold());
    }
    public static void display(Result[] results) {
        String header = "| Result # |   N   | Seq. time | Par. time | Speedup | Efficiency |";
        System.out.println("-".repeat(header.length()));
        System.out.println(header);
        System.out.println("-".repeat(header.length()));
        for (int i = 0; i < results.length; i++) {
            System.out.printf("| %8d %s\n", (i+1), results[i]);
        }
        System.out.println("-".repeat(header.length()));
    }
    public static List<String[]> getResultsStrList(Result[] results) {
        List<String[]> strResults = new ArrayList<>();
        strResults.add(new String[] {"objects_number", "sequential_time", "parallel_time", "pool_size", "parallel_threshold"});
        for (Result result : results) {
            strResults.add(result.getStrArray());
        }
        return strResults;
    }
}
