package com.multithread.task2;

import java.util.concurrent.*;

public class QuickSortTask extends RecursiveTask<Void> {
    private final int[] array;
    private final int left;
    private final int right;

    public QuickSortTask(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    @Override
    protected Void compute() {
        if (left < right) {
            int pivotIndex = partition(array, left, right);

            QuickSortTask leftTask = new QuickSortTask(array, left, pivotIndex - 1);
            QuickSortTask rightTask = new QuickSortTask(array, pivotIndex + 1, right);

            leftTask.fork();
            rightTask.fork();

            leftTask.join();
            rightTask.join();
        }

        return null;
    }

    private static int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
