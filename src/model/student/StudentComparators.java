package model.student;

import model.product.Product;

import java.util.Comparator;

public class StudentComparators {
    public static final Comparator<Product> BY_NAME =
            Comparator.comparing(Product::getName);
    public static final Comparator<Student> BY_SURNAME =
            Comparator.comparing(Student::getSurname);

    public static final Comparator<Student> BY_GROUP_NAME =
            Comparator.comparing(Student::getGroupName);
}
