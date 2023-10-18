package intefaces;

import java.util.Arrays;

public interface SortingAlgorithmsService {
    public int[] bubbleSort(int[] arr);

    public int[] countingSort(int[] arr);

    public int[] bucketSort(int[] arr, int max);

    public int[] defaultArr(int[] arr);
}
