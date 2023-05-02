package com.multithread.task2;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

class QuickSortActionTest {

    @Test
    void whenQuickSortTask_thenSort(){
        int[] ints = getInts();
        QuickSortTask quickSortTask = new QuickSortTask(ints, 0, ints.length - 1);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        forkJoinPool.invoke(quickSortTask);
        System.out.println("Time, ms: " + (System.currentTimeMillis() - start));
//        Arrays.stream(ints).forEach(i -> System.out.printf("Element: %s, ", i));
    }
    private int[] getInts() {
        int[] array = new int[1_000_000_00];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(-1_000_000, 1_000_000);
        }
        return array;
    }
}