package model;

import java.lang.reflect.Field;
import java.util.Comparator;

public class SortingObjects {

    public static <T> String getHeader(T object) {
        Class<?> clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();

        String header = "";

        for (Field field : fields) {
            header += String.format("%s | ", field.getName());
        }
        return header;
    }
    public static <T> String[] getHeaderArray(T object) {
        Class<?> clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();

        String[] headerArray = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            headerArray[i] += String.format("%s | ", fields[i].getName());
        }
        return headerArray;
    }
    public static <T> void printSortingObjects(T[] objects) {
        String header = getHeader(objects[0]);

        System.out.println(header);
        for (T object : objects) {
            System.out.println(object);
        }

        for (int i = 1; i < objects.length; i++) {}

//        String header = "|   Index   |   Name   | Price | Weight |";
//        System.out.println("-".repeat(header.length()));
//        System.out.println(header);
//        System.out.println("-".repeat(header.length()));
//
//        for (int i = 0; i < objects.length; i++) {
//            System.out.printf("| %9d | %8s |\n", i, objects[i]);
//        }
//        System.out.println("-".repeat(header.length()));
    }
    public static <T> boolean isSorted(T[] objects, Comparator<T> comparator) {
        for (int i = 1; i < objects.length; i++) {
            if (comparator.compare(objects[i], objects[i - 1]) < 0)
                return false;
        }
        return true;
    }
    public static Field[] getObjectColumns(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        System.out.println("Columns of " + clazz.getSimpleName() + ":");
        for (Field field : fields) {
            System.out.println(field.getInt(obj));
        }
        return fields;
    }
}
