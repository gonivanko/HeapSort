package test.sequential;

import model.Product;
import model.ProductComparators;
import test.speedtest.MultipleTests;
import test.speedtest.Result;
import test.speedtest.Results;
import utils.PythonRuner;
import utils.CsvWriter;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class SequentialMultitest {
    public static void main(String[] args) {
        int poolSize = 12;
        int heapsortThreshold = 10_000;

        int minObjects = 10_000;
        int maxObjects = 100_000;

        boolean runSequential = true;
        boolean runParallel = false;

        Comparator<Product> comparator = ProductComparators.BY_PRICE;

        String resultsPath = "data/sequential_results.csv";
        String pythonScriptPath = "src/plot.py";


        int resultsNumber = (int) (Math.log10((double) maxObjects / minObjects) / Math.log10(2)) + 1;
        Result[] results = new Result[resultsNumber];
        int testsNumber = 20;
        int i = 0;
        for (int objectsNumber = minObjects; objectsNumber <= maxObjects; objectsNumber *= 2) {
            MultipleTests tests = new MultipleTests(objectsNumber, testsNumber, poolSize, heapsortThreshold, comparator, runSequential, runParallel);
            tests.run();
            results[i] = tests.getAverageResult();
            i++;
        }
        List<String[]> data = Results.getResultsStrList(results);
        CsvWriter.writeToCsv(resultsPath, data);

        String[] pythonArguments = {"python", pythonScriptPath, resultsPath, "1"};

        try {
            System.out.println("Running Python script...");
            PythonRuner.main(pythonArguments);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
