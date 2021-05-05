import algorithm.SortAlgorithm;
import entity.Person;

import java.util.*;


/**
 * 按照年龄对 Person 进行排序
 */
class AgeCompare implements Comparator<Person> {
    public int compare(Person o1, Person o2) {
        return o1.getAge() - o2.getAge();
    }
}


public class TestSortAlgorithm {

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
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

        List<Person> ps = new ArrayList<>();
        ps.add(new Person("jack", 24));
        ps.add(new Person("milk", 10));
        // JDK8 中可以使用 Comparator 表达式进行构建迭代器
        // 不过还是推荐下面的一种写法，先利用 Comparator 构建好排序规则，然后再新建这个规则的对象的方式进行排序
//        ps.sort(Comparator.comparingInt(Person::getAge));
        ps.sort(new AgeCompare());
        System.out.println(ps);
    }

}
