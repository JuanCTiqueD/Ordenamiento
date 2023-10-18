package implement;

import java.util.ArrayList;
import java.util.List;

public class ParallelBucketSort {
    public static int[] parallelBucketSort(int[] arr, int max) {
        int processorCount = Runtime.getRuntime().availableProcessors();
        List<Thread> threads = new ArrayList<>();

        int[][] buckets = new int[processorCount][max + 1];
        int chunkSize = arr.length / processorCount;

        for (int i = 0; i < processorCount; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == processorCount - 1) ? arr.length : startIndex + chunkSize;
            threads.add(new Thread(new BucketSortTask(buckets[i], arr, startIndex, endIndex)));
        }

        // Iniciar todos los hilos
        for (Thread thread : threads) {
            thread.start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

        // Combinar los resultados de los buckets
        int[] sortedArray = new int[arr.length];
        int index = 0;

        for (int i = 0; i < processorCount; i++) {
            int[] bucket = buckets[i];
            for (int j = 0; j < bucket.length; j++) {
                while (bucket[j] > 0) {
                    sortedArray[index++] = j;
                    bucket[j]--;
                }
            }
        }

        return sortedArray;
    }

    static class BucketSortTask implements Runnable {
        private final int[] bucket;
        private final int[] arr;
        private final int startIndex;
        private final int endIndex;

        public BucketSortTask(int[] bucket, int[] arr, int startIndex, int endIndex) {
            this.bucket = bucket;
            this.arr = arr;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            for (int i = startIndex; i < endIndex; i++) {
                bucket[arr[i]]++;
            }
        }
    }
}
