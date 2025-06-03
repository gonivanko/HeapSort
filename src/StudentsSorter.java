import model.student.Student;
import model.student.StudentComparators;
import test.speedtest.MultipleTests;
import test.speedtest.Result;
import test.speedtest.Results;
import utils.CsvWriter;
import utils.PythonRuner;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class StudentsSorter {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        int poolSize = 12;
        int heapsortThreshold = 10_000;

        int minObjects = 50_000;
        int maxObjects = 100_000;
        int objectsMultiplier = 2;

        int minPool = 4, maxPool = 12, poolIncrease = 4;

        Comparator<Student> comparator = StudentComparators.BY_GROUP_NAME;
        Supplier<Student> generator = Student::createRandom;
        IntFunction<Student[]> arrayFactory = Student[]::new;

        String resultsPath = "output_data/speedtest_pool_results.csv";
        String pythonScriptPath = "src/script.py";

        boolean displayPlots = true;

        int resultsNumber = (int) (Math.log10((double) maxObjects / minObjects) / Math.log10(objectsMultiplier)) + 1;
        resultsNumber *= (maxPool - minPool) / poolIncrease + 1;
        Result[] results = new Result[resultsNumber];
        int testsNumber = 20;
        int i = 0;
        for (int objectsNumber = minObjects; objectsNumber <= maxObjects; objectsNumber *= objectsMultiplier) {
            for (poolSize = minPool; poolSize <= maxPool; poolSize += poolIncrease) {
                MultipleTests<Student> tests = new MultipleTests<>(objectsNumber, testsNumber, poolSize, heapsortThreshold, comparator, generator, arrayFactory);
                tests.run();
                results[i] = tests.getAverageResult();
                i++;
            }
        }
        Results.display(results);

        List<String[]> data = Results.getResultsStrList(results);
        CsvWriter.writeToCsv(resultsPath, data);

        String[] pythonArguments = {"python", pythonScriptPath, resultsPath};
        if (displayPlots) {
            try {
                System.out.println("Running Python script...");
                PythonRuner.main(pythonArguments);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
