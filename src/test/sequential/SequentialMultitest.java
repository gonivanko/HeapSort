package test.sequential;

import model.Product;
import model.ProductComparators;
import test.speedtest.MultipleTests;
import test.speedtest.ComparativeResult;
import test.speedtest.Results;
import utils.WriteToCSV;

import java.util.Comparator;
import java.util.List;

public class SequentialMultitest {
    public static void main(String[] args) {
        int poolSize = 12;
        int heapsortThreshold = 10_000;

        int minObjects = 100_000;
        int maxObjects = 1000_000;

        boolean sortSequential = true;
        boolean sortParallel = false;

        Comparator<Product> comparator = ProductComparators.BY_PRICE;

        String filePath = "speedtest_results.csv";

        int resultsNumber = (int) (Math.log10((double) maxObjects / minObjects) / Math.log10(2)) + 1;
        ComparativeResult[] comparativeResults = new ComparativeResult[resultsNumber];
        int testsNumber = 20;
        int i = 0;
        for (int objectsNumber = minObjects; objectsNumber <= maxObjects; objectsNumber *= 2) {
            MultipleTests tests = new MultipleTests(objectsNumber, testsNumber, poolSize, heapsortThreshold, comparator, sortSequential, sortParallel);
            tests.run();
            comparativeResults[i] = tests.getAverageResult();
            i++;
        }
        Results.display(comparativeResults);

        List<String[]> data = Results.getResultsStrList(comparativeResults);
        WriteToCSV.writeToCsv(filePath, data);
    }
}
