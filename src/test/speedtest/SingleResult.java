package test.speedtest;

public record SingleResult(int objectsNumber, double time, boolean sorted, int poolSize, int heapsortThreshold) {
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
}
