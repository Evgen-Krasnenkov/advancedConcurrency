package com.multithread.task5.Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class App {

    public static final int PLACES = 10;

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(PLACES, true);
        List<Integer> productionLine = new CopyOnWriteArrayList<>();
        List<Thread> consumers = new ArrayList<>();
        List<Thread> producers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Runnable producer = new Producer(semaphore, productionLine);
            Thread threadProduce = new Thread(producer, "Producer-" + (i + 1));
            producers.add(threadProduce);
            threadProduce.start();
        }
        for (int j = 0; j < 2; j++) {
           Runnable consumer = new Consumer(semaphore, productionLine);
           Thread threadConsume = new Thread(consumer, "Consumer-" + (j + 1));
           consumers.add(threadConsume);
           threadConsume.start();
        }
        try{
            for (Thread t :
                    producers) {
                t.join();
            }
            for (Thread t :
                    consumers) {
                t.join();
            }
        } catch (InterruptedException e){
            throw new RuntimeException("Threads problem", e);
        }
    }
}
