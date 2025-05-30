import speedtest.MultipleTests;
import speedtest.Result;
import speedtest.Results;
import utils.WriteToCSV;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        int poolSize = 12;
        int parallelThreshold = 10_000;

        int minObjects = 10_000;
        int maxObjects = 100_000;

        String filePath = "speedtest_results.csv";

        int resultsNumber = (int) (Math.log10((double) maxObjects / minObjects) / Math.log10(2)) + 1;
        Result[] results = new Result[resultsNumber];
        int testsNumber = 20;
        int i = 0;
        for (int objectsNumber = minObjects; objectsNumber <= maxObjects; objectsNumber *= 2) {
            MultipleTests tests = new MultipleTests(objectsNumber, testsNumber, poolSize, parallelThreshold);
            tests.run();
            results[i] = tests.getAverageResult();
            i++;
        }
        Results.display(results);

        List<String[]> data = Results.getResultsStrList(results);
        WriteToCSV.writeToCsv(filePath, data);
    }
}