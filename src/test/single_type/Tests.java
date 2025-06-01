package test.single_type;

import model.Product;
import model.ProductComparators;
import test.speedtest.Result;
import test.speedtest.Results;
import utils.PythonRuner;
import utils.CsvWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Tests {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        int poolSize = 1;
        int minThreshold = 1000;
        int maxThreshold = 100_000;
        int threshMultiplier = 10;

        int minObjects = 100_000;
        int maxObjects = 1000_000;
        int objectsMultiplier = 2;

        boolean runSequential = true;
        boolean runParallel = false;

        Comparator<Product> comparator = ProductComparators.BY_PRICE;

        String resultsPath = "output_data/sequential_results.csv";
        String pythonScriptPath = "src/script.py";


        int resultsNumber = (int) (Math.log10((double) maxObjects / minObjects) / Math.log10(objectsMultiplier)) + 1;
        resultsNumber *= ((int) (Math.log10((double) maxThreshold / minThreshold) / Math.log10(threshMultiplier)) + 1);
        System.out.println("Results number: " + resultsNumber);
        Result[] results = new Result[resultsNumber];
        int testsNumber = 20;
        int i = 0;
        for (int objectsNumber = minObjects; objectsNumber <= maxObjects; objectsNumber *= objectsMultiplier) {
            for (int threshold = minThreshold; threshold <= maxThreshold; threshold *= threshMultiplier) {
                test.speedtest.MultipleTests tests = new test.speedtest.MultipleTests(objectsNumber, testsNumber, poolSize, threshold, comparator, runSequential, runParallel);
                tests.run();
                results[i] = tests.getAverageResult();
                i++;
            }
        }
        List<String[]> data = Results.getResultsStrList(results);
        for (String[] dataRow : data) {
            System.out.println(Arrays.toString(dataRow));
        }
        CsvWriter.writeToCsv(resultsPath, data);

        Results.displaySequential(results);

        String[] pythonArguments = {"python", pythonScriptPath, resultsPath, "1"};

        try {
            System.out.println("Running Python script...");
            PythonRuner.main(pythonArguments);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
