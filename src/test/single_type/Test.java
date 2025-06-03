package test.single_type;

import model.Product;
import model.ProductComparators;
import model.Products;
import model.SortingObjects;
import sort.HeapSort;

import java.util.Comparator;

public class Test {
    public static void main(String[] args) {

        int objectsNumber = 10;
        int heapsortThreshold = 3;
        int poolSize = 12;

        Comparator<Product> comparator = ProductComparators.BY_WEIGHT;

        Product[] products = Products.createRandom(objectsNumber);

        System.out.println("Objects before sorting:");
        Products.print(products);

        HeapSort<Product> sorter = new HeapSort<>();

        sorter.parallelSort(products, 0, products.length, poolSize, heapsortThreshold, comparator);

        System.out.println("Objects after sorting:");
        Products.print(products);
        SortingObjects.printSortingObjects(products);

        boolean isSorted = Products.isSorted(products, comparator);
        System.out.println("Is sorted: " + isSorted);
    }
}
