package test.speedtest;

public record ComparativeResult(int objectsNumber, double sequentialTime, double parallelTime, boolean sorted, int poolSize, int heapsortThreshold) {
    public double getSpeedup() {
        return sequentialTime / parallelTime;
    }
    public double getEfficiency() {
        return getSpeedup() / poolSize;
    }

    public static String getObjectsNumberString(int objectsNumber) {
        String objectsNumberString;
        if (objectsNumber >= 1000_000_000)
            objectsNumberString = String.format("%4dB", objectsNumber / 1000_000_000);
        else if (objectsNumber >= 1000_000)
            objectsNumberString = String.format("%4dM", objectsNumber / 1000_000);
        else if (objectsNumber >= 1000)
            objectsNumberString = String.format("%4dk", objectsNumber / 1000);
        else
            objectsNumberString = String.format("%5d", objectsNumber);
        return objectsNumberString;
    }
    public String[] getStrArray() {
        String[] strArray = new String[5];
        strArray[0] = String.valueOf(objectsNumber);
        strArray[1] = String.valueOf(sequentialTime);
        strArray[2] = String.valueOf(parallelTime);
        strArray[3] = String.valueOf(poolSize);
        strArray[4] = String.valueOf(heapsortThreshold);
        return strArray;
    }

    @Override
    public String toString() {
        return String.format("| %s | %9.2f | %9.2f | %7.2f | %10.2f |", getObjectsNumberString(objectsNumber), sequentialTime, parallelTime, getSpeedup(), getEfficiency());
    }

}
