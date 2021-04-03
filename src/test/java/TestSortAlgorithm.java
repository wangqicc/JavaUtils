import algorithm.SortAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class TestSortAlgorithm {

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<Integer>();
        arr.add(1);
        arr.add(3);
        arr.add(1);
        arr.add(4);
        arr.add(5);
        arr.add(2);
        arr.add(0);
        System.out.println("arr: " + arr);

        SortAlgorithm.quickSort(arr);
        System.out.println("quick sort: " + arr);

        SortAlgorithm.bubbleSort(arr);
        System.out.println("bubble sort: " + arr);

        SortAlgorithm.insertSort(arr);
        System.out.println("insert sort: " + arr);

        SortAlgorithm.selectSort(arr);
        System.out.println("select sort: " + arr);
    }

}
