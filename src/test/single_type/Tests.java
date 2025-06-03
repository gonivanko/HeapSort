package test.single_type;

import model.product.Product;
import model.product.ProductComparators;
import test.speedtest.Result;
import test.speedtest.Results;
import utils.PythonRuner;
import utils.CsvWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class Tests {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        int poolSize = 1;
        int minThreshold = 1000;
        int maxThreshold = 100_000;
        int threshMultiplier = 10;

        int minObjects = 100_000;
        int maxObjects = 500_000;
        int objectsMultiplier = 2;

        boolean runSequential = true;
        boolean runParallel = false;

        String resultsPath = "output_data/sequential_results.csv";
        String pythonScriptPath = "src/script.py";

        Comparator<Product> comparator = ProductComparators.BY_PRICE;
        Supplier<Product> generator = Product::createRandom;
        IntFunction<Product[]> arrayFactory = Product[]::new;


        int resultsNumber = (int) (Math.log10((double) maxObjects / minObjects) / Math.log10(objectsMultiplier)) + 1;
        resultsNumber *= ((int) (Math.log10((double) maxThreshold / minThreshold) / Math.log10(threshMultiplier)) + 1);
        System.out.println("Results number: " + resultsNumber);
        Result[] results = new Result[resultsNumber];
        int testsNumber = 20;
        int i = 0;
        for (int objectsNumber = minObjects; objectsNumber <= maxObjects; objectsNumber *= objectsMultiplier) {
            for (int threshold = minThreshold; threshold <= maxThreshold; threshold *= threshMultiplier) {
                test.speedtest.MultipleTests<Product> tests = new test.speedtest.MultipleTests<>(objectsNumber, testsNumber, poolSize, threshold, comparator, runSequential, runParallel, generator, arrayFactory);
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
