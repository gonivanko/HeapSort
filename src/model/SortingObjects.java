package model;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class SortingObjects {
    public static <T> boolean isSorted(T[] objects, Comparator<T> comparator) {
        for (int i = 1; i < objects.length; i++) {
            if (comparator.compare(objects[i], objects[i - 1]) < 0)
                return false;
        }
        return true;
    }
    public static <T> T[] createRandomArray(int size, Supplier<T> generator, IntFunction<T[]> arrayFactory) {
        T[] array = arrayFactory.apply(size);
        for (int i = 0; i < size; i++) {
            array[i] = generator.get();
        }
        return array;
    }
}
