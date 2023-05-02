package com.multithread.task6;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.RecursiveAction;

public class SumAction extends RecursiveAction {
    private static final int SEQUENTIAL_THRESHOLD = 5;
    private int size;

    private double[] array;

    private double result;

    public SumAction(int size) {
        this.size = size;
        this.array = generateArray();
    }

    public SumAction(double[] array) {
        this.array = array;
    }

    public double getResult() {
        return result;
    }

    public double[] getArray() {
        return array;
    }

    @Override
    protected void compute() {
        if (this.size <= SEQUENTIAL_THRESHOLD) { // base case
            this.result = calculateSumOfSquares();
        } else {
            int mid = this.array.length / 2;
            double[] leftDoubles = new double[mid];
            System.arraycopy(this.array, 0, leftDoubles, 0, leftDoubles.length);


            double[] rightDoubles = new double[this.array.length - mid];
            System.arraycopy(this.array, mid, rightDoubles, 0, rightDoubles.length);

            SumAction leftSubTask = new SumAction(leftDoubles);
            SumAction rightSubtask = new SumAction(rightDoubles);

            invokeAll(leftSubTask, rightSubtask);
            result = leftSubTask.result + rightSubtask.result;
        }
    }

    public double calculateSumOfSquaresParallel(){
        return Arrays.stream(array)
                .parallel()
                .map(e -> e * e)
                .sum();
    }
    public double calculateSumOfSquares(){
        return Arrays.stream(array)
                .map(e -> e * e)
                .sum();
    }

    private double[] generateArray(){
        Random random = new Random();
        return random.doubles(size, -1000, 1000).toArray();
    }
}
