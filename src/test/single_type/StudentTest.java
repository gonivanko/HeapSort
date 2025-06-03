package test.single_type;

import model.*;
import model.student.Student;
import model.student.StudentComparators;
import sort.HeapSort;

import java.util.Comparator;

public class StudentTest {
    public static void main(String[] args) {

        int objectsNumber = 10;
        int heapsortThreshold = 3;
        int poolSize = 12;

        Comparator<Student> comparator = StudentComparators.BY_GROUP_NAME;

        Student[] objects = SortingObjects.createRandomArray(objectsNumber, Student::createRandom, Student[]::new);

        System.out.println("Objects before sorting:");
        for (Student student : objects) {
            System.out.println(student);
        }

        HeapSort<Student> sorter = new HeapSort<>();

        sorter.parallelSort(objects, 0, objects.length, poolSize, heapsortThreshold, comparator);

        System.out.println("Objects after sorting:");
        for (Student student : objects) {
            System.out.println(student);
        }

        boolean isSorted = SortingObjects.isSorted(objects, comparator);
        System.out.println("Is sorted: " + isSorted);
    }
}
