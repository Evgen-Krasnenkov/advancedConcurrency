package com.multithread.task1;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class FactorialTaskBig extends RecursiveTask<BigInteger> {
    private final BigInteger number;

    public FactorialTaskBig(BigInteger number) {
        this.number = number;
    }

    @Override
    protected BigInteger compute() {
        if (number.compareTo(BigInteger.valueOf(1L)) <= 0
            || number.compareTo(BigInteger.valueOf(0L)) <= 0) {
            return BigInteger.valueOf(1L);
        } else {
            final FactorialTaskBig taskBig = new FactorialTaskBig(number.subtract(BigInteger.ONE));
            taskBig.fork();
            return number.multiply(taskBig.join());
        }
    }
}
