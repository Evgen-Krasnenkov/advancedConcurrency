package com.multithread.task1;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

class FactorialTaskLongTest {

    @Test
    void whenCorrectLargeValueIn_ThenFactorialComputedOnTime() {
        FactorialTaskBig factorialTask = new FactorialTaskBig(BigInteger.valueOf(1000L));
        long start = System.currentTimeMillis();
        BigInteger compute = factorialTask.compute();
        System.out.println("Result: " + compute);
        System.out.println(("ms: " + (System.currentTimeMillis() - start)));

        final ForkJoinPool pool = new ForkJoinPool();
        long startPool = System.currentTimeMillis();
        BigInteger invoke = pool.invoke(factorialTask);
        System.out.println("Result with ForkJoinPool: " + invoke);
        System.out.println(("ms: " + (System.currentTimeMillis() - startPool)));
    }
}