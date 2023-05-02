package com.multithread.task6;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    void whenFibonacciNumberLessTen_thenCorrectResult(){
        //given
        long expected = 8L;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //when
        int number = 6;
        FibonacciTask fibonacciTask = new FibonacciTask(number);
        Long aLong = forkJoinPool.invoke(fibonacciTask);
        //then
        assertEquals(expected, aLong);
    }

    @Test
    void whenFibonacciNumberEqualTen_thenCorrectResult(){
        //given
        long expected = 55L;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //when
        int number = 10;
        FibonacciTask fibonacciTask = new FibonacciTask(number);
        Long aLong = forkJoinPool.invoke(fibonacciTask);
        //then
        assertEquals(expected, aLong);
    }

    @Test
    void whenFibonacciNumberLargeTen_thenCorrectResult(){
        //given
        long expected = 1134903170L;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //when
        int number = 45;
        FibonacciTask fibonacciTask = new FibonacciTask(number);
        Long aLong = forkJoinPool.invoke(fibonacciTask);
        //then
        assertEquals(expected, aLong);
    }

    @Test
    void whenFibonacciNumberTwenty_thenCorrectResult(){
        //given
        long expected = 6765;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //when
        int number = 20;
        FibonacciTask fibonacciTask = new FibonacciTask(number);
        Long aLong = forkJoinPool.invoke(fibonacciTask);
        //then
        assertEquals(expected, aLong);
    }
}