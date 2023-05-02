package com.multithread.task5.BlockingQueue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        for (int i = 0; i < 5; i++) {
            Producer producer = new Producer(queue);
            Thread threadProduce = new Thread(producer, "producer " + (i + 1));
            threadProduce.start();
        }
        for (int i = 0; i < 3; i++) {
            Consumer consumer = new Consumer(queue);
            Thread threadConsume = new Thread(consumer, "consumer " + (i + 1));
            threadConsume.setDaemon(true);
            threadConsume.start();
        }
    }
}
