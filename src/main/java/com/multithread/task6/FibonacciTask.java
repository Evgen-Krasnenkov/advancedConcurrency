package com.multithread.task6;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Long> {
    private static final int CAP = 11;
    private final Map<Long, Long> cache;
    private final long number;
    public FibonacciTask(long i) {
        this.number = i;
        this.cache = new ConcurrentHashMap<>();
    }
    public FibonacciTask(long i, Map<Long, Long> cache) {
        this.number = i;
        this.cache = cache;
    }
    @Override
    protected Long compute() {
        if(cache.containsKey(number)){
            return cache.get(number);
        }
        if(number < CAP){
            long calculatedFibonacci = calcFibonacciRecursion(number);
            cache.put(number, calculatedFibonacci);
            return calculatedFibonacci;
        }
        FibonacciTask fibonacciTaskNext = new FibonacciTask(number - 1, cache);
        fibonacciTaskNext.fork();
        FibonacciTask fibonacciTaskAfterNext = new FibonacciTask(number - 2, cache);
        fibonacciTaskAfterNext.fork();
        return fibonacciTaskAfterNext.join() + fibonacciTaskNext.join();
    }

    public long calcFibonacciRecursion(long n){
        if (n == 0 || n == 1) {
            return n;
        }
        return calcFibonacciRecursion(n - 2) + calcFibonacciRecursion(n - 1);
    }
}
