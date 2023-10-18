package implement;

import intefaces.SortingAlgorithmsService;

import java.util.Arrays;

public class SortingAlgorithmsImpl implements SortingAlgorithmsService {
    public int[] bubbleSort(int[] arr) {
        int n = arr.length;
        int[] sortedArray = Arrays.copyOf(arr, n);

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }
        }

        return sortedArray;
    }

    public int[] countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[arr.length];

        for (int i : arr) {
            count[i - min]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }

        return output;
    }

    public int[] bucketSort(int[] arr, int max) {
        // Llama al método de ordenación paralelo
        return ParallelBucketSort.parallelBucketSort(arr, max);
    }

    public int[] defaultArr(int[] arr){
        Arrays.sort(arr);
        return arr;
    }
}
