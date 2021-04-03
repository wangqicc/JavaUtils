package algorithm;

import java.util.List;

public class SortAlgorithm {

    /**
     * 快速排序一轮
     * @param arr List<Integer> 类型数组
     * @param start 开始位置
     * @param end 结束位置
     * @return 快排结束位置
     */
    private static int part(List<Integer> arr, int start, int end) {
        int temp;
        while (start < end) {
            // 从后往前
            while (start < end && arr.get(end) - arr.get(start) >= 0) {
                --end;
            }
            temp = arr.get(start); arr.set(start, arr.get(end)); arr.set(end, temp);
            // 从前往后
            while (start < end && arr.get(start) - arr.get(end) <= 0) {
                ++start;
            }
            temp = arr.get(start); arr.set(start, arr.get(end)); arr.set(end, temp);
        }
        return start;
    }

    /**
     * 快速排序
     * @param arr List<Integer> 类型数组
     * @param start 开始位置
     * @param end 结束位置
     */
    public static void quickSort(List<Integer> arr, int start, int end) {
        if (start < end) {
            int position = part(arr, start, end);
            quickSort(arr, start, position - 1);
            quickSort(arr, position + 1, end);
        }
    }

    /**
     * 快速排序
     * @param arr List<Integer> 类型数组
     */
    public static void quickSort(List<Integer> arr) {
        quickSort(arr, 0, arr.size() - 1);
    }

    /**
     * 冒泡排序
     * @param arr List<Integer> 类型数组
     * @param start 开始位置
     * @param end 结束位置
     */
    public static void bubbleSort(List<Integer> arr, int start, int end) {
        int temp;
        for (int i = start; i < end; ++i) {
            for (int j = start; j < end - i; ++j) {
                if (arr.get(j) - arr.get(j + 1) > 0) {
                    temp = arr.get(j); arr.set(j, arr.get(j + 1)); arr.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * 冒泡排序
     * @param arr List<Integer> 类型数组
     */
    public static void bubbleSort(List<Integer> arr) {
        bubbleSort(arr, 0, arr.size() - 1);
    }

    /**
     * 插入排序
     * @param arr List<Integer> 类型数组
     * @param start 开始位置
     * @param end 结束位置
     */
    public static void insertSort(List<Integer> arr, int start, int end) {
        int temp;
        for (int i = start + 1; i < end + 1; ++i) {
            for (int j = start; j < i; ++j) {
                if (arr.get(i) - arr.get(j) < 0) {
                    temp = arr.get(i); arr.set(i, arr.get(j)); arr.set(j, temp);
                }
            }
        }
    }

    /**
     * 插入排序
     * @param arr List<Integer> 类型数组
     */
    public static void insertSort(List<Integer> arr) {
        insertSort(arr, 0, arr.size() - 1);
    }

    /**
     * 选择排序
     * @param arr List<Integer> 类型数组
     * @param start 开始位置
     * @param end 结束位置
     */
    public static void selectSort(List<Integer> arr, int start, int end) {
        int k, temp;
        for (int i = start; i < end + 1; ++i) {
            k = i;
            for (int j = i + 1; j < end + 1; ++j) {
                if (arr.get(k) - arr.get(j) > 0) {
                    k = j;
                }
            }
            temp = arr.get(i); arr.set(i, arr.get(k)); arr.set(k, temp);
        }
    }

    /**
     * 选择排序
     * @param arr List<Integer> 类型数组
     */
    public static void selectSort(List<Integer> arr) {
        selectSort(arr, 0, arr.size() - 1);
    }

}
