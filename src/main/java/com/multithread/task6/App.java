package com.multithread.task6;

import java.util.concurrent.ForkJoinPool;

public class App {
    private final static int SIZE = 500_000_00;

    public static void main(String[] args) {
        SumAction sumAction = new SumAction(SIZE);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        long start = System.currentTimeMillis();
        double sumOfSquares = sumAction.calculateSumOfSquares();
        System.out.printf("Result: %s, time, ms: %s", sumOfSquares, System.currentTimeMillis() - start);
        long startParallel = System.currentTimeMillis();
        double sumOfSquaresParallel = sumAction.calculateSumOfSquaresParallel();
        System.out.printf("\nResult: %s, time parallel, ms: %s", sumOfSquaresParallel, System.currentTimeMillis() - startParallel);

        double[] doubles = sumAction.getArray();
        Applier a = new Applier(doubles, 0, doubles.length, null);
        long startApplier = System.currentTimeMillis();
        pool.invoke(a);
        double result = a.result;
        System.out.printf("\nResult: %s, time applier, ms: %s", result, System.currentTimeMillis() - startApplier);

        long startSumAction = System.currentTimeMillis();
        pool.invoke(sumAction);
        double resultSum = sumAction.getResult();
        System.out.printf("\nResult: %s, time SumAction, ms: %s", resultSum, System.currentTimeMillis() - startSumAction);

    }
}
